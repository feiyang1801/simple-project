
package cn.gy.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Queue 实现consumer provider
 * @author yang.gao created on 2016/10/13 11:04
 * @version $Id$
 */
public class ConsumeV1 {

    static class Provider implements Runnable{

        private final Queue<Integer> queue;

        public Provider(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    if(queue.size() < 3){
                        System.out.println("produce ..");
                        queue.offer(new Random().nextInt());
                        queue.notify();
                    }else{
                        System.out.println("queue is full,wait");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable{

        private final Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    if(queue.size() > 0){
                        System.out.println("consumer consume: " + queue.poll());
                        queue.notify();
                    }else{
                        System.out.println("consumer wait..");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Consumer(queue));
        executorService.execute(new Consumer(queue));
        executorService.execute(new Provider(queue));
        executorService.execute(new Provider(queue));
    }

}
