package com.example;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TestMain {

    public static void main(String[] args) {
//        testFuture1();
        testFuture2();
    }

    private static void testFuture2() {
        Callable callable = () -> {
            Thread.sleep(1000);
            return new Random().nextInt(100);
        };

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = (FutureTask<Integer>) threadPool.submit(callable);
        try {
            System.out.println("start count");
//            futureTask.cancel(true);
            if (!futureTask.isCancelled())
                System.out.println("count:" + futureTask.get());
            System.out.println("end count:" + futureTask.isDone()+","+futureTask.isCancelled());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void testFuture1() {
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            return new Random().nextInt(100);
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println("start count");
            System.out.println("count:" + futureTask.get());
            System.out.println("end count");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
