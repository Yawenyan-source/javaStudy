package com.wen.ext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 手写LRU缓存淘汰算法
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    //容量
    private int capacity;

    public LruCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public LruCache() {

    }

    /**
     * size() > capacity 清理头节点
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LruCache<String, String> lruCache = new LruCache<>(3);
        lruCache.put("a", "a");
        lruCache.put("b", "b");
        lruCache.put("c", "c");
        lruCache.put("d", "d");
        lruCache.forEach((s, s2) -> System.out.println("k=" + s + "，" + "v=" + s2));
    }

}
