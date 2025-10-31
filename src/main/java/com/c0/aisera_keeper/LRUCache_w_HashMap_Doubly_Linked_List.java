package com.c0.aisera_keeper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe LRU Cache with optional TTL per entry.
 *
 * Features:
 *  - O(1) get/put/remove via HashMap + doubly linked list
 *  - Thread-safe using ReentrantLock
 *  - Optional TTL expiration (lazy cleanup)
 *  - Capacity enforcement with LRU eviction

 Why two PriorityQueues isn’t great:
 - Java’s PriorityQueue doesn’t support decrease-key or efficient priority updates. Every get() must
   update “recency,” which means reheapifying.
 - Removing/adjusting an arbitrary element in a heap is O(n) unless you do “lazy deletion” (keep duplicates and
    ignore stale ones later).
 - Two heaps (one “newest”, one “oldest”) don’t buy you consistency—keeping them synchronized is harder than it sounds.

 */
public final class LRUCache_w_HashMap_Doubly_Linked_List<K, V> {

  private final int capacity;
  private final Map<K, Node<K, V>> map;
  private final ReentrantLock lock = new ReentrantLock();

  // Create doubly linked list with Sentinel nodes (just markers, no data, to keep code readable
  // and reducing/simplifying null/boundary error
  private final Node<K, V> head = new Node<>(null, null, NO_EXPIRY);
  private final Node<K, V> tail = new Node<>(null, null, NO_EXPIRY);

  public LRUCache_w_HashMap_Doubly_Linked_List(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("capacity must be >= 0");
    this.capacity = capacity;
    this.map = new HashMap<>(Math.max(16, capacity * 2));
    head.next = tail;
    tail.prev = head;
  }

  private static final long NO_EXPIRY = Long.MAX_VALUE;

  // Doubly Linked List
  private static final class Node<K, V> {
    final K key;
    V value;
    long expiresAtMillis;
    Node<K, V> prev;
    Node<K, V> next;

    Node(K key, V value, long expiresAtMillis) { // Constructor
      this.key = key;
      this.value = value;
      this.expiresAtMillis = expiresAtMillis;
    }
  }

  public void put(K key, V value) { put(key, value, 0L); }

  public void put(K key, V value, long ttlMillis) {
    Objects.requireNonNull(key, "key");
    Objects.requireNonNull(value, "value");

    if (capacity == 0) return; // no-op cache

    long now = System.currentTimeMillis();
    long expiresAt = ttlMillis > 0 ? now + ttlMillis : NO_EXPIRY;

    lock.lock();
    try {
      Node<K, V> node = map.get(key);
      if (node != null) {
        if (isExpired(node, now)) {
          unlink(node);
          map.remove(key);
          node = null;
        } else {
          node.value = value;
          node.expiresAtMillis = expiresAt;
          moveToFront(node);
          return;
        }
      }

      if (map.size() == capacity) {
        Node<K, V> lru = tail.prev;
        if (lru != head) {
          unlink(lru);
          map.remove(lru.key);
        }
      }

      Node<K, V> fresh = new Node<>(key, value, expiresAt);
      insertAtFront(fresh);
      map.put(key, fresh);

      Node<K, V> maybeExpiredLRU = tail.prev;
      if (maybeExpiredLRU != head && isExpired(maybeExpiredLRU, now)) {
        unlink(maybeExpiredLRU);
        map.remove(maybeExpiredLRU.key);
      }
    } finally {
      lock.unlock();
    }
  }

  public V get(K key) {
    Objects.requireNonNull(key, "key");
    long now = System.currentTimeMillis();

    lock.lock();
    try {
      Node<K, V> node = map.get(key);
      if (node == null) return null;
      if (isExpired(node, now)) {
        unlink(node);
        map.remove(key);
        return null;
      }
      moveToFront(node);
      return node.value;
    } finally {
      lock.unlock();
    }
  }

  public V remove(K key) {
    Objects.requireNonNull(key, "key");
    lock.lock();
    try {
      Node<K, V> node = map.remove(key);
      if (node == null) return null;
      unlink(node);
      return node.value;
    } finally {
      lock.unlock();
    }
  }

  public int size() {
    lock.lock();
    try { return map.size(); }
    finally { lock.unlock(); }
  }

  // Doubly linked list helpers (O(1))
  private void insertAtFront(Node<K, V> node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
  }

  private void unlink(Node<K, V> node) {
    Node<K, V> p = node.prev;
    Node<K, V> n = node.next;
    if (p != null) p.next = n;
    if (n != null) n.prev = p;
    node.prev = node.next = null;
  }

  private void moveToFront(Node<K, V> node) {
    unlink(node);
    insertAtFront(node);
  }

  private boolean isExpired(Node<K, V> node, long now) {
    return node.expiresAtMillis != NO_EXPIRY && now >= node.expiresAtMillis;
  }

  // ---------------------- Demo Section ----------------------
  public static void main(String[] args) throws InterruptedException {
    LRUCache_w_HashMap_Doubly_Linked_List<String, Object> cache = new LRUCache_w_HashMap_Doubly_Linked_List<>(2);

    cache.put("A", 1);
    cache.put("B", 2);
    System.out.println("Cache size after A,B: " + cache.size()); // 2

    System.out.println("Get A: " + cache.get("A")); // 1 (A now MRU)
    cache.put("C", 3); // Evicts LRU (B)
    System.out.println("Get B (evicted): " + cache.get("B")); // null
    System.out.println("Get A (still there): " + cache.get("A")); // 1
    System.out.println("Get C: " + cache.get("C")); // 3

    // TTL demo
    cache.put("X", 100, 1000); // expires in 1 sec
    System.out.println("Get X right away: " + cache.get("X")); // 100
    Thread.sleep(1200);
    System.out.println("Get X after expiry: " + cache.get("X")); // null

    // Remove test
    cache.put("Y", 200);
    System.out.println("Remove Y: " + cache.remove("Y")); // 200
    System.out.println("Get Y after remove: " + cache.get("Y")); // null

    System.out.println("Final cache size: " + cache.size());
  }
}

