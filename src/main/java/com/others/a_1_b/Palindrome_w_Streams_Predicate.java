package com.others.a_1_b;

import java.util.stream.IntStream;

public class Palindrome_w_Streams_Predicate {

    public static boolean palindromeCheckStreams(String original) {
        String normalized = original.toLowerCase();

        return IntStream.range(0, normalized.length() / 2)
                .allMatch(i -> normalized.charAt(i) == normalized.charAt(normalized.length() -i -1));
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("dilip"));
        System.out.println(palindromeCheckStreams("aba"));
    }

    public static boolean isPalindrome(String str) {
        // Null check
        if(str == null) {
            throw new IllegalArgumentException("Input string should not be null");
        }

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // It's not a palindrome
            }
            left++;
            right--;
        }
        return true; // It's a palindrome
    }
}
