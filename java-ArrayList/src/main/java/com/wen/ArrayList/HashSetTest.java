package com.wen.ArrayList;

import java.util.HashSet;

/**
 * HashSet测试
 * HashSet无序
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> strings = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            strings.add("wen" + i);
        }
        strings.forEach((t) -> {
            System.out.println(t);
        });
    }
}
