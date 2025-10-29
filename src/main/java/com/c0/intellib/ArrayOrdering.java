package com.c0.intellib;

import java.util.Arrays;
import java.util.Random;

public class ArrayOrdering {
  final static int ARRSIZE = 200000000;

  public static void main(String[] args) {
    int[] arr = new int[ARRSIZE];   // 200,000,000 integers (~800MB)
    Random r = new Random();
    for (int count = 0; count < ARRSIZE; count++)
      arr[count] = r.nextInt(100 - 1 + 1) + 1;
// LINE 1
    // Arrays.parallelSort(), much faster , Java 8	Uses a multi-threaded, parallel merge sort
    // internally (via ForkJoinPool.commonPool()
    Arrays.parallelSort(arr); // much faster, still takes quite a few seconds to execute
    // Arrays.sort()	Java 1.2	Uses a single-threaded, optimized Dual-Pivot Quicksort (for primitives)
    // Arrays.sort(arr); // much slower
    System.out.println(arr.length);
  }
}
