package com.others.a2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class WordsFreqTopK_lambda_comparable {

    public static String[] topKWords(final String stream, final int k) {
        final class WordFreq implements Comparable<WordFreq> {
            String word;
            int freq;

            public WordFreq(final String w, final int c) { word = w; freq = c; }

            @Override
            public int compareTo(final WordFreq other) {
                return Integer.compare(this.freq, other.freq);
            }
        }
        final Map<String, Integer> frequencyMap = new HashMap<String, Integer>();
        final PriorityQueue<WordFreq> topKHeap = new PriorityQueue<WordFreq>(k);

        final String[] words = stream.toLowerCase().trim().split(" ");
        for (final String word : words) {
            int freq = 1;
            if (frequencyMap.containsKey(word)) {
                freq = frequencyMap.get(word) + 1;
            }

            // update the frequency map
            frequencyMap.put(word, freq);
        }

        // Build the topK heap
        for (final Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (topKHeap.size() < k) {
                topKHeap.add(new WordFreq(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > topKHeap.peek().freq) {
                topKHeap.remove();
                topKHeap.add(new WordFreq(entry.getKey(), entry.getValue()));
            }
        }

        // extract the top K
        final String[] topK = new String[k];
        int i = 0;
        while (topKHeap.size() > 0) {
            topK[i++] = topKHeap.remove().word;
        }

        return topK;
    }

    public static void main(String[] args) {
        int k = 2;
        String[] topWords = topKWords("aa bb aa aa bb cc cc cc bb cc xx yy", k);
        for(int i=0;i<k;i++) {
            System.out.println(topWords[i]);
        }
    }
}
