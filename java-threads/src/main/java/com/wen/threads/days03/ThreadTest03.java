package com.wen.threads.days03;

/**
 * 用户线程和守护线程， 一般情况下都是用户线程，设置为守护线程，主线程挂了，守护线程跟着一起挂
 */
public class ThreadTest03 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        }, "我是子线程");
//        thread.start();
        //设置为守护线程，默认为用户线程
        thread.setDaemon(true);
        System.out.println("我是主线程");
    }
}
