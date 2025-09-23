package com.others.a2.tests;

import java.util.Arrays;

public class ebay_StonesSorter { // eBay - Akshay - 07/24/24

    public static void sortStones(String[] stones) {
        int low = 0;
        int mid = 0;
        int high = stones.length - 1;

        while (mid <= high) {
            switch (stones[mid]) {
                case "orange":
                    swap(stones, low, mid);
                    low++;
                    mid++;
                    break;
                case "white":
                    mid++;
                    break;
                case "green":
                    swap(stones, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(String[] stones, int i, int j) {
        String temp = stones[i];
        stones[i] = stones[j];
        stones[j] = temp;
    }

    public static void main(String[] args) {
        String[] stones = {"green", "orange", "white", "orange", "green", "white", "orange"};
        sortStones(stones);
        System.out.println(Arrays.toString(stones));
    }
}
