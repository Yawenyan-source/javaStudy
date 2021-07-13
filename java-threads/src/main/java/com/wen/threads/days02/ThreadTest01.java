package com.wen.threads.days02;

public class ThreadTest01 {
    private Object objectLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new ThreadTest01().print();
    }

    public void print() throws InterruptedException {
        new Thread(() -> {
            /**
             * this.wait()释放锁资源，同时当前线程会阻塞
             * this.notify()等必须放到synchronized同步锁中使用
             */
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>1<<<<<<<<<<然后阻塞了");
                //获取到锁的对象.wait()
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>2<<<<<<<<<<三秒之后释放唤醒子线程了");
            }
        }).start();

        try {
            Thread.sleep(3000);
            //主线程3秒之后唤醒子线程
            synchronized (objectLock) {
                objectLock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
