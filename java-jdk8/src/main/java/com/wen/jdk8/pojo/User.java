package com.wen.jdk8.pojo;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class User {
    private String username;
    private Integer age;

    public User() {
    }

    //public String say(SayHello sayHello) {
    //    return sayHello.sayHello(this.username);
    //}

    public String say(UnaryOperator<String> sayHello) {
        return sayHello.apply(this.username);
    }
    public String say(Function<String,String> sayHello) {
        return sayHello.apply(this.username);
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
