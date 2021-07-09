package com.wen.rfspringboot.pojo;

public class User {
    private String username;
    private int age;

    public User() {
        System.out.println("调用无参构造");
    }

    public User(String username, int age) {
        System.out.println("调用有参构造");
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
