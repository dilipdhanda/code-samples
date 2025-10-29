package com.c0.intellib;

import java.util.concurrent.atomic.AtomicInteger;

public class _AtomicInteger {
  private static AtomicInteger count = new AtomicInteger(0);

  public static void main(String[] args) {
    // Increment count atomically from multiple threads
    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        int newVal = count.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + " -> " + newVal);
      }).start();
    }
  }
}
