package com.wen.threads.days03;

/**
 * 多线程的yield使用
 * 主动释放cpu执行权
 */
public class ThreadTest07 extends Thread {
    public ThreadTest07(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i == 30) {
                System.out.println(Thread.currentThread().getName() + "，释放cpu执行权");
                this.yield();
            }
            System.out.println(Thread.currentThread().getName() + "," + i);
        }
    }

    public static void main(String[] args) {
        new ThreadTest07("wen1").start();
        new ThreadTest07("wen2").start();
    }
}
