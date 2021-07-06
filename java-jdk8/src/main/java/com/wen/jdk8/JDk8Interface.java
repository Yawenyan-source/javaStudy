package com.wen.jdk8;

/**
 * 默认的时候就是public abstract
 */
public interface JDk8Interface {
    void add();

    /**
     * jdk8 提供了默认的方法
     */
    default void defaultGet() {
        System.out.println("defaultGet");
    }

    /**
     * jdk8 提供了静态方法
     */
    static void staticDel() {
        System.out.println("staticDel");
    }
}
