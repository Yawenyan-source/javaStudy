package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Stream流实现对数据的排序
 */
public class Test08 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen1", 3));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 152));
        Stream<User> stream = users.stream();
        stream.sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        }).forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user);
            }
        });
    }
}
