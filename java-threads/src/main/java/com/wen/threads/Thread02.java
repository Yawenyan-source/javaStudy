package com.wen.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadCallable threadCallable = new ThreadCallable();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(threadCallable);
        new Thread(integerFutureTask).start();
        // 我的主线程需要等待我们子线程给我的返回结果
        Integer result = integerFutureTask.get();
        System.out.println(Thread.currentThread().getName() + "," + result);
    }
}
