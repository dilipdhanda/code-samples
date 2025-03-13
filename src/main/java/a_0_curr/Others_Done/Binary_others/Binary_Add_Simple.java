package a_0_curr.Others_Done.Binary_others;

import java.util.Scanner;

public class Binary_Add_Simple {

    public static void binayAdd(String s1, String s2){
        int num1 = Integer.parseInt(s1,2);
        int num2 = Integer.parseInt(s2,2);
        int sum = num1 + num2;
        String f = Integer.toBinaryString(sum);
        System.out.println(f);
    }

    public static void main(String[] args){
        String s1 = "100";
        String s2 = "111010";

        Scanner s = new Scanner(System.in);
        s1 = s.next();
        s2 = s.next();

        binayAdd(s1, s2);
    }
}
