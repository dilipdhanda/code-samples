package com.others.a2.Others_Done.Binary_others;

import java.util.ArrayList;
import java.util.Scanner;

public class Scanner_Binary_Add {

    public static void binaryAdd(String s1, String s2){
        int maxLen = Math.max(s1.length(), s2.length());
        ArrayList<Character> chars = new ArrayList<>();
        char carry = '0';
        for (int i = 0; i < maxLen; i++) {
            char b1 = s1.charAt(s1.length() - i - 1);
            char b2 = s2.charAt(s2.length() - i - 1);
            if (b1 == b2) {// case both 1 or 0
                if (b1 == '1') { // case both 1, carry 1 = result 1, carry stays 1
                    if (carry == '1') {
                        chars.add('1');
                        carry = '1';
                    } else {  // case both 0, carry 1
                        chars.add('0');
                        carry = '1';
                    }
                } else { // case both 0's
                    if (carry == '1') {
                        chars.add('1');
                        carry = '0';
                    } else {
                        chars.add('0');
                        carry = '0';
                    }
                }
            } else { // case either is 1
                if (carry == '1') {
                    chars.add('0');
                    carry = '1';
                } else {
                    chars.add('1');
                    carry = '0';
                }
            }
        }
        if (carry == '1')
            chars.add(carry);

        String f = "";
        for(int i = 0; i < chars.size(); i++){
            f = f + chars.get(chars.size()-i-1);
        }
        System.out.println(f);
    }

    public static void main(String[] args){
        String s1 = "100"; // still to add logic if str lengths are different
        String s2 = "111";

        Scanner s = new Scanner(System.in);
        s1 = s.nextLine();
        s2 = s.nextLine();

        binaryAdd(s1, s2);

    }
}
