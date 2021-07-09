package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Stream过滤器 过滤相关条件
 */
public class Test06 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen1", 3));
        users.add(new User("wen1", 120));
        users.add(new User("wen1", 43));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 152));
        Stream<User> stream = users.stream();
        stream.filter(user -> "wen1".equals(user.getUsername()) && user.getAge() >= 43)
                .forEach(System.out::println);
    }
}
