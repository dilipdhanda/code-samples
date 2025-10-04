package com.c7.a_1_c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletsWithTargetSum {

    public static List<List<Integer>> findTriplets(int[] nums, int target) { // O(n^2) - sorting arr not needed
        Arrays.sort(nums); // Step 1: Sort the array

        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) { // 1st loop
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {      // 2nd check Skip duplicates
                int left = i + 1;
                int right = nums.length - 1;
                int sum = target - nums[i];

                while (left < right) {  // 2nd loop
                    if (nums[left] + nums[right] == sum) {
                        triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;    // 2nd check Skip duplicates
                        while (left < right && nums[right] == nums[right - 1]) right--; // 2nd check Skip duplicates
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < sum) {  // Arrays.sort(nums) needed for this check
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return triplets;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int target = 10;

        List<List<Integer>> triplets = findTriplets(nums, target);

        for (List<Integer> triplet : triplets) {
            System.out.println(triplet);
        }
    }
}
