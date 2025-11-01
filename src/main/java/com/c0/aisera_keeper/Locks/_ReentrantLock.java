package com.c0.aisera_keeper.Locks;
import java.util.concurrent.locks.ReentrantLock;

/*
An explicit lock providing more control than synchronized.
- “Reentrant” means the same thread can acquire the lock multiple times without deadlocking.
- Supports fairness policies, tryLock(), and interruptible lock acquisition.
- Must be manually unlocked, unlike synchronized.
Pros: - More flexible than synchronized.
- Useful for advanced concurrency frameworks.
Cons: Must manually unlock (risk of forgetting → deadlocks).
- Slightly more verbose code.

tryLock()	Attempt to acquire lock without blocking
lockInterruptibly()	Can be interrupted while waiting
isHeldByCurrentThread()	Checks ownership
newCondition()	Works with Condition for advanced signaling (like wait/notify replacement)
 */
public class _ReentrantLock {
  private int count = 0;
  private final ReentrantLock lock = new ReentrantLock();

  public void increment() {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock(); // Always unlock in finally
    }
  }

  public int getCount() {
    return count;
  }
}
