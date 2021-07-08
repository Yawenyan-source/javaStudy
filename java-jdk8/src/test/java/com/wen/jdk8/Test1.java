package com.wen.jdk8;

import com.wen.jdk8.service.LamdbaService01;
import com.wen.jdk8.service.LamdbaService02;

public class Test1 {

    public static void main(String[] args) {

//      使用lambda表达式调用无参函数
        new LamdbaService01() {
            @Override
            public void get() {
                System.out.println("get");
            }
        }.get();
        //使用lambda方法体中只有一条语句的情况下，在这个时候我们不需要写{},也可以不需要要写return
        System.out.println("===========lamdba精简语法调用无参函数==============");
        ((LamdbaService01) () -> System.out.println("lamdba精简语法")).get();

        System.out.println("===========lamdba精简语法调用有参函数===============");
        System.out.println(((LamdbaService02) (i, j) -> i + "====" + j).get(1, 2));

        System.out.println("==========================");
        LamdbaService01 lamdbaService01 = () -> {
            System.out.println("使用lambda表达式调用方法");
        };
        lamdbaService01.get();
        System.out.println("==========================");
        //使用lambda表达式调用有参函数
        LamdbaService02 lamdbaService02 = new LamdbaService02() {
            @Override
            public String get(int i, int j) {
                return i + "====" + j;
            }
        };
        System.out.println(lamdbaService02.get(1, 2));
        System.out.println("============使用lambda表达式调用有参函数==============");
        LamdbaService02 lamdbaService021 = (i, j) -> {
            return i + "====" + j;
        };
        System.out.println(lamdbaService021.get(1, 2));
    }

}
