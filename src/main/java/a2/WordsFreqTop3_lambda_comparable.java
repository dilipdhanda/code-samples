package a2;

import java.util.*;

public class WordsFreqTop3_lambda_comparable {

    public static void main (String[] args) {
        String sent = "aa bb aa aa bb cc cc cc bb cc xx yy";
        String[] words = sent.split(" ");
        Map<String, Integer> mapCount = new HashMap<>();
        for (String w : words) {
            Integer n = mapCount.get(w);
            n = (n == null) ? 1 : ++n;
            mapCount.put(w, n);
        }
        System.out.println("mapCount -> "+mapCount);

        List<String> candidates = new ArrayList(mapCount.keySet());
        Collections.sort(candidates,
                (w1, w2) -> mapCount.get(w1).equals(mapCount.get(w2))
                        ? w1.compareTo(w2) // if equal count, this sorts by name as we use compareTo()
                        : mapCount.get(w2) - mapCount.get(w1)); // this sorts by count
        System.out.println("candidates -> "+candidates);
        System.out.println("Result -> "+candidates.subList(0, 3)); // get top 3 words with more frequencies
    }
}

