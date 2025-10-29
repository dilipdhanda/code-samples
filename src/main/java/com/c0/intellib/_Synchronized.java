package com.c0.intellib;

public class _Synchronized {

  class voterList {
    static private int voters = 0;
    int totalVoters;
    private static synchronized int Counter() {
      return ++voters;
    }
    // Each thread will get a unique number. - I answered correct
    public void getTotalVoterCount() {
      totalVoters = Counter();
    }
  }

  class productDispatch {
    private int dispatchID;
    private Integer orderID;

    public void dispatcher(double ticketID) {
      final Double dispTicket = ticketID;
      /*
      Let’s think logically:
      dispTicket - is a new local Double — each call to dispatcher() creates a fresh one,
          so synchronizing on it is useless (no two threads will ever share it).
      this - ensures only one thread per productDispatch object can execute the block — a common pattern.
      orderID - could make sense if multiple threads dispatch based on the same order.
      productDispatch.class - would block all threads globally, even across different objects
          — usually too restrictive.

      Answer: synchronized(this) is the most appropriate missing statement. (I answered wrong)
      It locks per object instance, ensuring thread-safe access to shared instance state
       */
      synchronized (this) { // (/*missing statement*/) is the question
        System.out.print("Dispatched");
      }
    }
  }
}
