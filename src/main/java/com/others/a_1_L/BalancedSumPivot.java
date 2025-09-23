package com.others.a_1_L;

/*
Balanced Array  - /Walmart plus question 05/15
Given an array of numbers, find the index of the smallest array element (the pivot),
for which the sums of all elements to the left and to the right are equal. The array may
not be reordered.
Example arr=[1,2,3,4,6], the sum of the first three elements, 1+2+3=6. The value of the last element is 6.
Using zero based indexing, arr[3]=4 is the pivot between the two subarrays.
The index of the pivot is 3.
 */

import java.util.stream.IntStream;

public class BalancedSumPivot {
    static int balancedSumPivot(int[] arr){
        int p = -1;
        int l = arr[0];
        int r = arr[arr.length - 1];
        int i = 1;
        int j = arr.length - 2;
        while (i <= j){
            if ((i == j) && (l == r)) {
                p = i;
            }
            if (l < r) {
                l += arr[i];
                i++;
            }
            else {
                r += arr[j];
                j--;
            }
        }
        System.out.println(p);
        return p;
    }
    public static void main(String[] args){
        int[] arr = {1,3,6,3,4};
        arr = IntStream.of(9,1,2,3,4,4,4,6,9).toArray();
        balancedSumPivot(arr);
    }
}
