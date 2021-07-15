package com.wen.ext;

/**
 * 自定义实现hashMap 基于数组和链表
 * @param <K>
 * @param <V>
 */
public class ExtHashMap<K, V> {
    private Entry[] entries = new Entry[100];

    class Entry<K, V> {
        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public void put(K k, V v) {
        //数组+链表实现hash算法
        int index = k == null ? 0 : k.hashCode() % entries.length;
        Entry oldEntry = entries[index];
        if (oldEntry == null) {
            //key 没有发生hash碰撞
            entries[index] = new Entry<>(k, v);
        } else {
            oldEntry.next = new Entry<>(k, v);
        }
    }

    public V get(K k) {
        int index = k == null ? 0 : k.hashCode() % entries.length;
        for (Entry oldEntry = entries[index]; oldEntry != null; oldEntry = oldEntry.next) {
            if ((k == null && oldEntry.k == null) || oldEntry.k.equals(k)) {
                return (V) oldEntry.v;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ExtHashMap<Object, String> hashMap = new ExtHashMap<>();
        hashMap.put("a", "a");
        hashMap.put(97, "97");
        hashMap.put(null, "wenNull");
        //a的hashcode和97的hashcode相同都是97
        System.out.println(hashMap.get("a"));
        System.out.println(hashMap.get(97));
        System.out.println(hashMap.get(null));
    }
}
