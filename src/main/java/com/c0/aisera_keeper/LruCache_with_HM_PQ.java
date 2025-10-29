package com.c0.aisera_keeper;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;

/*
ram.gande@aisera.com - Aisera interview 10/16/25

Problem Statement
----------------------
Implement a thread-safe Least Recently Used (LRU) cache in Java. The cache should support the following operations:

put(String key, Object value): Insert or update the value for the given key. If the cache is at capacity, evict the least recently used entry before inserting the new one.
get(String key): Retrieve the value for the given key if it exists and is not expired. This operation should mark the key as recently used. Return null if the key does not exist.
remove(String key): Remove the entry for the given key if it exists.
size(): Return the current number of entries in the cache.

----------------------
Additional Requirements
----------------------

The cache has a fixed capacity (provided in the constructor).
The cache must be thread-safe: It should handle concurrent reads and writes correctly without data corruption or inconsistencies (e.g., using locks or concurrent data structures).
Optional TTL (Time-to-Live): Each entry can optionally have a TTL in milliseconds (provided as an additional parameter in put(String key, Object value, long ttlMillis)). If TTL is 0 or negative, the entry does not expire. Expired entries should be lazily removed during get or put operations (no background threads).
Handle edge cases: null keys/values, capacity of 0 or 1, concurrent access leading to evictions, expiration during access.
Aim for O(1) average time complexity for get, put, and remove operations.
 */
/**
 * Thread-safe LRU cache implemented with HashMap + PriorityQueue (min-heap).
 * Policies:
 *  - LRU is tracked by last-access time (nanoTime) via a heap. Updates are done by inserting
 *    a new heap node and leaving old ones to be lazily discarded when popped.
 *  - Optional TTL per entry; expiration is lazy (purged during get/put/evict).
 *
 * Complexity:
 *  - get/put/remove: O(1) hashmap ops + O(log n) heap inserts/pops when we touch the heap.
 *  - This is the best practical approach with PriorityQueue in Java (no decrease-key).
 *
 * Edge cases:
 *  - capacity <= 0: cache is effectively disabled.
 *  - null key -> NPE. null values allowed (change if you prefer).
 */
public class LruCache_with_HM_PQ {

  private static final class Entry {
    Object value;
    long expireAtNanos;  // <=0 means no expiry
    long lastAccessNanos;
    long version;        // incremented on each access/update to invalidate older heap nodes

    Entry(Object value, long ttlMillis, long now) {
      this.value = value;
      this.lastAccessNanos = now;
      setTtl(ttlMillis, now);
      this.version = 1L;
    }

    void setTtl(long ttlMillis, long now) {
      if (ttlMillis > 0) {
        this.expireAtNanos = now + ttlMillis * 1_000_000L;
      } else {
        this.expireAtNanos = 0L;
      }
    }

    boolean isExpired(long now) {
      return expireAtNanos > 0 && now >= expireAtNanos;
    }
  }

  /** Node stored in the heap. Multiple nodes may exist per key; only the one with the latest version is valid. */
  private static final class HeapNode implements Comparable<HeapNode> {
    final String key;
    final long lastAccessNanos; // priority: least-recently-used = smallest timestamp
    final long version;         // must match current Entry.version to be valid

    HeapNode(String key, long lastAccessNanos, long version) {
      this.key = key;
      this.lastAccessNanos = lastAccessNanos;
      this.version = version;
    }

    @Override
    public int compareTo(HeapNode o) {
      return Long.compare(this.lastAccessNanos, o.lastAccessNanos);
    }
  }

  private final int capacity;
  private final Map<String, Entry> map;
  private final PriorityQueue<HeapNode> heap;
  private final ReentrantLock lock = new ReentrantLock();

  public LruCache_with_HM_PQ(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>(Math.max(16, capacity * 2));
    this.heap = new PriorityQueue<>(Math.max(16, capacity * 2));
  }

  // ---------------- Public API ----------------

  public Object get(String key) {
    if (key == null) throw new NullPointerException("key");
    if (capacity <= 0) return null;

    lock.lock();
    try {
      // Clean top expired/stale nodes opportunistically
      pruneStaleOrExpired();

      Entry e = map.get(key);
      if (e == null) return null;

      long now = System.nanoTime();
      if (e.isExpired(now)) {
        // lazily remove
        map.remove(key);
        return null;
      }

      // Mark as recently used: bump version, update lastAccess, push heap node
      e.lastAccessNanos = now;
      e.version++;
      heap.offer(new HeapNode(key, e.lastAccessNanos, e.version));
      return e.value;
    } finally {
      lock.unlock();
    }
  }

