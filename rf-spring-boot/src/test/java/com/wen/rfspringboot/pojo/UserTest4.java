package com.wen.rfspringboot.pojo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射调用方法
 * 一般情况下都是用Class.forName("")
 */
class UserTest4 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> userClass = Class.forName("com.wen.rfspringboot.pojo.User");
        Method method = userClass.getDeclaredMethod("wen");
        User user = (User) userClass.newInstance();
        method.setAccessible(true);
        method.invoke(user);
        Method sum = userClass.getDeclaredMethod("sum", Integer.class, Integer.class);
        sum.setAccessible(true);
        System.out.println(sum.invoke(user, 1, 2));
    }
}