package com.wen.threads.days01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池创建线程
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是子线程，" + Thread.currentThread().getName());
            }
        });
    }
}
