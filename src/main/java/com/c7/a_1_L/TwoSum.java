package com.c7.a_1_L;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

class TwoSum {

    // Time complexity: O(n) best
    static int[] twoSumHash(int[] nums, int target){
        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i = 0; i < nums.length ; i++){
            int complement = target - nums[i];
            if (numMap.containsKey(complement)){
                return new int[]{numMap.get(complement), i};
            } else {
                numMap.put(nums[i], i); // add value as key, and index in nums as value
            }
        }
        return new int[]{}; // empty array

    }

    // Time complexity: O(n*log(n))
    private static int[] findTwoSum_Sorting(int[] nums, int target) {
        Arrays.sort(nums);
        // With sort we loose original indexes here (unless we put them in a hashmap) we
        // return actual values not indices from original array
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            if(nums[left] + nums[right] == target) {
                return new int[] {nums[left], nums[right]};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 2, 7, 11, 15};
        int target = 9;

        int[] indices;
        indices = twoSumHash(nums, target);
        Arrays.stream(indices).forEach(x -> System.out.print(x + ", "));
//        indices = findTwoSum_BruteForce(nums, target);
//        Arrays.stream(indices).forEach(x -> System.out.print(x + ", "));
//        indices = findTwoSum_Sorting(nums, target);  // returns values, not indices
//        Arrays.stream(indices).forEach(x -> System.out.print(x + ", "));
    }

    // Time complexity: O(n^2)
    private static int[] findTwoSum_BruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }

    static void scanInputs(){
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = keyboard.nextInt();
        }
        int target = keyboard.nextInt();
        keyboard.close();
    }
}
