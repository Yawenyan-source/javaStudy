package com.wen.threads.days01;

public class Thread01 extends Thread {
    /**
     * 方式一：继承Thread类
     * 线程执行的代码，就是在run方法中 执行完毕，线程死亡
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "我是子线程");
        //当前线程阻塞三秒时间
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是子线程");
    }
    public static void main(String[] args) {
        System.out.println("我是主线程");
        //获取当前线程的名称
        System.out.println(Thread.currentThread().getName());
        //调用start()线程不是立即被cpu调度执行，而是出于就绪状态 --->等待cpu调度 线程从就绪到运行状态
        new Thread01().start();
        new Thread01().start();
        System.out.println("主线程执行完毕");
    }
}
