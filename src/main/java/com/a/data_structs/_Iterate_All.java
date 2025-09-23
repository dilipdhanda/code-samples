package com.a.data_structs;

import java.util.*;

public class _Iterate_All {

    public static void main(String[] args){
        itr_ArrayList();
        itr_HashMaps();
        itr_array();
        itr_String();
    }

    public static void itr_ArrayList(){
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

        for (int i = 0; i < l1.size(); i++){
            System.out.print(l1.get(i) + " ");
        }
    }
    public static void itr_HashMaps(){
        Map<String, String> hm = new HashMap<>();
        hm.put("India", "IN");
        hm.put("United States", "US");
        hm.put("Russia", "RU");

        Iterator<Map.Entry<String, String>> i1 = hm.entrySet().iterator();
        while(i1.hasNext()) { System.out.println("Iterator Entry : "+i1.next()); }
        Iterator<String> i2 = hm.keySet().iterator();
        while(i2.hasNext()) { System.out.println("Iterator Key: "+hm.get(i2.next())); }

        Set<Map.Entry<String, String>> countryISOCodeEntries = hm.entrySet(); // ENTRY set - insertion order
        System.out.println("countryISOCode entries : " + countryISOCodeEntries);
        for(Map.Entry<String, String> e : hm.entrySet()) { System.out.println("Entry: "+e.getKey()+", "+e.getValue()); }

        hm.entrySet().forEach(entry -> { System.out.println("forEach: "+entry.getKey() + " => " + entry.getValue()); });

        Set<String> countries = hm.keySet(); // HashMap's KEY set
        System.out.println("countries : " + countries);

        Collection<String> isoCodes = hm.values(); // HashMap's values
        System.out.println("isoCodes : " + isoCodes);
    }
    public static void itr_String(){
        String s1 = "abc";
        for (int i = 0; i < s1.length(); i++) {
            System.out.println(s1.charAt(s1.length()-i-1));
        }
    }
    public static void itr_array() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i]);
        }
        for (String i : cars) {
            System.out.println(i);
        }
        int[][] myNumbers = {{1, 2, 3, 4}, {5, 6, 7}};
        for (int i = 0; i < myNumbers.length; ++i) {
            for (int j = 0; j < myNumbers[i].length; ++j) {
                System.out.println(myNumbers[i][j]);
            }
        }
    }
}
