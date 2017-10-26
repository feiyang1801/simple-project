package cn.gy.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * futures
 * Created by yang.gao on 2017/5/23.
 */
public class FuturesTest {

    public static void mergeTest() throws Exception{
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future1 = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("future1 start...");
                TimeUnit.SECONDS.sleep(2);
                throw new RuntimeException("run");
//                System.out.println("future1 end...");
//                return "future1";
            }
        });
        ListenableFuture<String> future2 = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("future2 start...");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("future2 end...");
                return "future2";
            }
        });
        List<ListenableFuture<String>> futures = Lists.newArrayList();
        futures.add(future1);
        futures.add(future2);
        ListenableFuture<List<String>> mergeFutures = Futures.successfulAsList(futures);
//        Futures.addCallback(mergeFutures, new FutureCallback<List<String>>() {
//            @Override
//            public void onSuccess(List<String> result) {
//                System.out.println("mergeFutures start...");
//                System.out.println(Thread.currentThread().getName());
//                System.out.println(result);
//                System.out.println();
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        },Executors.newSingleThreadExecutor());
        List<String> results = mergeFutures.get();
        System.out.println(results);
    }

    public static void main(String[] args) throws Exception{
        mergeTest();
    }

}
