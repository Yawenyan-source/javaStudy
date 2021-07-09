package com.wen.rfspringboot.pojo;

import com.wen.rfspringboot.annotation.wenName;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射越过泛型检查
 * 一般情况下都是用Class.forName("")
 */
class UserTest6 {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Class<?> userClass = Class.forName("com.wen.rfspringboot.pojo.User");
        Object o = userClass.getDeclaredConstructor().newInstance();
        Method wen = userClass.getDeclaredMethod("wen");
        wenName wenDeclaredAnnotation = wen.getDeclaredAnnotation(wenName.class);
        System.out.println(wenDeclaredAnnotation);
    }
}