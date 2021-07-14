package com.wen.threads.days03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁 获取锁和释放锁 需要开发人员自己定义
 */
public class ThreadTest05 {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadTest05 threadTest05 = new ThreadTest05();
        threadTest05.print1();
        try {
            Thread.sleep(500);
            System.out.println("开始执行线程2抢锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadTest05.print2();
    }


    public void print1() {
//        try {
//            lock.lock();
//            System.out.println("获取锁成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ":获取锁成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + ":释放锁成功");
                }

            }
        }, "t1").start();
    }

    public void print2() {
//        try {
//            lock.lock();
//            System.out.println("获取锁成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":线程2开始准备抢锁，但是没有拿到Lock锁，一直阻塞");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ":获取锁成功");
            }
        }, "t2").start();
    }
}
