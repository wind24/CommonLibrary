package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by huangzefeng on 20/9/16.
 */

public class ThreadTest {

    public static void main(String[] args){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("current thread in callable:"+Thread.currentThread().getName());
                Thread.sleep(5000);
                return "test thread";
            }
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Thread(futureTask){
            @Override
            public void run() {
                super.run();
                if(futureTask.isDone()){
                    System.out.println("current thread:"+Thread.currentThread().getName());
                    try {
                        System.out.print("future task:"+futureTask.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

}
