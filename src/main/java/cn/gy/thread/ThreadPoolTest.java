
package cn.gy.thread;

import java.util.concurrent.*;

/**
 * java 中的各种线程池
 * 
 * @author yang.gao created on 2016/11/21 11:20
 * @version $Id$
 */
public class ThreadPoolTest {

    public void commonPool() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " run start..");
                TimeUnit.SECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + " run end..");
                return "hello server";
            }
        });
        while (!future.isDone()) {
            System.out.println(Thread.currentThread().getName() + " wait 2s ...");
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println(Thread.currentThread().getName() + " get result: " + future.get());
        executorService.shutdown();
    }

    public void schedulePoolTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run start..");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " run end..");
            }
        }, 0, 3, TimeUnit.SECONDS);
        // scheduledExecutorService.shutdown();
    }

    public static void main(String[] args) throws Exception {
        new ThreadPoolTest().schedulePoolTest();
    }

}
