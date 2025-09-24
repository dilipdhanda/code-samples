package com.curr.data_structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class _Integers {
    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 2, 3, 4, 5, 6};
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(array1)); // creates unique numbers set
//        System.out.println(set1);

        int[] intArr = {1, 2, 2, 3, 4, 5, 6};
        List<Integer> integerList = Arrays.stream(intArr).boxed().toList(); // give immutable collection
        // ArrayList<Integer> preferred more than List<Integer>
        ArrayList<Integer> integerArrayList = // use THIS
                new ArrayList<Integer>(Arrays.stream(intArr).boxed().toList());
        System.out.println(integerList);
        for(int i: intArr){
            continue;
        }
        for(int i = 0; i < intArr.length; i++){
//            System.out.println(intArr[i]);
            continue;
        }

    }
}
