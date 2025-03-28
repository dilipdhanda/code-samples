package a_1_b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RepeatedWords_fromFile {
    public static void findRepeatedWords(String filePath) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.trim().isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                if (entry.getValue() > 1) {
                    System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filePath = "src/main/resources/file.txt";
        findRepeatedWords(filePath);
    }
}

