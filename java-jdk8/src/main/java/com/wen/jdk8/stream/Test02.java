package com.wen.jdk8.stream;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream List集合转变成Map集合
 */
public class Test02 {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("wen1", 10));
        users.add(new User("wen2", 11));
        users.add(new User("wen3", 12));
        /*list集合只有元素值 key list转换成Map集合的情况下，指定key--user对象的username属性，value user对象
         * map<String(username),User>
         * */
        Stream<User> stream = users.stream();
        Map<String, User> collect = stream.collect(Collectors.toMap(new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUsername();
            }
        }, new Function<User, User>() {
            @Override
            public User apply(User user) {
                return user;
            }
        }));
        collect.forEach((s, user) -> System.out.println(s + "," + user));
    }
}
