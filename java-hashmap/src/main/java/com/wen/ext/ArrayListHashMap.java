package com.wen.ext;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ArrayList实现HashMap
 */
public class ArrayListHashMap<K, V> {
    private List<Entry<K, V>> listEntries = new ArrayList<>();

    class Entry<K, V> {
        K k;
        V v;

        public Entry(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public void put(K k, V v) {
        listEntries.add(new Entry<>(k, v));
    }

    public V get(K k) {
        for (Entry<K, V> listEntry : listEntries) {
            if (listEntry.k.equals(k)) {
                return listEntry.v;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayListHashMap<Object, String> arrayListHashMap = new ArrayListHashMap<Object, String>();
        arrayListHashMap.put("a", "a");
        arrayListHashMap.put(97, "97");
        System.out.println(arrayListHashMap.get("a"));
        System.out.println(arrayListHashMap.get(97));
    }
}
