package com.wen.threads.days03;

/**
 * 如何终止一个线程
 */
public class ThreadTest04 extends Thread {

    @Override
    public void run() {
        while (true) {
            //判断此线程是否被中断
            if (this.isInterrupted()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        ThreadTest04 threadTest04 = new ThreadTest04();
        threadTest04.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程中断了");
        threadTest04.interrupt();
    }
}