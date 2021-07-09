package com.wen.rfspringboot.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射如何给属性赋值
 * 一般情况下都是用Class.forName("")
 */
class UserTest3 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> userClass = Class.forName("com.wen.rfspringboot.pojo.User");
        Field[] fields = userClass.getDeclaredFields();
        User user = (User) userClass.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field username = userClass.getDeclaredField("username");
        //反射访问私有权限
        username.setAccessible(true);
        username.set(user, "wen");
        System.out.println(user.getUsername());
    }
}