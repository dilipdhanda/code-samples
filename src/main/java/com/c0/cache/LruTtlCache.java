package com.c0.cache;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class LruTtlCache<K, V> implements Cache<K, V> {
  private final ConcurrentHashMap<K, Node<K,V>> map = new ConcurrentHashMap<>();
  private final ReentrantLock lock = new ReentrantLock();   // protects the LRU list & size

  private Node<K,V> head, tail; // most-recent at head
  private int size = 0;

  private final int maxEntries;
  private final long ttlNanos; // 0 = no TTL
  private final ScheduledExecutorService cleaner; // optional

  private final AtomicLong hits = new AtomicLong();
  private final AtomicLong misses = new AtomicLong();
  private final AtomicLong evictions = new AtomicLong();

  public static <K,V> LruTtlCache<K,V> withCapacity(int maxEntries) {
    return new LruTtlCache<>(maxEntries, Duration.ZERO, null);
  }

  public static <K,V> LruTtlCache<K,V> withTtl(int maxEntries, Duration ttl, Duration cleanupInterval) {
    return new LruTtlCache<>(maxEntries, ttl, cleanupInterval);
  }

  private LruTtlCache(int maxEntries, Duration ttl, Duration cleanupInterval) {
    if (maxEntries <= 0) throw new IllegalArgumentException("maxEntries must be > 0");
    this.maxEntries = maxEntries;
    this.ttlNanos = (ttl == null ? 0 : Math.max(0, ttl.toNanos()));

    if (ttlNanos > 0 && cleanupInterval != null && !cleanupInterval.isZero() && !cleanupInterval.isNegative()) {
      this.cleaner = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "cache-cleaner");
        t.setDaemon(true);
        return t;
      });
      cleaner.scheduleAtFixedRate(this::cleanUpExpired, cleanupInterval.toMillis(), cleanupInterval.toMillis(), TimeUnit.MILLISECONDS);
    } else {
      this.cleaner = null;
    }
  }

  @Override
  public V get(K key) {
    if (key == null) return null;
    Node<K,V> n = map.get(key);
    if (n == null) { misses.incrementAndGet(); return null; }

    if (isExpired(n)) {
      removeInternal(n); // expire-on-access
      misses.incrementAndGet();
      return null;
    }

    moveToHead(n);
    hits.incrementAndGet();
    return n.value;
  }

  @Override
  public void put(K key, V value) {
    Objects.requireNonNull(key, "key");
    Node<K,V> existing = map.get(key);
    final long exp = expiryTimestamp();

    if (existing != null) {
      existing.value = value;
      existing.expireAt = exp;
      moveToHead(existing);
      return;
    }

    Node<K,V> n = new Node<>(key, value, exp);
    Node<K,V> prev = map.putIfAbsent(key, n);
    if (prev != null) {
      prev.value = value;
      prev.expireAt = exp;
      moveToHead(prev);
      return;
    }

    // new node – link & evict if needed
    lock.lock();
    try {
      linkAtHead(n);
      size++;
      while (size > maxEntries) {
        evictTail();
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public V remove(K key) {
    if (key == null) return null;
    Node<K,V> n = map.remove(key);
    if (n == null) return null;
    V old = n.value;
    removeFromList(n);
    return old;
  }

  @Override
  public void invalidateAll() {
    lock.lock();
    try {
      map.clear();
      head = tail = null;
      size = 0;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() { return map.size(); }

  @Override
  public Stats stats() {
    return new Stats(hits.get(), misses.get(), evictions.get());
  }

  @Override
  public void close() {
    if (cleaner != null) cleaner.shutdownNow();
  }

  // ----- internals -----

  private long expiryTimestamp() {
    return ttlNanos == 0 ? Long.MAX_VALUE : (System.nanoTime() + ttlNanos);
  }

  private boolean isExpired(Node<K,V> n) {
    return ttlNanos != 0 && System.nanoTime() - n.expireAt >= 0;
  }

  private void moveToHead(Node<K,V> n) {
    lock.lock();
    try {
      if (n == head) return;
      // unlink
      if (n.prev != null) n.prev.next = n.next;
      if (n.next != null) n.next.prev = n.prev;
      if (n == tail) tail = n.prev;

      // link at head
      n.prev = null;
      n.next = head;
      if (head != null) head.prev = n;
      head = n;
      if (tail == null) tail = n;
    } finally {
      lock.unlock();
    }
  }

  private void linkAtHead(Node<K,V> n) {
    n.prev = null;
    n.next = head;
    if (head != null) head.prev = n;
    head = n;
    if (tail == null) tail = n;
  }

  private void evictTail() {
    if (tail == null) return;
    Node<K,V> evict = tail;
    // unlink tail
    if (evict.prev != null) {
      evict.prev.next = null;
      tail = evict.prev;
    } else {
      head = tail = null;
    }
    size--;
    // remove from map
    map.remove(evict.key, evict);
    evictions.incrementAndGet();
  }

  private void removeFromList(Node<K,V> n) {
    lock.lock();
    try {
      if (n.prev != null) n.prev.next = n.next;
      if (n.next != null) n.next.prev = n.prev;
      if (n == head) head = n.next;
      if (n == tail) tail = n.prev;
      // adjust size if this node was still counted
      // (it is only counted if still present in map; best-effort)
      size = Math.max(0, size - 1);
    } finally {
      lock.unlock();
    }
  }

  private void removeInternal(Node<K,V> n) {
    if (map.remove(n.key, n)) removeFromList(n);
  }

  private void cleanUpExpired() {
    if (ttlNanos == 0) return;
    for (Node<K,V> n : map.values()) {
      if (isExpired(n)) removeInternal(n);
    }
  }

  private static final class Node<K,V> {
    final K key;
    volatile V value;
    volatile long expireAt;
    Node<K,V> prev, next;
    Node(K key, V value, long expireAt) {
      this.key = key; this.value = value; this.expireAt = expireAt;
    }
  }
}

