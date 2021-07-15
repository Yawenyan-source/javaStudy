package com.wen.ext;

import java.util.LinkedHashMap;

/**
 * linkedHashMap 使用
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("a", "a");
        linkedHashMap.put("b", "b");
        linkedHashMap.put("c", "c");
        linkedHashMap.get("b");
        linkedHashMap.forEach((k, v) -> {
            System.out.println("k=" + k + "," + "v=" + v);
        });
    }
}
