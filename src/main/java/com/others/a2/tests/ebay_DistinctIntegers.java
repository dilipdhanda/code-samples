package com.others.a2.tests;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class ebay_DistinctIntegers { // eBay - Roshan - 07/23/24
    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 2, 3, 4, 5};
        Integer[] array2 = {4, 5, 6, 7, 7, 8};

        List<Integer> result = findDistinctIntegers(array1, array2);
        System.out.println("Distinct integers: " + result);
    }

    public static List<Integer> findDistinctIntegers(Integer[] array1, Integer[] array2) {
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(array1));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(array2));

        List<Integer> result = new ArrayList<>();

        // Add elements from set1 that are not in set2
        for (Integer num : set1) {
            if (!set2.contains(num)) {
                result.add(num);
            }
        }

        // Add elements from set2 that are not in set1
        for (Integer num : set2) {
            if (!set1.contains(num)) {
                result.add(num);
            }
        }

        return result;
    }
}
