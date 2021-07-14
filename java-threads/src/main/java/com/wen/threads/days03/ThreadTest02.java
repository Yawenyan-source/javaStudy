package com.wen.threads.days03;

public class ThreadTest02 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ",线程执行"), "t1");
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",线程执行");
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }

}
