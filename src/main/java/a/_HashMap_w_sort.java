package a;

import java.util.*;

public class _HashMap_w_sort {
    public static void main(String[] args) {
        /*
        HashMap allows duplicate values and null keys, not synchronized (better performance, but not thread safe)
        Hashing fn takes key and maps to hash, that determines where obj is stored
        HashTable is synchronized
         */
        hashmap_withSort();
//        characterHMs();
//        countryCodes_Streams();
    }

    public static void hashmap_withSort() {
        // Following Map is immutable. Map.of() is since Java 9 only
        Map<Integer, Integer> mapImmutable = Map.of(0,22, 1,1, 2,200, 3,8, 4,90,
                5,8, 6,7, 7,88, 8, 33, 9,55); // max 10 allowed since Java 9
        // use Map.entries() is you have more than 10 entries
        HashMap<Integer, Integer> mapCount =  new HashMap<>();
        mapImmutable.forEach((k, v) -> mapCount.put(k, v));
        /* Try this later
        HashMap<Integer, String> hashMap = Stream.of(new Object[][] {
                { 1, "One" },
                { 2, "Two" },
                { 3, "Three" },
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
         */

        List<Integer> keys = new ArrayList(mapCount.keySet()); // O(n)
        // sort keys by their values in hashmap
        Collections.sort(keys, (w1, w2) -> mapCount.get(w1) < (mapCount.get(w2)) ? 1  : -1); // O(n log n)
        // final O(n log n)  + O(n) is n + n log n = n (1 + log n)
        keys.forEach(x -> System.out.println(x + " -> " + mapCount.get(x)));
        mapCount.put(2,100); // mapCount is mutable
        // LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>(); // not needed with Comparator util
        // create entry in hashmap, so you can retain original indexes for result triplets
    }

    public static void characterHMs (){
        String str = "abcdABCDabcdabcdABCDabc";
        HashMap<Character, Integer> hm = new HashMap<>();
        int i = 0;
        while (i < str.length()) {
            Character c = str.charAt(i);
            if (hm.get(c) != null) {
                hm.put(c, hm.get(c) + 1);
            } else {
                hm.put(c, Integer.valueOf(1));
            }
            i++;
        }

        Iterator it = hm.keySet().iterator();         // Key - iterator
        System.out.println("HashMap Key-Value Pairs : ");
        while(it.hasNext()){
            Character key = (Character) it.next();
            System.out.println(key + " -> " +hm.get(key));
        }

        it = hm.entrySet().iterator(); // Entry - different iterator
        System.out.println("HashMap Key-Value Pairs : ");
        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
            System.out.println(me.getKey() + " -> " + me.getValue());
        }
    }
    public static void countryCodes_Streams(){
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
}