package com.wen.rfspringboot.pojo;

public class User {
    private String username;
    private Integer age;

    public User() {
        System.out.println("调用无参构造函数");
    }

    public User(String username, Integer age) {
        System.out.println("调用有参构造函数");
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
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    private void wen() {
        System.out.println("hello wen");
    }

    private Integer sum(Integer a, Integer b) {
        return a + b;
    }
}
