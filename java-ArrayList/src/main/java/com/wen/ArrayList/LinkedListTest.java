package com.wen.ArrayList;

import java.util.LinkedList;

/**
 * LinkedList测试
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("WEN" + i);
        }
        strings.forEach((s) -> {
            System.out.println(s);
        });
    }
}
