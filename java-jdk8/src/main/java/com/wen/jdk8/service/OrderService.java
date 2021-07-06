package com.wen.jdk8.service;

/**
 * @functionalInterface 标记该接口为函数接口
 */
@FunctionalInterface
public interface OrderService {
    /**
     * 如果需要使用到OrderService接口
     * 接口是无法new、可以通过匿名内部类定义子类
     */
    void get();

    default void defaultAdd() {
        System.out.println("我是默认方法");
    }

    /**
     * object 父类中的方法可以在函数接口中重写
     *
     * @return
     */
    String toString();
}
