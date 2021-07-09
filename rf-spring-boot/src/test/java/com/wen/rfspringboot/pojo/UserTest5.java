package com.wen.rfspringboot.pojo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 反射越过泛型检查
 * 一般情况下都是用Class.forName("")
 */
class UserTest5 {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("wen1");
        Class<? extends ArrayList> aClass = strings.getClass();
        Method addMethod = aClass.getDeclaredMethod("add", Object.class);
        addMethod.invoke(strings, 1);
        System.out.println(strings);
    }
}