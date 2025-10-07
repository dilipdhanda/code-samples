package com.c1.PayPal;

public final class BuildAnalogousArrays {
  // part of PayPal exercises
  public static void main(String[] args) {
    int[] diffs = {-1, -3, 4};   // means s[i] - s[i+1]
    int L = 0, U = 255;

    int[] a = buildAnalogousArray(diffs, L, U);
    System.out.println(java.util.Arrays.toString(a)); // e.g., [0, 1, 4, 0]
  }

  /**
   * Build one analogous array a[] such that:
   *  - a.length = diffs.length + 1
   *  - lowerBound <= a[i] <= upperBound
   *  - a[i] - a[i+1] == diffs[i]   (i.e., diffs = s[i] - s[i+1])
   *
   * If no solution exists, returns an empty array.
   */
  public static int[] buildAnalogousArray(int[] diffs, int lowerBound, int upperBound) {
    int n = (diffs == null) ? 0 : diffs.length;
    int len = n + 1;

    // Edge case: single element array – pick any value in range.
    if (n == 0) {
      return (lowerBound <= upperBound) ? new int[] { lowerBound } : new int[0];
    }

    // prefix[i] = sum_{k=0..i-1} diffs[k], with prefix[0] = 0
    long minPref = 0, maxPref = 0, pref = 0;
    for (int d : diffs) {
      pref += d;
      if (pref < minPref) minPref = pref;
      if (pref > maxPref) maxPref = pref;
    }

    // Valid a[0] must lie in [L + maxPref, U + minPref]
    long loX = (long) lowerBound + maxPref;
    long hiX = (long) upperBound + minPref;
    if (loX > hiX) return new int[0];   // no intersection → no solution

    // Pick any a[0] in the interval; choose the smallest to stay well within bounds
    int a0 = (int) loX;

    // Build the array: a[i] = a0 - prefix[i]
    int[] a = new int[len];
    a[0] = a0;
    pref = 0;
    for (int i = 1; i < len; i++) {
      pref += diffs[i - 1];
      a[i] = (int) (a0 - pref);
      // (Bounds are guaranteed by construction, no need to re-check)
    }
    return a;
  }
}

