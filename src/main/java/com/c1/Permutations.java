package com.c1;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        String input = "abc";
        List<String> result = getAllPermutations(input);
        System.out.println("All permutations of the string \"" + input + "\":" + result.toString());
    }

    public static List<String> getAllPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null || str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String remaining = str.substring(1);
        List<String> words = getAllPermutations(remaining);

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

