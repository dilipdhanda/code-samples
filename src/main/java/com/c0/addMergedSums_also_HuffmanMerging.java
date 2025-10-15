package com.c0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class addMergedSums_also_HuffmanMerging {
  public static void main(String[] args) {
    // Aerolens Intuit Interview 10/07/25 -
    // By the same guy who interviewed the girl in the good mp4 you watched
    int arr[] = {5,3,1,2};
    // lessor sum
    // 5 + 3 = 8, 8 + 1 = 9, 9 + 2 = 11
    // 8 + 9 + 11 = 28
    // 5 + 1 = 6, 6 + 2 = 8, 8 + 3 = 11,
    // 6 + 8 + 11 = 25
    // create all combinations like, pick any two, then randomly pick others left -
    // get all combinations for the first 2 - 5,3 -
    // first pick all pick-2 combinations - 5,3 - 5,1 - 5,2 - 3,1 - 1,2
    // for each pick-2 get all permutations for the rest of the elements left
    // 5,3 - 1 - 2 - get sum - put in arraylist
    // 5,3 - 2 - 1 - write fn to get sum(intial_sum_of_two8, arr[1,2]) = 11
    // 5,1 - 3 - 2
    // 5,1 - 2 - 3
    // create combinations to get two dim arr[][] like 5,3 - 5,1 - 5,2 - 3,1 - 1,2
    // for each pick-2 combination - get all permutations
    // use sum() to get sum and append to arraylist, finally find the least sum.
    // pick lowest 2 - then add other with sum()

    // You gave (above) a is very complicated, and there was a simple solutions for the problem.
    // Sort the array, then add first two, add to third, increase sum and keep going on for rest of elements
    ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(5,3,1,2));
    Collections.sort(l1);
    // 1 + 2 = 3, 3 + 3 = 6, 6 + 5 = 11 - 20
    Integer sum = l1.get(0) + l1.get(1);
    Integer n = sum;
    for(int i = 2; i < l1.size(); i++){
      n = n + l1.get(i);
      sum = sum + n;
    }
    System.out.println(sum);
    // 10/07/25 - Aerolens Intuit problem above is different, but result is same with Huffman merging
    System.out.println(minimalAccumulatedSum(new long[]{5,3,1,2}));
  }

  /*
  classic “optimal merge” (a.k.a. Huffman merging) problem: keep merging the two
  smallest numbers, add their sum to the running total, push the sum back, and repeat.
  This yields the lowest possible total sum across all merge orders.
   */
  public static long minimalAccumulatedSum(long[] nums) {
    if (nums == null || nums.length < 2) return 0L;

    PriorityQueue<Long> pq = new PriorityQueue<>();
    for (long v : nums) pq.add(v);

    long total = 0L;
    while (pq.size() > 1) {
      long a = pq.poll();   // smallest
      long b = pq.poll();   // next smallest
      long s = a + b;       // merge cost this step
      total += s;           // accumulate total cost
      pq.add(s);            // push merged sum back
    }
    return total;
  }
}
