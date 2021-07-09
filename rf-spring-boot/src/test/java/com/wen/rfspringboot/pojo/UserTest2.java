package com.wen.rfspringboot.pojo;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射：无参，有参构造函数
 * 一般情况下都是用Class.forName("")
 */
class UserTest2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> userClass = Class.forName("com.wen.rfspringboot.pojo.User");
        User user = (User) userClass.getDeclaredConstructor().newInstance();
        System.out.println(user);
    }
}