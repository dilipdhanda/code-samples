package com.c7.a_1_b;

public class test {
    public static void main(String[] args){
        // System.out.println(summation(new int[]{1,6,8,5,9,4,7,2}));
        int [] arr = new int[]{1,6,8,5,9,4,7,2};
        int count = 1;
        int idx = 0;
        while (idx < arr.length){
            int sum = 0;
            int i = count;
            while (idx < arr.length && i != 0) {
                sum += arr[idx];
                idx ++;
                i --;
            }
            System.out.println(sum);
            count++;
        }
    }
}
