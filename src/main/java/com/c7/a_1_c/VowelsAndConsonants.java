package com.c7.a_1_c;

import java.util.List;
import java.util.stream.Collectors;

public class VowelsAndConsonants {

    public static void findNumberOfVowelsAndConsonantsStreams(String input) {
        String vowels = "aeiouy";
        String normalized = input.toLowerCase().trim();
        List<Integer> letters = normalized.chars()
                .filter(Character::isAlphabetic)
                .boxed()
                .collect(Collectors.toList());
        long vowelsCount = letters.stream()
                .filter(c -> vowels.indexOf(c) != -1)
                .count();
        long consonantCount = letters.stream().count() - vowelsCount;
        System.out.println("There are " + vowelsCount + " vowels in " + input);
        System.out.println("There are " + consonantCount + " consonants in " + input);
        System.out.println();
    }
    public static void findNumberOfVowelsAndConsonants(String input) {
        int vowelsCount = 0;
        int consonantCount = 0; // not vowel, not space
        String vowels = "aeiouy";
        String normalized = input.toLowerCase().trim();
        char[] normalizedArray = normalized.toCharArray();
        for (char c : normalizedArray) {
            if (vowels.indexOf(c) != -1) {
                vowelsCount++;
            } else if (c != ' '){
                consonantCount++;
            }
        }
        System.out.println("There are " + vowelsCount + " vowels in " + input);
        System.out.println("There are " + consonantCount + " consonants in " + input);
        System.out.println();
    }

    public static void main(String[] args) {
        String s1 = "HellO";
        String s2 = " there is a quiet Mouse";
        String s3 = "I am happy    ";

        findNumberOfVowelsAndConsonants(s1);
        findNumberOfVowelsAndConsonants(s2);
        findNumberOfVowelsAndConsonants(s3);

        findNumberOfVowelsAndConsonantsStreams(s1);
        findNumberOfVowelsAndConsonantsStreams(s2);
        findNumberOfVowelsAndConsonantsStreams(s3);
    }
}
