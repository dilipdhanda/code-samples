package a_0_curr.String;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueString {

    public static String getUniqueCharacterSubstring(String str) {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        for (int start = 0, end = 0; end < str.length(); end++) {
            char currChar = str.charAt(end);
            if (visited.containsKey(currChar)) {
                start = Math.max(visited.get(currChar)+1, start);
            }
            if (output.length() < end - start + 1) {
                output = str.substring(start, end + 1);
            }
            visited.put(currChar, end);
        }
        return output;
    }
    
    public static void main(String[] args){
        String str = "abcbbbbde12";
        str = "aaacbbcabd";
        System.out.println(getUniqueCharacterSubstring(str));
        
    }
}
