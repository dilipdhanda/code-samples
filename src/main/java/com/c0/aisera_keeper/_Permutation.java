package com.c0.aisera_keeper;

import java.util.ArrayList;
import java.util.List;

/*
In Java, there’s no built-in data structure or library that directly generates or stores all permutations or
combinations for you automatically. Unlike Python (itertools.permutations, itertools.combinations),
Java’s standard library doesn’t provide these generators.
You have to write your own using recursion, backtracking, or iteration.

Backtracking is a type of recursion used for search and exploration problems — where you explore all possible
options and undo (backtrack) bad or incomplete choices.
You can think of it as: "Try a choice → recurse → undo → try next choice"
Backtracking is the core engine behind both permutations and combinations.
The only difference is whether order matters and how you control the recursion path (boolean[] used vs start index).

Complexity O(P(n, r)) = O(n! / ((n−r)!))
 */

public class _Permutation {
    public static void main(String[] args) {

        System.out.printf(getIntPermutations(new int[]{6, 7, 8, 9}, 2).toString());

        if (1==2) System.out.println(getStringPermutations("abc").toString());
    }

    // Generate permutations of length 'r' from the array 'nums'
    // need "used" and "resultList" as params, to remember them across backtracking and recursion
    // "used" needed, since order matters
    static void permute(int[] nums, int r, boolean[] used, List<Integer> currPermutation, List<List<Integer>> resultList) {
        // Base case: once we pick 'r' elements, store this permutation
        // Below is just a "condition" to stop recursion and add to resultList
        // Condition can also be based on other factors, e.g., sum of elements, etc.
        if (currPermutation.size() == r) {
            resultList.add(new ArrayList<>(currPermutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // this check skips the current number in ALL recursive calls below for say 7
            used[i] = true; // this makes sure we do not reuse current recursive call below
            // e.g. for 7 in {6,7,8,9} used[] will become (F,T,F,F) when we pick or start for 7
            currPermutation.add(nums[i]);
            permute(nums, r, used, currPermutation, resultList);
            // Backtrack Step (two lines below) - i may not be 0 in this Backtrack steps
            // - call comes here after recursion ended, and we have a complete currPermutation
            // Remember i starts with 0 only in permute recursive call, as it's a new function call
            currPermutation.remove(currPermutation.size() - 1);
            used[i] = false;
            // for 7, it will make 6 T, then backtrack to make it F, then do the same for 8,9
            // After 7 is done, and we do the same for 8, 9
        }
    }

    // Public method to generate all permutations of length r
    static List<List<Integer>> getIntPermutations(int[] nums, int r) {
        List<List<Integer>> resultList = new ArrayList<>(); // empty result list
        List<Integer> currPermutation = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // init all to false at start
        permute(nums, r, used, currPermutation, resultList);
        return resultList;
    }

    public static List<String> getStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null || str.length() == 0) {
            permutations.add("");
            return permutations;
        }
        char first = str.charAt(0);
        String remaining = str.substring(1);
        List<String> words = getStringPermutations(remaining);
        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                String permutation = insertCharAt(word, first, i);
                permutations.add(permutation);
            }
        }
        return permutations;
    }

    private static String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }
}

