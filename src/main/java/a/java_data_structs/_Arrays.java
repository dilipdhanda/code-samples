package a.java_data_structs;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _Arrays {
    public static void main(String[] args){
        String[] words = {"tea", "eat", "apple", "ate", "vaja", "cut", "java", "utc"};
        words = new String[]{"aa", "bb"};
        Arrays.stream(words).forEach(x -> System.out.print(x + ", "));
        System.out.println();
        // System.out.println(words); // just prints reference as string

        int[] arr = {1,3,6,3,4};
        arr = IntStream.of(9,1,2,3,4,4,4,6,9).toArray();
        arr = new int[]{3,4};
        Arrays.stream(arr).forEach(x -> System.out.print(x + ","));
    }
}
