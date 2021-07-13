package com.wen.threads.days02;

public class ThreadCount implements Runnable {

    private int count = 100;

    @Override
    public void run() {
        while (true) {
            if (count > 1) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    count--;
                    System.out.println(Thread.currentThread().getName() + ">>" + count);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadCount threadCount = new ThreadCount();
        new Thread(threadCount).start();
        new Thread(threadCount).start();
    }
}
