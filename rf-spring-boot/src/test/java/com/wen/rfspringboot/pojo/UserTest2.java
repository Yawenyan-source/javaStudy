package com.wen.rfspringboot.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射：无参，有参构造函数
 * 一般情况下都是用Class.forName("")
 */
class UserTest2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> userClass = Class.forName("com.wen.rfspringboot.pojo.User");
        /*
         * 默认是执行无参构造函数
         * */
        User user = (User) userClass.getDeclaredConstructor().newInstance();
        System.out.println(user);
        User user1 = (User) userClass.getDeclaredConstructor(String.class, Integer.class).newInstance("wen", 1);
        System.out.println(user1);
    }
}