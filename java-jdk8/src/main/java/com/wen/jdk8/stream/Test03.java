package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * 使用stream流来计算求和
 */
public class Test03 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 12));
        Stream<User> stream = users.stream();
        Optional<User> sum = stream.reduce(new BinaryOperator<User>() {
            @Override
            public User apply(User user1, User user2) {
                User user3 = new User("sum", user1.getAge() + user2.getAge());
                return user3;
            }
        });
        System.out.println(sum.get().getAge());
    }
}
