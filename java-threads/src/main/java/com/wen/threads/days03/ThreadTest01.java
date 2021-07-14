package com.wen.threads.days03;

public class ThreadTest01 {
    private Object objectLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadTest01 threadTest01 = new ThreadTest01();
        threadTest01.print();
        Thread.sleep(5000);
        threadTest01.notifyThread();
    }

    public void notifyThread() {
        synchronized (objectLock) {
            try {
                objectLock.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void print() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectLock) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ",1");
                        objectLock.wait();
                        System.out.println(Thread.currentThread().getName() + ",2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
