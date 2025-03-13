package b;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class EnglishWordsToNumber {

    static class Entry {
        public Entry(String opr, Integer val){
            this.opr = opr;
            this.val = val;
        }
        String opr;
        Integer val;
    }

    public static void main(String[] args) {

//        HashMap<String, Integer> m = new HashMap(); // valid multipliers
//        m.put("thousand", 1000); m.put("hundred", 100); m.put("million", 1000000);
//
//        HashMap<String, Integer> w = new HashMap();
//        w.put("zero", 0); w.put("two", 2); w.put("three", 3); w.put("four", 4); w.put("five", 5);
//        w.put("six", 6); w.put("seven", 7); w.put("eight", 8); w.put("nine", 9); w.put("ten", 10);
//
//        HashSet<String> opr = new HashSet(); // valid operators allowed
//        opr.add("minus"); opr.add("plus"); opr.add("and"); opr.add("val");
//
//        String inp = "Fifty One Thousand Two Hundred and Three";
//        inp = inp.toLowerCase();
//
//        String [] words = inp.split(" ");
//        Stack<Entry> s  = new Stack();
//
//        Integer val = 0; // assuming null or empty string is zero
//        for(String word : words) {
//
//            if (opr.contains(word)) {
//                s.add(new Entry(word, val));
//                val = 0;
//            }
//
//            if (w.containsKey(word)) {
//                val = val + w.get(word); // if you got four earlier, you would end up with 400 now
//                s.push("val", val);
//                val = 0;
//            }
//            // check word is in haspmap w, throw pasrse error if there is word say xyx
//            val = val + w.get(word); // assumes zero to nine to
//        }
//        System.out.println(s);
//            // Fifty One Thousand Two Hundred and Threen
//            // 50 + 1 = 51
//            // 51 to 51000 - val - Stack {"val", 200} {"val",5100} - "val", minus, plus, and - ad functionaluty
//            //
//// Four hundred minus fifty
//// Stack
//// "null", 50
//// minus, 400
//// unwind the stack = 450

    }

    /*
    " twenty",
                " thirty",
                " forty",
                " fifty",
                " sixty",
                " seventy",
                " eighty",
                " ninety"
        " eleven",
                " twelve",
                " thirteen",
                " fourteen",
                " fifteen",
                " sixteen",
                " seventeen",
                " eighteen",
                " nineteen"

     */
}

