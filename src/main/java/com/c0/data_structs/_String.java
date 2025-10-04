package com.c0.data_structs;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _String {
    public static void main(String args[]) {

        // Normalize your strings before processing
        List<String> animals = List.of("panda", "fish", "horse", "cat", "nothing");
        animals.stream().forEach(s -> System.out.println(s.trim()));
        List<String> trimmed = animals.stream().map(s -> s.trim()).collect(Collectors.toList());

        String apples = "Apples";
        char firstCharacter = apples.charAt(0);
        System.out.println(firstCharacter);
        System.out.println(apples.indexOf('e'));
        String sub = apples.substring(2, 4);
        System.out.println(sub);
        System.out.println(apples.contains("App"));
        char[] applesArray = apples.toCharArray();

        String s1 = "aabccddda";
        String out = ""; // can use StringBuilder
        Character p = null;
        Integer count = 0;

        for (int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            if (p == null) {
                p = c;
                count = 1;
            } else if (!p.equals(c)) {
                out = out + p + Integer.toString(count);
                p = c;
                count = 1;
            } else {
                count++;
            }
        }
        if (count > 0){
            out = out + p + Integer.toString(count);
        }
        System.out.println(out);

        String text = "The giant panda has an insatiable appetite for bamboo. " +
                "A typical animal eats half the day—a full 12 out of every 24 " +
                "hours—and relieves itself dozens of times a day. It takes 28 " +
                "pounds of bamboo to satisfy a giant panda's daily dietary needs. " +
                "Pandas will sometimes eat birds or rodents as well.";
        String[] sentences = text.split("\\. ");
        System.out.println("The text has " + sentences.length + " sentences.");
        String[] words = text.split(" |-");
        System.out.println("The text has " + words.length + " words.");
        System.out.println(Arrays.asList(words));
        System.out.println("The text has " + text.length() + " characters.");
    }
}
