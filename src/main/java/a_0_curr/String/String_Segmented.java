package a_0_curr.String;

import java.util.Scanner;
import java.util.HashSet;

public class String_Segmented {

    public static void isSegmented(String s1, HashSet<String> wordDict){
        String s2 = s1;
        for (String word:wordDict) {
            s2 = s2.replaceAll(word,"");
            if (s2.length() == 0)
                break;
        }
        if (s2.length()>0)
            System.out.println("false");
        else
            System.out.println("true");
    }

    public static void main(String[] args){
        String s1 = "applepenapple";
        HashSet<String> wordDict = new HashSet<>();
        wordDict.add("apple");
        wordDict.add("pen");
//        isSegmented(s1, wordDict);

        Scanner s = new Scanner(System.in);
        s1 = s.next();
//        int dictSize = s.nextInt(); // not used actually but problem asked to scan second input as size
        String wholeDict = s.next();
        wordDict.clear();
        for(String w: wholeDict.split(" ")){
            wordDict.add(w);
        }
        isSegmented(s1, wordDict);
    }
}
