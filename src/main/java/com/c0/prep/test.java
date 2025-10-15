package com.c0.prep;

import java.util.PriorityQueue;
import java.util.function.Function;

public class test {
  public static void main(String[] args) {
    int target = 10;
    int n = 3;
    int arr[] = {25, 15, 7, 5, 10, 3};
    int res[] = findTopN(arr, target, n);
    for (int no : res)
      System.out.println(no);
  }

  static int[] findTopN(int arr[], int target, int n) {
    PriorityQueue<Element> pq = new PriorityQueue<Element>((x, y) -> {
      Integer a = x.diff;
      Integer b = y.diff;
      return b.compareTo(a);
    });
    for (int no : arr) {
      if (no == target)
        continue;
      Element el = new Element();
      el.no = no;
      el.diff = Math.abs(target - no);
      pq.offer(el);
      if (pq.size() > n)
        pq.poll();
    }
    int res[] = new int[pq.size()];
    int i = 0;
    while (!pq.isEmpty()) {
      res[i++] = pq.poll().no;
    }
    Function f = (a) -> { // Lambda
      return " World!" + a;
    };
    calling(f);
    return res;
  }

  static void calling(Function<Integer, String> function) {
    System.out.println("Hello" + function.apply(8));
  }
}
