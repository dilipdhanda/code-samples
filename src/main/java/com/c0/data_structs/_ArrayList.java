package com.c0.data_structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class _ArrayList {
    public static void main(String[] args){
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1,9, 3));
        l1.add(5);l1.get(2);
        Iterator<Integer> itr = l1.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
        for (Integer i : l1){
            System.out.println(i);
        }
        l1.stream().forEach(x -> System.out.print(x + ", "));
    }
}
