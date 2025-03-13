package a_1_L;

import java.util.Scanner;
public class CountPrimes_Simple {
    public static void main(String[] args) {
        System.out.println("Input the number(n):");
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        int prime_ctr = 0;
        for (int i = 2; i <= c; i++) {
            if (Check_Prime(i)) {
                prime_ctr++;
            }
        }
        System.out.println("Number of prime numbers which are less than or equal to " + c + ": " + prime_ctr);
    }
    public static boolean Check_Prime(int n) {
        for (int divisor = 2; divisor <= n / 2; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }
}