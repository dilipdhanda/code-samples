package com.others.a2.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class apple2_CharacterFrequency {
    /* Apple_2 - Arif Mohammed - Jacob Daigle - 07/29/24
    Given a string, print frequency of each alphabetic (ignore special chars) character, sorted by characters
    Arif at end was suggesting TreeMap, but I said that will take more time with complexity O(n*logn).
     */

    public static void main(String[] args) {
        String input = "Hello World !! World";
        charFrequencies_TreeMap_OnLogn(input);
//        charFrequencies_On(input);
    }

    public static void charFrequencies_On(String input) {
        int[] frequency = new int[26]; // for a to z, make 52 to add case-sensitive A to Z as well
        // int array above also make sure frequencies are also already sorted
        input = input.toLowerCase(); // to lowercase to handle case-insensitivity
        for (char c : input.toCharArray()) {
            if (c >= 'a' && c <= 'z') {  // this also removes special chars
                frequency[c - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (frequency[i] > 0) {
                System.out.println((char) (i + 'a') + ": " + frequency[i]);
            }
        }
    }

    public static void charFrequencies_TreeMap_OnLogn(String input) {
        // Use a HashMap to count frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        Map<Character, Integer> sortedMap = new TreeMap<>();
        // Count frequency of each character
        for (char c : input.toCharArray()) {
            if (Character.isAlphabetic(c)){
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1); // O(n)
                // insert as you put is better than later new TreeMap<>(frequencyMap)
                sortedMap.put(c, frequencyMap.get(c)); // O(log-n)
            }
        }
        // Use a TreeMap to sort characters
        // sortedMap = new TreeMap<>(frequencyMap);
        // O(n*logn) : O(n) to iterate over each entry, O(log-n) to insert each entry in TreeMap that is a Red-Black tree

        // Print sorted character frequencies
        for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