  public void put(String key, Object value) {
    put(key, value, 0L);
  }

  /** Put with TTL (ms). ttlMillis <= 0 means no expiration. */
  public void put(String key, Object value, long ttlMillis) {
    if (key == null) throw new NullPointerException("key");
    if (capacity <= 0) return; // disabled

    lock.lock();
    try {
      long now = System.nanoTime();

      // Clean expired/stale nodes opportunistically
      pruneStaleOrExpired();

      Entry e = map.get(key);
      if (e != null) {
        if (e.isExpired(now)) {
          // Treat as brand new
          map.remove(key);
          e = null;
        }
      }

      if (e == null) {
        // New insert
        e = new Entry(value, ttlMillis, now);
        map.put(key, e);
        heap.offer(new HeapNode(key, e.lastAccessNanos, e.version));

        // Evict if over capacity
        evictIfNeeded(now);
      } else {
        // Update existing
        e.value = value;
        e.setTtl(ttlMillis, now);
        e.lastAccessNanos = now;
        e.version++;
        heap.offer(new HeapNode(key, e.lastAccessNanos, e.version));
        // Capacity unchanged, but we can still opportunistically evict
        evictIfNeeded(now);
      }
    } finally {
      lock.unlock();
    }
  }

  public void remove(String key) {
    if (key == null) throw new NullPointerException("key");
    if (capacity <= 0) return;

    lock.lock();
    try {
      // We don't remove heap nodes directly—lazy removal keeps complexity low
      map.remove(key);
      // Optional: prune a little to keep heap from growing too stale
      pruneStaleOrExpired();
    } finally {
      lock.unlock();
    }
  }

  public int size() {
    if (capacity <= 0) return 0;
    lock.lock();
    try {
      // size reflects current map entries (expired entries may linger until touched)
      return map.size();
    } finally {
      lock.unlock();
    }
  }

  // ---------------- Internals ----------------

  /** Pop heap until top corresponds to a live, non-expired map entry and versions match. */
  private void pruneStaleOrExpired() {
    long now = System.nanoTime();
    while (!heap.isEmpty()) {
      HeapNode top = heap.peek();
      Entry e = map.get(top.key);
      if (e == null) {
        heap.poll(); // key removed; discard node
        continue;
      }
      if (e.version != top.version) {
        heap.poll(); // stale node (older version)
        continue;
      }
      if (e.isExpired(now)) {
        // Remove entry and discard node
        map.remove(top.key);
        heap.poll();
        continue;
      }
      // Top is valid & non-expired
      break;
    }
  }

  private void evictIfNeeded(long now) {
    while (map.size() > capacity) {
      // Pop until we find a valid, non-expired LRU
      if (heap.isEmpty()) break; // defensive

      HeapNode top = heap.poll();
      Entry e = map.get(top.key);
      if (e == null) {
        continue; // already removed
      }
      if (e.version != top.version) {
        continue; // stale node
      }
      if (e.isExpired(now)) {
        map.remove(top.key); // drop expired
        continue;
      }
      // Evict the true LRU (valid match)
      map.remove(top.key);
      // Do not push anything else; eviction done for this iteration
    }
  }

  // ---------------- Demo ----------------

  public static void main(String[] args) throws InterruptedException {
    LruCache_with_HM_PQ cache = new LruCache_with_HM_PQ(2);

    cache.put("a", 1);
    cache.put("b", 2);
    System.out.println(cache.get("a")); // 1 (a becomes MRU)
    cache.put("c", 3);                  // evicts LRU ("b")
    System.out.println(cache.get("b")); // null
    System.out.println(cache.get("c")); // 3
    System.out.println(cache.size());   // 2

    cache.put("x", "ttl", 100); // 100 ms TTL
    Thread.sleep(150);
    System.out.println(cache.get("x")); // null (expired, lazily removed)

    // Concurrency note: all ops are protected by a single lock for correctness.
    // If you need higher read throughput, consider a ReadWriteLock; but keep in mind
    // heap mutations happen on most accesses due to LRU updates.
  }
}

