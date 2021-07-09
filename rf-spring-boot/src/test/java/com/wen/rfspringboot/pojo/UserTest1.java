package com.wen.rfspringboot.pojo;


class UserTest1 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        /*
         * 反射机制，动态获取class 信息
         * */
        //1、获取class User.class
        Class<User> userClass = User.class;
        User user = userClass.newInstance();
        System.out.println(user);
        //2、Class.forName("")
        Class<?> aClass = Class.forName("com.wen.rfspringboot.pojo.User");
        System.out.println(aClass == userClass);
        //3、new User("").class
        User user1 = new User();
        Class<? extends User> aClass1 = user1.getClass();
        System.out.println((aClass1 == userClass));
    }
}