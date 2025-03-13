package a_0_curr.String;

import java.util.HashSet;

public class LongestSubstringWithUniqueCharacters {
    public static void main(String[] args) {
        String input = "abcabcbadb";
//        input = "aaacbbcabd";
        String result = getUniqueCharacterSubstring(input);
        System.out.println("Longest substring with unique characters: " + result);
    }

    public static String getUniqueCharacterSubstring(String s) {
        int n = s.length();
        if (n == 0) return "";

        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0; // Sliding Window Technique
        int maxLength = 0;
        int start = 0;

        while (right < n) {
            char c = s.charAt(right);
            if (!set.contains(c)) { // increment right and add to set
                set.add(c);
                right++;
                if (right - left > maxLength) { // check this on every add to set
                    maxLength = right - left;
                    start = left;
                }
            } else { // increment left and remove from set, left increment imp to reduce right - left maxLength check
                set.remove(s.charAt(left));
                left++;
            }
        }

        return s.substring(start, start + maxLength);
    }
}