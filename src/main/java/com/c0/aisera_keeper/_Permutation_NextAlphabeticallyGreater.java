package com.c0.aisera_keeper;

import java.io.*;

public class _Permutation_NextAlphabeticallyGreater { // WorkDay Liu - 05/16/24
    public static void main(String[] args) throws IOException {
        String word = "abdc";
        word = "hefg"; // WorkDay Liu - 05/16/24
        word = "4321"; // No next permutation available.
        word = "1234"; // 1243
        word = "123421"; // 124123 so you need reverse, else you will return 124321 which is not the next
                        // smallest combo. 123421 is next example to understand
        word = "baca"; // bcaa - WorkDay
        String result = "";
        result = getNextPermutation(word);
//        result = inputWithBufferedReader();
        if (result != null) {
            System.out.println("Next permutation: " + result);
        } else {
            System.out.println("No next permutation available.");
        }
    }

    public static String getNextPermutation(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;

        // Step 1: Find the largest index k such that chars[k] < chars[k + 1]
        int k = n - 2;  // Out loop var starting with n - 2
        while (k >= 0 && chars[k] >= chars[k + 1]) {
            k--;
        }

        // If no such k exists, the word is in its last permutation
        if (k < 0) {
            return null;  // No next permutation
        }

        // Step 2: Find the largest index l greater than k such that chars[k] < chars[l]
        int l = n - 1;
        while (l > k && chars[k] >= chars[l]) {
            l--;
        }

        // Step 3: Swap the characters at k and l
        swap(chars, k, l);

        // Step 4: Reverse the sequence from k + 1 to the end of the word
        reverse(chars, k + 1, n - 1);

        return new String(chars);
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start, end);
            start++;
            end--;
        }
    }

    public static String inputWithBufferedReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a String: ");
        String word = bufferedReader.readLine();
        String result = _Permutation_NextAlphabeticallyGreater.getNextPermutation(word);
        bufferedReader.close();
        return result;
    }

}
