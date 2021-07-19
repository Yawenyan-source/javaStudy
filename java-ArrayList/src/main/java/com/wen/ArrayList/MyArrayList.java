package com.wen.ArrayList;

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
        elementData[size++] = t;
    }

    public T get(int index) {
        return (T) elementData[index];
    }

    public static void main(String[] args) {
        MyArrayList<String> stringMyArrayList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringMyArrayList.add("wen" + i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(stringMyArrayList.get(i));
        }
    }
}
