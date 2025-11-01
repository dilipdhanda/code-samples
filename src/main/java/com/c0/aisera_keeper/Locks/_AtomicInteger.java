package com.c0.aisera_keeper.Locks;

import java.util.concurrent.atomic.AtomicInteger;
/*
Lightweight, lock-free thread-safe integer operations
- Works without using locks — uses CAS (Compare-And-Swap) at the CPU level.
- Ideal for counters or accumulators shared by multiple threads.
- Non-blocking — threads don’t wait; failed updates simply retry.
- Extremely fast under low to medium contention.
- No thread blocking or context switching.
 */
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
