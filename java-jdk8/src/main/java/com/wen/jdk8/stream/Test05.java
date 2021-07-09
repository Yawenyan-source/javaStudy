package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * stream做Match匹配,有匹配的数据，返回true，没有返回false
 */
public class Test05 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 152));
        Stream<User> stream = users.stream();
        boolean b = stream.anyMatch(user -> "wen1".equals(user.getUsername()));
        System.out.println(b);
    }
}
