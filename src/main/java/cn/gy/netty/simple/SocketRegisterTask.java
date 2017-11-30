package cn.gy.netty.simple;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by gaoyang on 2017/11/1.
 */
public class SocketRegisterTask implements Runnable{

    private Selector selector;
    private List<SocketHandler> handlers;

    public SocketRegisterTask(Selector selector, List<SocketHandler> handlers) {
        this.selector = selector;
        this.handlers = handlers;
    }

    @Override
    public void run(){
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        socketChannel.read(byteBuffer);
                        SocketHandlerContext context = SocketHandlerContext.build(handlers,true);
                        context.fireRead(byteBuffer);
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                    if (selectionKey.isValid() && selectionKey.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        if (byteBuffer.hasRemaining()) {
                            selectionKey.interestOps(SelectionKey.OP_WRITE);
                        }
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
