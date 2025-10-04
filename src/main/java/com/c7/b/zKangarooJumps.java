package com.c7.b;

import java.io.IOException;
import java.util.Scanner;

public class zKangarooJumps {

    static String kangaroo(int x1, int v1, int x2, int v2) {

        // int loc1=x1;
        // int loc2 = x2;
        // x1 + v1*n = x2 + v2*n
        // x2-x1 = n*(v1-v2)

        // n = (x2-x1)/(v1-v2)
        // 0 3 4 2
        // n = 4-0/3-2 // n = 
        // 1 2 3 4

        // 1 3 5 7 9

        // 3 7 11 15 19

        // 0 2 5 3

        // 0 5 10 15
        //
        // 2 5 8 11
        //
        //
        //
        // n = 5/-1= -5
        //
        // n <0

        // n = 8/-8

        // if n < 0 or n is between ()

        // v2 > v1 ----NO,

        // v1==v2 ===NO

        // 2 1 1 2

        return null;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] x1V1X2V2 = scanner.nextLine().split(" ");
        int x1 = Integer.parseInt(x1V1X2V2[0]);
        int v1 = Integer.parseInt(x1V1X2V2[1]);
        int x2 = Integer.parseInt(x1V1X2V2[2]);
        int v2 = Integer.parseInt(x1V1X2V2[3]);
        String result = kangaroo(x1, v1, x2, v2);
        System.out.println(result + " " + x1 + " " + v2);
        scanner.close();

    }

}
