package com.wen.ArrayList;

/**
 * 简单实现一个LinkedList
 */
public class MyLinkedList<E> {
    /**
     * 记录当前链表的头节点
     * 为了后期便利能够找到从哪里开始
     */
    transient Node<E> first;
    /**
     * 记录当前链表的尾节点
     */
    transient Node<E> last;

    transient int size;

    public void add(E e) {
        //获取当前尾节点
        Node<E> l = last;
        //采用尾插法
        Node<E> newNode = new Node<>(e, null, l);
        last = newNode;
        if (l == null) {
            //如果l为空，表示第一次新增node节点
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public Node<E> getNode(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public E get(int index) {
        return getNode(index).item;
    }

    /**
     * 移除一个元素
     */
    public boolean remove(int index) {
        Node<E> delNode = getNode(index);
        //判断当前节点是否为空
        if (delNode == null) {
            return false;
        }
        //当前节点的上一个节点
        Node<E> prev = delNode.prev;
        //当前节点的下一个节点
        Node<E> next = delNode.next;
        //如果当前节点的上一个节点为空，代表为第一个节点.
        if (prev == null) {
            //把下一个节点的上一个节点置位null，即为第一个节点
            first = next;
        } else {
            prev.next = next;
            delNode.prev = null; //告诉gc清理垃圾
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            delNode.next = null;//告诉gc清理垃圾
        }
        delNode.item = null;
        size--;
        return true;
    }

    /**
     * 双向链表结构
     *
     * @param <E>
     */
    private static class Node<E> {
        //元素
        E item;
        //下一个节点
        Node<E> next;
        //上一个节点
        Node<E> prev;

        public Node(E item, Node<E> next, Node<E> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> stringMyLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            stringMyLinkedList.add("wen0" + i);
        }
        stringMyLinkedList.remove(1);
        stringMyLinkedList.remove(3);
        stringMyLinkedList.remove(2);
        for (int i = 0; i < stringMyLinkedList.size; i++) {
            System.out.println(stringMyLinkedList.get(i));
        }
    }
}
