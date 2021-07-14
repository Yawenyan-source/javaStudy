package com.wen.threads.days03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁的condition使用
 */
public class ThreadTest06 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ThreadTest06 threadTest06 = new ThreadTest06();
        threadTest06.cal();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadTest06.signal();
    }

    /**
     * 使线程阻塞，类似wait
     */
    public void cal() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //主动释放锁，当前线程变成阻塞状态
                try {
                    System.out.println("开始获取锁，并阻塞");
                    lock.lock();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("释放锁成功");
                }
            }
        }).start();
    }

    /**
     * 唤醒子线程方法,也需要在获取锁的时候执行，类似notify
     */
    public void signal() {
        try {
            lock.lock();
            condition.signal();
            System.out.println("唤醒子线程");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("释放锁成功");
        }
    }
}
