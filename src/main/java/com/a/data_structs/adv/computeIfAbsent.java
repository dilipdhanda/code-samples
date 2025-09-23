package com.a.data_structs.adv;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.SECONDS;

class computeIfAbsent {
  public static void main(String[] args) throws Exception {
    int threads = 16;
    new computeIfAbsent().demoNaivePut(threads);
    new computeIfAbsent().demoPutIfAbsent(threads);
    new computeIfAbsent().demoComputeIfAbsent(threads);
  }

  class Expensive {
    static AtomicInteger computeCalls = new AtomicInteger(0);
    static String load(String id) {
      // Pretend this is expensive (DB, network, etc.)
      computeCalls.incrementAndGet();
      try { Thread.sleep(50); } catch (InterruptedException ignored) {}
      return "ValueFor:" + id;
    }
  }

  void demoComputeIfAbsent(int threads) throws Exception {
    var map = new ConcurrentHashMap<String, String>();
    var pool = Executors.newFixedThreadPool(threads);
    Expensive.computeCalls.set(0);
    var key = "user:42";
    var start = new CountDownLatch(1);
    var done = new CountDownLatch(threads);

    for (int i = 0; i < threads; i++) {
      pool.submit(() -> {
        try {
          start.await();
          // Only one thread will actually run load(), others will wait and reuse
          map.computeIfAbsent(key, Expensive::load);
        } catch (InterruptedException ignored) {}
        finally { done.countDown(); }
      });
    }

    start.countDown();
    done.await(5, SECONDS);
    pool.shutdown();

    System.out.println("[computeIfAbsent] compute calls = " + Expensive.computeCalls.get());
    System.out.println("[computeIfAbsent] map.get = " + map.get(key));
  }

  void demoPutIfAbsent(int threads) throws Exception {
    var map = new ConcurrentHashMap<String, String>();
    var pool = Executors.newFixedThreadPool(threads);
    Expensive.computeCalls.set(0);
    var key = "user:42";
    var start = new CountDownLatch(1);
    var done = new CountDownLatch(threads);

    for (int i = 0; i < threads; i++) {
      pool.submit(() -> {
        try {
          start.await();
          String candidate = Expensive.load(key);      // every thread computes eagerly
          map.putIfAbsent(key, candidate);             // only first insert wins
        } catch (InterruptedException ignored) {}
        finally { done.countDown(); }
      });
    }

    start.countDown();
    done.await(5, SECONDS);
    pool.shutdown();

    System.out.println("[putIfAbsent] compute calls = " + Expensive.computeCalls.get());
    System.out.println("[putIfAbsent] map.get = " + map.get(key));
  }

  void demoNaivePut(int threads) throws Exception {
    var map = new ConcurrentHashMap<String, String>();
    var pool = Executors.newFixedThreadPool(threads);
    Expensive.computeCalls.set(0);
    var key = "user:42";
    var start = new CountDownLatch(1);
    var done = new CountDownLatch(threads);

    for (int i = 0; i < threads; i++) {
      pool.submit(() -> {
        try {
          start.await();
          if (!map.containsKey(key)) {                 // not atomic with put()
            String v = Expensive.load(key);            // multiple threads can compute
            map.put(key, v);                           // last writer wins
          }
        } catch (InterruptedException ignored) {}
        finally { done.countDown(); }
      });
    }

    start.countDown();
    done.await(5, SECONDS);
    pool.shutdown();

    System.out.println("[Naive put] compute calls = " + Expensive.computeCalls.get());
    System.out.println("[Naive put] map.get = " + map.get(key));
  }

}

