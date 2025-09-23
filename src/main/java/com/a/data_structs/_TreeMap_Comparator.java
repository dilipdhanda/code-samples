package com.a.data_structs;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class _TreeMap_Comparator {
    public static void main(String[] args) {
        // TreeMap with keys sorted by ignoring case
        // SortedMap<String, String> fileExtensions = new TreeMap<>(String.CASE_INSENSITIVE_ORDER); // short form

        SortedMap<String, String> fileExtensions = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        fileExtensions.put("PYTHON", ".py");
        fileExtensions.put("c++", ".cpp");
        fileExtensions.put("a-Key", "a-value");
        fileExtensions.put("KOTLIN", ".kt");
        fileExtensions.put("A-Key", "A-value");
        fileExtensions.put("Golang", ".go");
        fileExtensions.entrySet().stream().forEach(System.out::println);

        // The keys will be sorted ignoring the case (Try removing String.CASE_INSENSITIVE_ORDER and see the output)
        System.out.println(fileExtensions);
    }
}