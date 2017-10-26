
package cn.gy.thread;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

/**
 * @author yang.gao created on 2016/4/5 18:22
 * @version $Id$
 */
public class SimpleTest {

    public static void runTaskTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i< 3; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int n = 0;
                    while ( n < 100){
                        System.out.println("current " + Thread.currentThread().getName() + " :" + n);
                        n ++;
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.shutdown();
    }

    public static void callTaskTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "call";
            }
        });
        while (true){
            if(result.isDone()){
                try {
                    System.out.println(result.get());
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("wait");
            }
        }
    }

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "call";
            }
        });
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println(s);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

}
