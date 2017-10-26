
package cn.gy.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock实现
 * @author yang.gao created on 2016/10/14 15:23
 * @version $Id$
 */
public class ConsumeV2 {

    static class BoundBuffer<T>{
        private Object[] items;
        private Lock lock = new ReentrantLock();
        private Condition notFull = lock.newCondition();
        private Condition notEmpty = lock.newCondition();
        private int takeptr = 0;
        private int putptr = 0;
        private int count = 0;

        public BoundBuffer(int size) {
            items = new Object[size];
        }

        public T take() throws Exception{
            lock.lock();
            try{
                while (count == 0){
                    notEmpty.await();
                }
                Object x = items[takeptr];
                if(++takeptr == items.length){
                    takeptr = 0;
                }
                --count;
                notFull.signal();
                return (T)x;
            }finally {
                lock.unlock();
            }
        }

        public void put(T x) throws Exception{
            lock.lock();
            try{
                while (count == items.length){
                    notFull.await();
                }
                items[putptr] = x;
                if(++putptr == items.length){
                    putptr = 0;
                }
                ++count;
                notEmpty.signal();
            }finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        final BoundBuffer<Integer> buffer = new BoundBuffer<Integer>(2);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Integer x = buffer.take();
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("take:" + x);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Integer x = new Random().nextInt(10);
                    try {
                        buffer.put(x);
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("put:" + x);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
