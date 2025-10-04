package com.c7.b;

import java.util.*;

public class ArraysSortColectionsSort {
    public static void main(String[] args) {
        Integer[] ai = { 10, 2, 3 };
        System.out.println(ai[0]);
        Arrays.sort(ai);
        System.out.println(ai[0]);
        
        ArrayList<Integer> al = new ArrayList();
        al.add(10); al.add(2); al.add(3);
        System.out.println(al);
        Collections.sort(al);
        System.out.println(al);

        List<String> listA = new ArrayList<>();

        listA.add("element 0");
        listA.add("element 1");
        listA.add("element 2");
        listA.add("element 1");
        String element0 = listA.get(0);
        String element1 = listA.get(1);
        String element3 = listA.get(2);
        System.out.println(listA);
        listA.remove("element 1");
        System.out.println(listA);
//        listA.toArray()

        String[] values = new String[]{ "one", "two", "three" };
        List<String> list = (List<String>) Arrays.asList(values);
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        for(String element : list) {
            System.out.println(element);
        }
        list.forEach( element -> { System.out.println(element); });
    }
}
