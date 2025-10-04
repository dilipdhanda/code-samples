package com.c0.data_structs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class _LinkedList {

    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("apple");
        linkedList.add("banana");
        linkedList.add("pear");

        linkedList.add(1, "mango");
        linkedList.get(1);
        linkedList.contains("pear");
        linkedList.getFirst();
        linkedList.getLast();
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.size();

        System.out.println(linkedList);

        List<String> synchronizedShoppingList = Collections.synchronizedList(linkedList);
        System.out.println(synchronizedShoppingList);

    }
}