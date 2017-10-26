
package cn.gy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 返回服务器时间
 * @author yang.gao created on 2016/11/10 10:33
 * @version $Id$
 */
public class NioServerTimer {

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public void TimeServer() throws Exception{
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8081));
        System.out.println("bind on 8081");
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
                    SocketChannel channel = serverChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                    keyIterator.remove();
                }else if(key.isReadable()){
                    byteBuffer.clear();
                    SocketChannel channel = (SocketChannel)key.channel();
                    channel.read(byteBuffer);
                    String msg = new String(byteBuffer.array());
                    System.out.println("in msg:" + msg);
                    key.interestOps(SelectionKey.OP_WRITE);
                    keyIterator.remove();
                }else if(key.isWritable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    byteBuffer.clear();
                    byteBuffer.put("hello client".getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    keyIterator.remove();
                    System.out.println("channel close");
                    channel.close();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new NioServerTimer().TimeServer();
    }


}
