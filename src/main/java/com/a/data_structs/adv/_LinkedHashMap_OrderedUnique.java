package com.a.data_structs.adv;

import java.util.LinkedHashMap;

public class _LinkedHashMap_OrderedUnique {
    public static void main(String[] args) {
/*
In a LinkedHashMap, the elements are ordered either by their insertion order or based on their access order,
depending on how the LinkedHashMap was constructed. This is why the elements appear in a predictable sequence
when you iterate over them, unlike with a regular HashMap.
This is achieved through a doubly-linked list running through all of its entries.
It has a performance overhead due to maintaining a linked list alongside the hash table. However, it still
provides constant-time performance for the basic operations (get and put), assuming the hash function
disperses elements properly among the buckets.
Access Order: LinkedHashMap can be configured to order its entries based on their access order (the order
in which keys were last accessed) instead of insertion order. This can be useful for implementing LRU
(Least Recently Used) caches.
 */
        LinkedHashMap<String, String> insertOrderMap = new LinkedHashMap<>();
        insertOrderMap.put("1st","aaa"); insertOrderMap.put("2nd","ccc"); insertOrderMap.put("3rd","ccc");
        System.out.println(insertOrderMap.toString());
        boolean contains = insertOrderMap.containsKey("com/others/a_2");
        insertOrderMap.remove("2nd");
        insertOrderMap.put("4th","aaa");
        insertOrderMap.put("2th","cccCCC"); // replaces 2nd
        System.out.println (insertOrderMap); // retains order
//        for (Map.Entry<String, String > entry : insertOrderMap.entrySet()) {
//            System.out.println(entry.getKey() + " - >" + entry.getValue());
//        }
//        for(String key : insertOrderMap.keySet()) { System.out.println(key); }
//        for(String value : insertOrderMap.values()) { System.out.println(value); }

        LinkedHashMap<String, Integer> accessOrderMap = new LinkedHashMap<>(16, 0.75f, true);
        accessOrderMap.put("One", 1);
        accessOrderMap.put("Two", 2);
        accessOrderMap.put("Three", 3);
        System.out.println(accessOrderMap.toString());
        accessOrderMap.get("One"); // Accessing "One" will move it to the end of the map
        System.out.println(accessOrderMap.toString());
//        for (Map.Entry<String, Integer > entry : accessOrderMap.entrySet()) {
//            System.out.println(entry.getKey() + " - >" + entry.getValue());
//        }
    }
}
