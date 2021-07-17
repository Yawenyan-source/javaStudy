package com.wen.ext;

import java.util.Hashtable;

/**
 * 手写一个简单的ConcurrentHashMap
 *
 * @param <K>
 * @param <V>
 */
public class MyConcurrentHashMap<K, V> {
    private final Hashtable<K, V>[] segments;

    public MyConcurrentHashMap() {
        segments = new Hashtable[16];
    }

    public void MyConcurrentHashMapPut(K k, V v) {
        //第一次计算index 计算key存放哪个Hashtable
        int segmentIndex = k.hashCode() & (segments.length - 1);
        Hashtable<K, V> segment = segments[segmentIndex];
        if (segment == null) {
            //注意懒加载
            segment = new Hashtable<>();
        }
        segment.put(k, v);
        //将segment重新赋值到segments对应数组
        segments[segmentIndex] = segment;
    }

    public V MyConcurrentHashMapGet(K k) {
        int segmentIndex = k.hashCode() & (segments.length - 1);
        Hashtable<K, V> segment = segments[segmentIndex];
        if (segment != null) {
            return segment.get(k);
        }
        return null;
    }

    public static void main(String[] args) {
        MyConcurrentHashMap<String, String> myConcurrentHashMap = new MyConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            myConcurrentHashMap.MyConcurrentHashMapPut(i + "", i + "++");
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(myConcurrentHashMap.MyConcurrentHashMapGet(i + ""));
        }
    }
}
