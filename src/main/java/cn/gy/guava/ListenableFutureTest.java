package cn.gy.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * guava listenableFuture demo
 * Created by yang.gao on 2017/5/12.
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<String> listenableFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call thread " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
                return "call result";
            }
        });
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("callback thread " + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
