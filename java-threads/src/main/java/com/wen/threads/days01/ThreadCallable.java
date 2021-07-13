package com.wen.threads.days01;

import java.util.concurrent.Callable;

/**
 * 用Callable和Future实现多线程
 * 底层是用LockSupport.park()和LockSupport.unpark()实现
 */
public class ThreadCallable implements Callable<Integer> {
    /**
     * 当前线程需要执行的代码，返回结果
     *
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "我是子线程，开始执行");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "返回结果是1");
        return 1;
    }
}
