
package cn.gy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yang.gao created on 2016/10/24 16:04
 * @version $Id$
 */
public class MultiPortEcho {

    private int ports[];
    private ByteBuffer echoBuffer = ByteBuffer.allocate(1024);

    public MultiPortEcho(int ports[]) throws Exception{
        this.ports = ports;
        go();
    }

    private void go() throws Exception{
        Selector selector = Selector.open();
        for (int port : ports) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ServerSocket ss = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Going to listen on " + port);
        }
        while (true){
            int num = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                if(key.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                    it.remove();
                }else if(key.isReadable()){
                    SocketChannel sc = (SocketChannel)key.channel();
                    int bytesEchoed = 0;
                    while (true){
                        echoBuffer.clear();
                        int r = sc.read(echoBuffer);
                        if(r <= 0){
                            break;
                        }
                        echoBuffer.flip();
                        sc.write(echoBuffer);
                        bytesEchoed += r;
                    }
                    System.out.println("Echoed "+bytesEchoed+" from "+sc );
                    sc.close();
                    it.remove();
                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        int ports[] = {8081};
//        int ports[] = new int[args.length];
//        for(int i = 0; i < args.length; i++){
//            ports[i] = Integer.parseInt(args[i]);
//        }
        new MultiPortEcho(ports);
    }

}
