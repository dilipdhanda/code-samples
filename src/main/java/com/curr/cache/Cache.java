package com.curr.cache;

import java.time.Duration;
import java.util.function.Function;

public interface Cache<K, V> extends AutoCloseable {
  V get(K key);
  void put(K key, V value);
  V remove(K key);
  void invalidateAll();
  int size();
  Stats stats();
  default V computeIfAbsent(K key, Function<K, V> loader) {
    V v = get(key);
    if (v != null) return v;
    v = loader.apply(key);
    put(key, v);
    return v;
  }
  record Stats(long hits, long misses, long evictions) {}
  @Override default void close() {}
}

