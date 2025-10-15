package com.c0;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problem: Find the count of longest continuous subsequence that has the same integer value.
 * Input: [1,2,5,8,8,8,5,5,9,10,13,5,5,5]
 * Output: 3
 */
public class MaxSeq{

    public static void main(String[] args){
        ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1,2,5,8,8,8,5,5,5,5,9,10,13,5,5,5));
        findMaxSeq(input);
        findMaxSeq(new ArrayList<Integer>());
    }

    public static Result findMaxSeq(ArrayList<Integer> input){
        if (input.isEmpty()) {
            System.out.println("Error - Input list is empty");
            return new Result(-1, -1);
        }
        int prevStart = 0;
        int prevEnd = 0;
        int currStart = 0;
        int currEnd = 0;
        int currValue = input.get(0);
        for(int i = 1; i < input.size(); ++i){
            if (input.get(i).equals(currValue)){
                continue;
            } else {
                currEnd = i;
                if (currEnd - currStart > prevEnd - prevStart){
                    prevEnd = currEnd;
                    prevStart = currStart;
                }
                currValue = input.get(i);
                currStart = i;
            }
        }
        // Check last sequence
        currEnd = input.size();
        if (currEnd - currStart > prevEnd - prevStart){
            prevEnd = currEnd;
            prevStart = currStart;
        }
        Result res = new Result(prevEnd - prevStart, input.get(prevStart));
        System.out.println("Length: " + res.length + ", Value: " + res.value);
        return res;
    }

    public static class Result {
        public final int length;
        public final int value;
        public Result(int length, int value) {
            this.length = length;
            this.value = value;
        }
    }
}