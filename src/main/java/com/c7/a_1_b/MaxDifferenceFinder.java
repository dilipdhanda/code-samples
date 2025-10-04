package com.c7.a_1_b;

public class MaxDifferenceFinder {
    public static int findMaxDifference(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array should contain at least two elements");
        }
        int maxDifference = arr[1] - arr[0];  // init
        int minElement = arr[0];              // init
        for (int i = 1; i < arr.length; i++) {
            maxDifference = Math.max(maxDifference, arr[i] - minElement); // update if we find a new max difference
            minElement = Math.min(minElement, arr[i]); // Update minElement if we find a new min element
        }
        return maxDifference;
    }

    public static void main(String[] args) {
        int[] testArray = {2, 3, 10, 6, 4, 8, 100};
        System.out.println(findMaxDifference(testArray)); // Output: 8
    }
}

