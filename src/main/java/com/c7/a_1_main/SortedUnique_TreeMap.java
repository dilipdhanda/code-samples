package com.c7.a_1_main;

import java.util.*;

public class SortedUnique_TreeMap {
    public static void main(String args[]) {

        Map<String, String> map2 = new TreeMap<String, String>();
        map2.put("One", "1");
        map2.put("Five", "5");
        map2.put("Four", "4");
        map2.put("Two", "2");
        map2.put("Three", "3");
        System.out.println("TreeMap: \n" + map2);

        Set<String> map1 = new TreeSet<String>();
        map1.add("One");
        map1.add("Five");
        map1.add("Four");
        map1.add("Two");
        map1.add("Three");
        System.out.println("TreeSet: \n" + map1);
    }
}