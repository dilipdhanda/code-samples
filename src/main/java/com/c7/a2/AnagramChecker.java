package com.c7.a2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnagramChecker {

    public static void listAllAnagrams() {
        String[] words = {"tea", "eat", "apple", "ate", "vaja", "cut", "java", "utc"};
        //        solution(words) = 4.
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : words) {
            char[] chArr = str.toCharArray();
            Arrays.sort(chArr);
            String key = new String(chArr); // do not use chArr.toString(), it returns reference as string
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> strList = new ArrayList<>();
                strList.add(str);
                map.put(key, strList);
            }
        }
        map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    public static boolean areAnagrams(String str1, String str2) {
        // If lengths are different, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to char arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted char arrays
        return Arrays.equals(charArray1, charArray2);
    }

    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";

        if (areAnagrams(str1, str2)) {
            System.out.println(str1 + " and " + str2 + " are anagrams.");
        } else {
            System.out.println(str1 + " and " + str2 + " are not anagrams.");
        }
        listAllAnagrams();
    }

    static boolean areAnagrams_with_Hashmaps(String s1, String s2){  // char arrays sort is better
        if (s1.length() != s2.length()){
            return false;
        }
        HashMap<Character,Integer> hm = new HashMap<>();
        for(Character c: s1.toCharArray()){
            if (hm.containsKey(c)){
                hm.put(c,hm.get(c)+1);
            } else {
                hm.put(c,1);
            }
        }
        for(Character c: s2.toCharArray()){
            if (hm.containsKey(c)){
                hm.put(c,hm.get(c)-1);
                if (hm.get(c) == 0){
                    hm.remove(c);
                }
            } else {
                return false;
            }
        }
        if (hm.size() == 0){
            return true;
        }
        return false;
    }
}

