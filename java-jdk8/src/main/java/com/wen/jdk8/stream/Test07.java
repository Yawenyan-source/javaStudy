package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Stream limit 和 skip
 * limit 从头开始获取
 * skip 跳过
 */
public class Test07 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen1", 3));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 152));
        Stream<User> stream = users.stream();
        //mysql limit(0,2) stream分页 (开始skip(),结束：limit())
        stream.skip(1).limit(2).forEach(user -> System.out.println(user));
    }
}
