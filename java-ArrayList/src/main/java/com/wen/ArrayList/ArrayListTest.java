package com.wen.ArrayList;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objects.add("WEN" + i);
        }
        objects.forEach(o -> {
            objects.add(o);
            System.out.println(o);
        });
    }
}
