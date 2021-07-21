package com.wen.ArrayList;

import java.util.Arrays;

/**
 * 手动实现ArrayList
 */
public class MyArrayList<T> {
    Object[] elementData;
    /**
     * 当前我们数组存放的个数
     */
    private int size;
    /**
     * elementData 默认初始容量为10
     */
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
    }

    /**
     * 新增元素
     *
     * @param t
     */
    public void add(T t) {
        //默认的初始化
        if (elementData == null) {
            elementData = new Object[DEFAULT_CAPACITY];
        }
        //判断是否需要扩容
        if ((size + 1) > elementData.length) {
            //原来的容量
            int oldCapacity = elementData.length;
            //现在的容量
            int nowCapacity = oldCapacity + (oldCapacity >> 1);
            //将原来的数组扩容到新数组，扩容1.5倍
            elementData = Arrays.copyOf(elementData, nowCapacity);
        }
        elementData[size++] = t;
    }

    public T get(int index) {
        return (T) elementData[index];
    }

    public static void main(String[] args) {
        MyArrayList<String> stringMyArrayList = new MyArrayList<>();
        for (int i = 0; i < 200; i++) {
            stringMyArrayList.add("wen" + i);
        }
        for (int i = 0; i < stringMyArrayList.size; i++) {
            System.out.println(stringMyArrayList.get(i));
        }
    }
}
