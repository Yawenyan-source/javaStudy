package com.wen.threads;

/**
 * 方式二：实现Runnable接口
 */
public class ThreadRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "我是子线程");
    }

    public static void main(String[] args) {
        //启动线程
//        new Thread(new ThreadRunnable()).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "我是子线程")).start();
        //使用匿名内部类的形式来创建线程
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "我也是子线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
