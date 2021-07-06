package com.wen.jdk8;

import com.wen.jdk8.service.OrderService;
import org.junit.Test;

public class test {
    @Test
    public static void main(String[] args) {
        /**
         * 使用匿名内部类的形式调用OrderService 中的get()方法
         */
        //原始形式
        OrderService orderService = new OrderService() {
            @Override
            public void get() {
                System.out.println("hello world");
            }
        };
        orderService.get();
        System.out.println(" /*==================================================*/");
        //简化形式
        new OrderService() {
            @Override
            public void get() {
                System.out.println("get");
            }
        }.get();
        System.out.println(" /*==================================================*/");
        //使用lambda表达式
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ",run")).start();
    }
}

