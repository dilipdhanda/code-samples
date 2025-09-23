package com.others.a2.Others_Done.Binary_others;

import java.util.LinkedList;
import java.util.Queue;

public class PrintBinaryNumbers {
    public static void main(String[] args) {
        printBinary_w_Integer_toBinaryString(10); // print first 10 binary numbers

        printBinaryUsingQueue(6); // print first 6 binary numbers
//        printBinaryUsingQueue(-9);

    }

    public static void printBinaryUsingQueue(int n) {
        if (n <= 0) {
            System.out.println("Nothing to print\n");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        for (int i = 1; i <= n; i++) {
            Integer current = queue.remove();
            System.out.println(current);

            // adding two, but we print only one above, so queue has 2*n items in it
            // Optimization to check if queue size is n, then just print out the queue, less memory used
            if (queue.size() >= n/2) {
                i++; // needed as i++ happens automatically at end ofu outer for loop
                while (!queue.isEmpty() && i <= n){
                    i++;
                    System.out.println(queue.remove());
                }
                return;
            }
            queue.add(current * 10);     // to create new num with 0 appended
            queue.add(current * 10 + 1); // to create new num with 1 appended
        }

        System.out.println(); // queue still have n number of items left, but we do not print them
    }
    public static void printBinary_w_Integer_toBinaryString(int n) {
        if (n <= 0) {
            System.out.println("Nothing to print\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(Integer.toBinaryString(i));
        }

        System.out.println();
    }

}
