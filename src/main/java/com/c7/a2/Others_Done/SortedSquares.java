package com.c7.a2.Others_Done;

import java.util.Arrays;
/* returning an array of the squares of each number sorted in non-decreasing order, given an
integer array sorted in non-decreasing order, you can use a two-pointer approach.
This approach takes advantage of the fact that the input array is already sorted
*/
public class SortedSquares {
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int pos = n - 1;

        while (left <= right) {
            // lower negative square may be higher a positive number square
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                result[pos] = nums[left] * nums[left];
                left++;
            } else {
                result[pos] = nums[right] * nums[right];
                right--;
            }
            pos--; // result is non-decreasing, so start from end
        }

        return result;
    }

    public static Double[] sortedSquareRoots(int[] nums) {
        int n = nums.length;
        Double[] result = new Double[n];
        int left = 0, right = n - 1;
        int pos = n - 1;

        while (left <= right) {
            // lower negative square may be higher a positive number square
            if (Math.sqrt(Math.abs(nums[left])) > Math.sqrt(Math.abs(nums[right]))) {
                result[pos] = Math.sqrt(Math.abs(nums[left]));
                left++;
            } else {
                result[pos] = Math.sqrt(Math.abs(nums[right]));
                right--;
            }
            pos--; // result is non-decreasing, so start from end
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(sortedSquares(nums)));
        System.out.println(Arrays.toString(sortedSquareRoots(nums)));
    }
    /*
    Squares
    [-4, -1, 0, 3, 10]
    [0, 1, 9, 16, 100]
    Square roots
    [-4, -1, 0, 3, 10]
    [16, 1, 0, 9, 100]
     */
}

