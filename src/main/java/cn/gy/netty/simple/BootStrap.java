package cn.gy.netty.simple;

import com.google.common.collect.Lists;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 启动方法
 * Created by gaoyang on 2017/11/1.
 */
public class BootStrap {

    private int workerThreadCount;

    private List<SocketHandler> handlers = Lists.newArrayList();
    private Selector[] selectors;
    private Lock lock = new ReentrantLock();
    private ThreadFactory threadFactory = new NameThreadFactory("simple-netty");

    public BootStrap wokerThreadCount(int count) {
        this.workerThreadCount = count;
        return this;
    }

    public BootStrap addSocketHandler(SocketHandler handler) {
        this.handlers.add(handler);
        return this;
    }

    public void bind(int port) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocket.bind(inetSocketAddress);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isValid() && selectionKey.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(getRandomSelector(), SelectionKey.OP_READ);
                }
            }
        }
    }

    private Selector getRandomSelector() throws Exception {
        lock.lock();
        try {
            if (selectors == null) {
                selectors = new Selector[workerThreadCount];
            }
            int index = new Random().nextInt(100) % workerThreadCount;
            Selector selector = selectors[index];
            if (selector != null) {
                return selector;
            }
            selector = Selector.open();
            selectors[index] = selector;
//            threadFactory.newThread();
            return selector;
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {

    }

}
