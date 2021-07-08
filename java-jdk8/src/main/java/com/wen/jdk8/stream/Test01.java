package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试jdk8Stream流
 */
public class Test01 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 12));
        // user对象属性值都是相等的，但是new的是两个对象，这两个对象的内存地址是不一样的
        users.add(new User("wen4", 13));
        users.add(new User("wen4", 13));
        /*
         * 通过集合创建stream流
         * 1、串行流stream()         单线程
         * 2、并行流parallelStream() 多线程
         * 并行流比串行流效率高
         * */
        Stream<User> stream = users.stream();
        //转换成set集合
        Set<User> userSet = stream.collect(Collectors.toSet());
        userSet.forEach(user -> {
            System.out.println(user.toString());
        });
    }
}
