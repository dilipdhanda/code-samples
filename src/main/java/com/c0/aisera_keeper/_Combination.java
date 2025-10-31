package com.c0.aisera_keeper;

import java.util.*;

/*
"start" index to ensure we don’t reuse earlier elements.
Complexity O(C(n, r)) = n! / (r! × (n−r)!)
 */
public class _Combination {
  public static void main(String[] args) {
//    System.out.println(getIntCombinations(new int[]{6, 7, 8}, 2));
    System.out.println(getTargetSumCombinations(new int[]{3, 2, 5, 2, 10, 7}, 2, 9));
//    System.out.println(getTargetSumCombinations(new int[]{3, 2, 6, 2, 10, 7}, 2, 9));
    System.out.println(getTargetSumCombinationsAnySize(new int[]{3, 2, 6, 2, 10, 7}, 9));
  }

  // Generate combinations of length 'r' from the array 'nums'
  static void combine(int[] nums, int r, int start, List<Integer> currCombination, List<List<Integer>> resultList) {
    // Base case: once we pick 'r' elements, store this combination
    // Below is just a "condition" to stop recursion and add to resultList
    // Condition can also be based on other factors, e.g., sum of elements, etc.
    if (currCombination.size() == r) {
      resultList.add(new ArrayList<>(currCombination));
      return;
    }
    // Iterate from 'start' index to end — ensures no duplicates and preserves combination order
    for (int i = start; i < nums.length; i++) {
      currCombination.add(nums[i]);
      combine(nums, r, i + 1, currCombination, resultList);
      // Backtrack:
      // Backtrack Step (line below) - remove last element before next iteration
      // i may not be 0 in this Backtrack step
      // Remember i starts with 0 only in combine recursive call, as it's a new function call
      currCombination.remove(currCombination.size() - 1);
    }
  }

  // Public method to generate all combinations of length r
  static List<List<Integer>> getIntCombinations(int[] nums, int r) {
    List<List<Integer>> resultList = new ArrayList<>();
    List<Integer> currCombination = new ArrayList<>();
    combine(nums, r, 0, currCombination, resultList);
    return resultList;
  }

  // Public method to generate all combinations of length r
  static List<List<Integer>> getTargetSumCombinations(int[] nums, int r, int targetSum) {
    List<List<Integer>> resultList = new ArrayList<>();
    List<Integer> currCombination = new ArrayList<>();
    combineTargetSum(nums, r, 0, targetSum, currCombination, resultList);
    return resultList;
  }

  // Generate combinations of length 'r' from the array 'nums'
  static void combineTargetSum(int[] nums, int r, int start, int targetSum,
                               List<Integer> currCombination, List<List<Integer>> resultList) {
    // Base case: once we pick 'r' elements, store this combination
    // Below is just a "condition" to stop recursion and add to resultList
    // Condition can also be based on other factors, e.g., sum of elements, etc.
    // But "currCombination.size() == r" must always be true to consider a valid combination and other conditions
    // like sum of elements, etc. need to be checked inside this block.
    if (currCombination.size() == r) {
      if (currCombination.stream().mapToInt(Integer::intValue).sum() == targetSum){
        resultList.add(new ArrayList<>(currCombination));
      }
      return;
    }
    // Iterate from 'start' index to end — ensures no duplicates and preserves combination order
    for (int i = start; i < nums.length; i++) {
      currCombination.add(nums[i]);
      combineTargetSum(nums, r, i + 1, targetSum, currCombination, resultList);
      // Backtrack:
      // Backtrack Step (line below) - remove last element before next iteration
      // i may not be 0 in this Backtrack step
      // Remember i starts with 0 only in combine recursive call, as it's a new function call
      currCombination.remove(currCombination.size() - 1);
    }
  }

  // Public method to generate all combinations of length r
  static List<List<Integer>> getTargetSumCombinationsAnySize(int[] nums, int targetSum) {
    List<List<Integer>> resultList = new ArrayList<>();
    List<Integer> currCombination = new ArrayList<>();
    combineTargetSumAnySize(nums, 0, targetSum, currCombination, resultList);
    return resultList;
  }

  static void combineTargetSumAnySize(int[] nums, int start, int targetSum,
                               List<Integer> currCombination, List<List<Integer>> resultList) {
    /*
    for any size targeted sum combinations, conditions remove size r check, but you still need to return to continue
     backtracking
        if(remain < 0) return; // Error - stop this path and backtrack by removing last added element
        else if(remain == 0) solutions.add(new ArrayList<>(tempList)); // FOUND SOLUTION - deep copy as tempList has a solution
     */
    int currSum = currCombination.stream().mapToInt(Integer::intValue).sum();
    if (currSum > targetSum || currSum < 0) { // greater than targetSum or negative, means no point in continuing this path
      return; // backtrack
    } else {
      resultList.add(new ArrayList<>(currCombination));
    }
    // currSum < targetSum, so solution STILL POSSIBLE, so continue this path
    // Iterate from 'start' index to end — ensures no duplicates and preserves combination order
    for (int i = start; i < nums.length; i++) {
      currCombination.add(nums[i]);
      combineTargetSumAnySize(nums,i + 1, targetSum, currCombination, resultList);
      // Backtrack:
      // Backtrack Step (line below) - remove last element before next iteration
      // i may not be 0 in this Backtrack step
      // Remember i starts with 0 only in combine recursive call, as it's a new function call
      currCombination.remove(currCombination.size() - 1);
    }
  }
}

