package cn.gy.netty.simple;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 带名字的线程
 * Created by gaoyang on 2017/11/1.
 */
public class NameThreadFactory implements ThreadFactory{

    private String namePrefix;
    private AtomicLong count;

    public NameThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,this.namePrefix + "-" + this.count.incrementAndGet());
    }
}
