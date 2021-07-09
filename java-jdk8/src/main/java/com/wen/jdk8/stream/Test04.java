package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 使用stream流查找集合中最大和最小的元素
 */
public class Test04 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 152));
        Stream<User> stream = users.parallelStream();
//        Optional<User> max = stream.max(new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });
//        System.out.println(max.get());

        Optional<User> min = stream.min((o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println(min.get());
    }

}
