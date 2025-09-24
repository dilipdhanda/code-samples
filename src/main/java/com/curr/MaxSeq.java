package com.curr;

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
        findMaxSeq(input);
    }

    public static Result findMaxSeq(ArrayList<Integer> input){
        if (input.isEmpty()) {
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
        return new Result(prevEnd - prevStart, input.get(prevStart));
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