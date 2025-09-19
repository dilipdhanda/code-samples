package a2.tests;

import java.util.HashMap;
import java.util.Map;

public class wm_RepeatedWords_ItemsArray { // Walmart Nagappa - Oct 2023 to 12/13/23

    public static class Item {
        public Item(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
        String name;
        long soldCount;
        double soldAmount;

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }
        @Override
        public boolean equals(Object obj) {
            // checks for name null and both mull then true, it either null then false
            return ((Item) obj).name.equalsIgnoreCase(this.name);
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> hm = new HashMap<>();
        for(String name : new String[] {"i1", "i2", "i1"} ){
            Item i = new Item(name); // did not add soldCount and soldAmount as they are not used in logic
            String key = i.getName();
            Integer v = hm.get(key) == null ? hm.put(key, 1) : hm.put(key, (hm.get(key)+1));
        }
        /*
        // i1, 3, 5 - i1, 5, 9 - i2, 5 , 0
        // put i1=2, i2 =1
         */
        System.out.println(hm);
    }
}
