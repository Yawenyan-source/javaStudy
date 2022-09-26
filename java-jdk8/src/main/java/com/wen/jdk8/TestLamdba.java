package com.wen.jdk8;

import com.wen.jdk8.pojo.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class TestLamdba {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("yyw");
        strings.add("gsl");
        strings.add("yywagsl");
        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        strings.forEach(s -> {
            System.out.println(s);
        });

        System.out.println("====================");
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("yyw", 13));
        userList.add(new User("ywy", 14));
        userList.add(new User("wyy", 1));
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        userList.forEach((u) -> {
            System.out.println(u.toString());
        });
        System.out.println("=============简写==============");
        userList.sort((Comparator.comparingInt(User::getAge)));

        userList.forEach((u) -> {
            System.out.println(u.toString());
        });
    }
}
