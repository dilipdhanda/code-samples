package com.c0.aisera_keeper.Locks;
/*
Simplest way to make code thread-safe by using intrinsic locks (monitor locks).
- Locks an object or class monitor.
- Automatically releases the lock when exiting the block/method.
- Works at method or block level.
Pros: - Easiest to use.
- No explicit unlock needed.
- Great for simple critical sections.
Cons: - Less flexible (no tryLock, no fairness).
- Blocking — threads must wait; no interruption or timeout.
- Slightly slower than atomic operations due to kernel-level monitor entry/exit.

Situation	                                                 - Use
You only need to increment/decrement a counter	            ✅ AtomicInteger
You need fine-grained lock control, fairness, or conditions	✅ ReentrantLock
You just need simple mutual exclusion in a small section	  ✅ synchronized
 */
public class _Synchronized {
  private int count = 0;

  public synchronized void increment() {
    count++; // Only one thread executes this at a time
  }
  public void increment_2() {
    synchronized (this) {
      count++;
    }
  }
  public synchronized int getCount() {
    return count;
  }

}
