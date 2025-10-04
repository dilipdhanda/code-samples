package com.c7.a_1_main;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private Map<K, Node> cache;
    private Node head;
    private Node tail;
    private int maxSize;

    private class Node {
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int maxSize) {
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
        this.maxSize = maxSize;
    }

    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node == null) {
            node = new Node(key, value);
            cache.put(key, node);
            addNode(node);
            if (cache.size() > maxSize) {
                removeTail();
            }
        } else {
            node.value = value;
            moveNodeToHead(node);
        }
    }

    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveNodeToHead(node);
        return node.value;
    }

    private void addNode(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void removeNode(Node node) {
        if (node == head) {
            head = node.next;
        } else if (node == tail) {
            tail = node.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    private void moveNodeToHead(Node node) {
        if (node == head) {
            return;
        }
        removeNode(node);
        addNode(node);
    }

    private void removeTail() {
        cache.remove(tail.key);
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    public static void main(String[] args){
        LRUCache<Integer, String> ca = new LRUCache<>(3);
        ca.put(3,"dilip");
        ca.put(4,"pradeep");
        ca.put(3,"sundeep");
    }
}