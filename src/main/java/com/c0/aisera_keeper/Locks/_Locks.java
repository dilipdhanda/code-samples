package com.c0.aisera_keeper.Locks;

/*
Java gives you a whole toolbox beyond AtomicInteger, ReentrantLock, and synchronized. Here are the most useful alternatives, when they shine, and quick notes:
Read/Write & Versioned Locks
- ReentrantReadWriteLock - Many readers, single writer. Great when reads ≫ writes.
- StampedLock - Read, write, and optimistic read (lock-free read validation). Often faster than
  ReentrantReadWriteLock in read-heavy paths. Not reentrant; be careful with unlock.
High-contention Counters / Accumulators
- LongAdder / LongAccumulator - Outperform AtomicLong/AtomicInteger under contention by striping
  across cells and combining later (ideal for metrics, hit counters).
Semaphores, Latches, Barriers, Phasers
- Semaphore - Limit concurrent access to a resource pool (N permits).
- CountDownLatch - One-shot gate: wait until N events/tasks complete.
- CyclicBarrier - Reusable rendezvous point for N threads per phase.
- Phaser - More flexible, dynamic parties; phased coordination.
Conditions & Parking
- Condition (from ReentrantLock) - Fine-grained wait/notify with separate condition queues.
- LockSupport - Low-level park/unpark primitives (building blocks for custom synchronizers).
Concurrent Collections (often better than explicit locks)
- ConcurrentHashMap, ConcurrentSkipListMap/Set (sorted),
- CopyOnWriteArrayList/Set (iteration-heavy, write-light),
- ConcurrentLinkedQueue, LinkedTransferQueue, ArrayBlockingQueue, LinkedBlockingQueue,
  SynchronousQueue, PriorityBlockingQueue, DelayQueue. Prefer these to home-rolled locking
  around standard collections.
Atomics & VarHandles
- AtomicReference, AtomicStampedReference, AtomicMarkableReference - CAS on objects; stamped/markable
  help avoid ABA problems.
- AtomicIntegerFieldUpdater / AtomicReferenceFieldUpdater - CAS on volatile fields without wrapper objects.
- VarHandle (Java 9+) - Modern, flexible, low-level memory & atomic ops (successor to most Unsafe use cases).
Framework for Custom Synchronizers
- AbstractQueuedSynchronizer (AQS)
- Build your own locks/latches; foundation of many JUC classes.
Striped / Sharded Locking
- Guava Striped<Lock/ReadWriteLock> (external lib) - Many lightweight locks hashed by key—great
  for maps/caches to cut contention.

java.lang
 └── synchronized (language-level)

java.util.concurrent.locks
 └── ReentrantLock (built on AbstractQueuedSynchronizer)

java.util.concurrent.atomic
 └── AtomicInteger, AtomicReference (built on CAS)

java.util.concurrent
 └── Higher-level frameworks built using the above:
      ├── Executors, ThreadPoolExecutor
      ├── BlockingQueue (ArrayBlockingQueue, LinkedBlockingQueue, etc.)
      ├── ConcurrentHashMap, CopyOnWriteArrayList
      └── Synchronizers (CountDownLatch, Semaphore, Phas
 */
public class _Locks {
}
