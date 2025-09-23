package com.others.a_1_c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Stack;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

public class Algorithms {

    public static void main(String[] args) {

        Arrays_binarySearch();
        printNextGreaterElement_w_Stack(new int[]{16, 7, 2, 15});
        int[] arr1 = { -9, 3, 2, -8, 12, -16 };
        int[] arr2 = { 0, -3, -8, -35, 40, 20, 7 };
        findEvenNum_Streams_Predicate(arr1, arr2);
        findEvenNums_Simple(arr1, arr2);
        reverseInPlace_Arrays(arr1);
        reverseWords("abc 123 dfg");
    }

    public static void printNextGreaterElement_w_Stack(int[] arr) {
        if (arr.length == 0) {
            System.out.println();
            return;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            int next = arr[i];
            if (!stack.isEmpty()) {
                int popped = stack.pop();
                while (popped < next) {
                    System.out.println(popped + " --> " + next);
                    if (stack.isEmpty()) {
                        break;
                    }
                    popped = stack.pop();
                }

                if (popped > next) {
                    stack.push(popped);
                }
            }
            stack.push(next);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " --> " + -1);
        }
    }

    public static void reverseInPlace_Arrays(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++){
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;

        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void Arrays_findEvenNums() {
        int[] arr1 = { -9, 3, 2, -8, 12, -16 };
        int[] arr2 = { 0, -3, -8, -35, 40, 20, 7 };
        Arrays.stream(findEvenNums_Simple(arr1, arr2))
                .forEach(System.out::println);
        System.out.println();
        Arrays.stream(findEvenNum_Streams_Predicate(arr1, arr2))
                .forEach(System.out::println);
    }

    public static int[] findEvenNum_Streams_Predicate(int[] arr1, int[] arr2) {
        IntPredicate isEvenPred = num -> num % 2 == 0;
        return Stream.of(arr1, arr2)
                .flatMapToInt(Arrays::stream)
                .filter(isEvenPred)
                .toArray();
    }

    public static int[] findEvenNums_Simple(int[] arr1, int[] arr2) {
        ArrayList<Integer> result = new ArrayList<>();

        for (int num : arr1) {
            if (num % 2 == 0) {
                result.add(num);
            }
        }

        for (int num : arr2) {
            if (num % 2 == 0) {
                result.add(num);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int Arrays_binarySearch() {
        int[] arr = { 1, 2, 3, 4, 5, 6 , 7, 80};
        int item = 9;
        return Arrays.binarySearch(arr, item);
    }
    public static boolean binarySearch() {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        int item = 8;
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == arr[mid]) {
                return true;
            } else if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    public static void linerSearch() {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        int item = 5;
        OptionalInt result = Arrays.stream(arr).filter(x -> x == item).findFirst();
        System.out.println(result);
    }
    public static void reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        for(String w : s.split(" ")){
            sb.append(new StringBuilder(w).reverse() + " ");
        }
        System.out.println(sb);
    }
}
