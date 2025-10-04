package com.c7.a_1_c;

import java.util.Stack;

public class printNextGreaterElement_withStack_O_N {

    public static void main(String[] args) {
        printNextGreaterElementa_All(new int[]{16, 7, 7, 2, 7, 15});
    }
    public static void printNextGreaterElementa_All(int[] arr) {
        if (arr.length == 0) { System.out.println(); return; }

        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {  // start with 1, 0 on stack already
            int next = arr[i];
            if (!stack.isEmpty()) { // this check needed as previous iterations may have drained it out
                int popped = stack.pop();
                while ( next > popped) { // while needed as new next may be greater than many previous numbers
                    System.out.println(popped + " --> " + next);
                    if (stack.isEmpty()) {
                        break;
                    }
                    popped = stack.pop();
                }
                // put popped back on stack
                if (popped >= next) {   // = takes care of duplicates (like 7), that are sequential or not
                    stack.push(popped); // put popped back on stack as we need to find next greater for that also
                }
            }
            stack.push(next); // no matter what happens above, next must be pushed on to the stack
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " --> " + -1);
        }
    }
}
