package com.c0.aisera_keeper;

import java.util.Collections;
import java.util.*;
/*
TreeMap<K, V> is a sorted map in Java — it stores keys in ascending order by default (using their natural order
or a custom Comparator). Internally, it uses a Red-Black Tree (a balanced binary search tree).
All operations O(log n)
🧩 Common use cases
- Keeping entries sorted by key (e.g., timestamps, names, IDs)
- Finding next / previous / range keys quickly
- When you need ordered iteration
- Implementing LRU-like structures or interval lookups
✅ Summary
- TreeMap keeps keys sorted automatically.
- Slower than HashMap (O(log n) vs O(1)) but gives you ordering + range queries.
- Use it when ordering matters or when you need nearest / range lookups.

TreeMap is a good choice when you need a map whose entries are sorted by key, or when you need to retrieve keys
    in a sorted order.

HashMap stores its entries in a hash table and does not guarantee any specific order of the entries over time.
    The order can appear random and may change when items are inserted or removed.
    performance of HashMap operations (containsKey, get, put, remove) is typically O(1), assuming the hash function
    disperses the elements properly among the buckets. However, in the worst case (e.g., poor hash function
    leading to collisions), the performance can degrade to O(n).
    HashMap Suitable for cases where order doesn't matter, and fast access is important, O(1) for read, write, delete

TreeSet is implemented using a self-balancing binary search tree (usually a Red-Black Tree),
    which ensures that the tree remains balanced and maintains the logarithmic height.

LinkedHashMap maintains the insertion order, and achieves this by using a doubly-linked list in addition to the
    hash table. Bit slower due to the linked list maintenance and more memory.
 */
public class _TreeMap {

    public static void main (String[] args)
    {
        simple();
        TreeMap_Comparator(); // Comparator as Lambda
        MinFrequency(); // TreeMap is overkill, use simple loop with just two min and freq vars
    }
    static void simple(){
        // Create a TreeMap of Integer -> String
        TreeMap<Integer, String> scores = new TreeMap<>();
        // Insert values (auto-sorted by key)
        scores.put(85, "Alice");
        scores.put(95, "Bob");
        scores.put(75, "Charlie");
        scores.put(90, "David");
        System.out.println("Sorted scores: " + scores);
        System.out.println("Highest score: " + scores.lastEntry());
        System.out.println("Lowest score: " + scores.firstEntry()); // default is ascending order
        System.out.println("Next higher than 85: " + scores.higherEntry(85));
        System.out.println("Between 80–95: " + scores.subMap(80, true, 95, true));
        scores.remove(75);
        System.out.println("After removing 75: " + scores);
    }

    static void TreeMap_Comparator(){
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

    /*
    Find frequency of smallest integer in list
    So, a TreeMap (which maintains a sorted structure with O(log n) insertions) is overkill here.
    A HashMap or simple loop (O(n)) is faster and simpler.
    Best solution below, is simple loop with two variables tracking min and freq. min and freq is like one
    member hashmap, if you top 3, create a 3 member hashmap, better than six variables (too much clutter).
    Time Complexity: O(n) - Space Complexity: O(1)
    */
    static void MinFrequency() {
        List<Integer> nums = List.of(5, 1, 3, 1, 2, 1);
        int min = Integer.MAX_VALUE;
        int freq = 0;
        for (int n : nums) {
            if (n < min) {
                min = n;
                freq = 1; // reset count
            } else if (n == min) {
                freq++;
            }
        }
        System.out.println("Smallest number: " + min + " - Frequency: " + freq);
        /*
        🧩 Alternative using Collections (cleaner) - two loops but still O(n) as O(2n) considered O(n)
         */
        Integer min2 = Collections.min(nums);
        // Variables in Lambda must be final or effectively final, hence min2 define (not final but effectively final)
        Long freq2 = nums.stream().filter(x -> x == min2).count(); // count() returns Long, so freq2 Long
        System.out.println("Smallest number: " + min2 + " - Frequency: " + freq2);
    }

}