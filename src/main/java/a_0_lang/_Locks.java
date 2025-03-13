package a_0_lang;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock; // interface
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.atomic.AtomicInteger;

public class _Locks {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        _Locks l = new _Locks();
//        l.reEntrantLock();
//        l.readLock_ReentrantReadWriteLock(); // same ReentrantReadWriteLock class
//        l.writeLock_ReentrantReadWriteLock();// same ReentrantReadWriteLock class
//        l.incrementAtomicInteger(); // more efficient than synchronization which involves locking resources
//        l.semaphoreLock();
//        l.countDownLatch();
//        l.CyclicBarrier();
        l.executorService();
    }

    public void executorService(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                // task to execute
                System.out.println("Running task "); // TODO - how to pass i, its not accessible here
            });
        }
        executor.shutdown();
    }
    private final CyclicBarrier barrier = new CyclicBarrier(1); // TODO Wait hangs with 2 or more
    public void CyclicBarrier() throws InterruptedException, BrokenBarrierException {
        System.out.println("barrier - Parties Waiting: "+barrier.getNumberWaiting()+", Parties:"+barrier.getParties());
        System.out.println("doing - barrier reset - Parties Waiting: "+barrier.getNumberWaiting()
                +", Parties:"+barrier.getParties());
        barrier.reset(); // multiple resets do nothing
        /* Resets the barrier to its initial state. If any parties are currently waiting at the barrier,
        they will return with a BrokenBarrierException.
         */
        System.out.println("doing - barrier await - Parties Waiting: "+barrier.getNumberWaiting()
                +", Parties:"+barrier.getParties());
        barrier.await();
        /* Waits until all parties have invoked await on this barrier, or the specified waiting time elapses. */
    }

    private final CountDownLatch latch = new CountDownLatch(2);
    public void countDownLatch() throws InterruptedException {
        signalCompletion_CountDownLatch();
        waitForCompletion_CountDownLatch();
    }
    public void waitForCompletion_CountDownLatch() throws InterruptedException {
        System.out.println("trying await() - a blocking call with Latch count: "+latch.getCount());
        latch.await(); // Causes the current thread to wait until the latch has counted down to zero
    }
    public void signalCompletion_CountDownLatch() {
        System.out.println("ready to do stuff with Latch count: "+latch.getCount());
        latch.countDown(); // do this before doing stuff
        // do stuff 1
        System.out.println("stuff 1 done- Latch count: "+latch.getCount());
        latch.countDown();
        // do stuff 2
        System.out.println("stuff 2 done- Latch count: "+latch.getCount());
    }

    private final Semaphore semaphore = new Semaphore(5); // 5 permits allowed
    public void semaphoreLock() throws InterruptedException {
        System.out.println("at start: semaphore availablePermits: "+semaphore.availablePermits());
        int permits = 3;
        for (int i = 1; i <= permits; ++i) {
            System.out.println("getting  permit: "+i);
            semaphore.acquire();
            System.out.println("acquired permit: "+i);
        }
        semaphore.acquire();
        System.out.println("semaphore availablePermits: "+semaphore.availablePermits());
        /* Acquires a permit from this semaphore, blocking until one is available. Acquires a permit, if one is
        available and returns immediately, reducing the number of available permits by one
         */
        try {
            // synchronized code
        } finally {
            semaphore.release();
            semaphore.release(2);
            System.out.println("released (1+2) 3 semaphore permits");
            System.out.println("semaphore availablePermits: "+semaphore.availablePermits());
        }
    }

    private final AtomicInteger counter = new AtomicInteger(0);
    public void incrementAtomicInteger() {
        counter.incrementAndGet();

    }

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void readLock_ReentrantReadWriteLock() {
        readLock.lock();
        try {
            // read-only code
        } finally {
            readLock.unlock();
        }
    }

    public void writeLock_ReentrantReadWriteLock() {
        writeLock.lock();
        try {
            // write-only code
        } finally {
            writeLock.unlock();
        }
    }


    private final ReentrantLock lock = new ReentrantLock();
    public void reEntrantLock() {
        lock.lock(); // Blocking call, increases lock count to 1
        System.out.println("got lock, lock hold count: "+lock.getHoldCount());
        /* Acquires the lock if it is not held by another thread and returns immediately, setting the lock hold
        count to one. If the current thread already holds the lock then the hold count is incremented by one and
        the method returns immediately.
        If the lock is held by another thread then the current thread becomes disabled for thread scheduling
        purposes and lies dormant until the lock has been acquired, at which time the lock hold count is set to one.
         */
        try {
            // synchronized code
        } finally {
            lock.unlock(); // Lock released only if lock count reduces to zero
            System.out.println("unlock() reduced, lock hold count to: "+lock.getHoldCount());
            /*
            Attempts to release this lock. If the current thread is the holder of this lock then the hold count
            is decremented. If the hold count is now zero then the lock is released. If the current thread is not
            the holder of this lock then IllegalMonitorStateException is thrown.
             */
        }
    }

}
