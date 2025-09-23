package com.others.a_1_main;

import java.util.*;

public class Sort_By_Word_Count_Tuple implements Comparable<Sort_By_Word_Count_Tuple> {
    private int count;
    private String word;

    public Sort_By_Word_Count_Tuple(int count, String word) {
        this.count = count;
        this.word = word;
    }

    @Override
    public int compareTo(Sort_By_Word_Count_Tuple o) {
        return new Integer(this.count).compareTo(o.count);
    }
    public String toString() {
        return word + " " + count;
    }

    public static void main(String[] args) {
        String[] words = { "the", "he", "he", "he", "he", "he", "he", "he",
                "he", "the", "the", "with", "with", "with", "with", "with",
                "with", "with" };
        // find frequencies
        Arrays.sort(words);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        List<Sort_By_Word_Count_Tuple> tuples = new ArrayList<Sort_By_Word_Count_Tuple>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            tuples.add(new Sort_By_Word_Count_Tuple(entry.getValue(), entry.getKey()));
        }
        Collections.sort(tuples);
        System.out.println(tuples);
    }
}
