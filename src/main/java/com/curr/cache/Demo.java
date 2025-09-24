package com.curr.cache;

import java.time.Duration;

public class Demo {
  public static void main(String[] args) throws Exception {
    try (Cache<String, String> cache =
           LruTtlCache.withTtl(1000, Duration.ofMinutes(10), Duration.ofSeconds(30))) {

      cache.put("a", "alpha");
      System.out.println(cache.get("a")); // alpha

      String b = cache.computeIfAbsent("b", k -> "bravo");
      System.out.println(b); // bravo

      System.out.println(cache.stats()); // hits/misses/evictions snapshot
    }
  }
}

