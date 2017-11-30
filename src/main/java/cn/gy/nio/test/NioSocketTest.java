package cn.gy.nio.test;

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
 * server socket test
 * selectionKey 在处理完之后，必须显式的remove掉，否则还会在下一次select唤醒后，出现在selectedKeys集合里面
 * Created by gaoyang on 2017/10/30.
 */
public class NioSocketTest {


    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8081);
        serverSocket.bind(inetSocketAddress);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    long byteReads = socketChannel.read(byteBuffer);
                    if (byteReads == -1) {
                        System.out.println("close channel");
                        socketChannel.close();
                    } else {
                        selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("read bytes " + byteReads);
                    }
                }
                if (selectionKey.isValid() && selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    if (byteBuffer.hasRemaining()) {
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    }
                    byteBuffer.compact();
                }
            }
        }
    }

}
