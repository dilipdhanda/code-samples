package a_1_L;

import java.util.Scanner;

public class PrimeNumbers_Complex {

    public static void main(String[] args) {
        System.out.println("Input the number(n):");
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        int ans = check(c);
        System.out.println("Number of prime numbers which are less than or equal to n.:");
        System.out.println(ans);
    }
    static int check(int c) {
        boolean[] prime = new boolean[c+1];
        int count = 0;
        for(int i = 2; i <= Math.sqrt(c); i++) {
            for(int j = i + i; j <= c; j += i) {
                prime[j] = true;
            }
        }
        for(int i = 2; i <= c; i++) {
            if(!prime[i]) {
                count++;
            }
        }
        return count;
    }
}