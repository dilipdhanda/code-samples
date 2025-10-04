package com.c1.Comparable_Comparator;

import java.util.ArrayList;
import java.util.Collections;

public class Comparable_FindGapsInRangePairs {

    static class MinMax implements Comparable<MinMax>  {
        int min;
        int max;
        public MinMax(int min, int max){
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(MinMax o) {
            if (o.min < this.min) {
                return 1; // larger
            } else if (o.min > this.min) {
                return -1; // smaller
            }
            return 0; // 0 is equal
        }

        @Override
        public String toString(){
            return "{"+min+","+max+"}";
        }
    }

    public static void main(String[] args)
    {
        ArrayList<MinMax> inp = new ArrayList<MinMax>();
        int arr[][] = null;
        arr = new int[][]{ {7, 9}, {1, 3}, {2, 4}, {3, 5} };
        arr = new int[][]{ {1, 3}, {1, 12}, {2, 4}, {6, 8} };
        arr = new int[][]{ {1, 3}, {9, 12}, {2, 4}, {6, 8} };

        for(int[] p : arr) { inp.add( new MinMax(p[0],p[1])); }

        Collections.sort(inp);
        System.out.println("Input - sorted - "+inp);

        ArrayList<MinMax> res = new ArrayList<MinMax>();
        Integer res1 = null;
        for (MinMax e : inp) {
            if (res1 == null) {
                res1 = e.max;
                continue;
            }
            if (res1 >= e.min) { // check for overlap
                if (e.max > res1) { // this account for {1,12}, {2,4} kind of situation, where prev max is more than curr range max
                    res1 = e.max; // this entry overlaps
                }
                // System.out.println("res1 is "+res1);
            } else { // no overlap
                System.out.println(res1+", "+e.min); // this is overlap
                res1 = e.max;
            }
        }
    }
    /* TwinHealth - Sagar - https://codeshare.io/AdZ71r

At Twin, we get sensor data which has start & end time along with metabolic data. Based on this data we do precision nutrition/medicine & train our data models.
Many times we do not get data for long Intervals & in this exercise, we want to solve for that. Given N set of time Intervals, the task is to find the missing Intervals with the given set of intervals.
The interval will have start and end where start < end. For sake of simplicity, we have removed metabolic data from Interval.

Example
Input: Interval arr[] = { {1, 3}, {2, 4}, {3, 5}, {7, 9} }
Output: [5, 7]
Explanation: The only interval which doesn’t overlaps with the other intervals is [5, 7].

Input: Interval arr[] = { {1, 3}, {9, 12}, {2, 4}, {6, 8}, }
Output: [4, 6] [8, 9]
Explanation: There are two intervals which don’t overlap with other intervals are [4, 6], [8, 9].

public Interval[] findInterval (Interval[] arr) {

Input: Interval arr[] = { {1, 3}, {2, 4}, {3, 5}, {7, 9} }
Output: [5, 7]

1,3 - 2,4 - 3,5 - 7,9 #
min_max set1 1,3 -> 1,4 -> 1,5 -> PritotyQuere - some binary already sorted ->
min_max set2 7,9

itr thru mn_max sets 1,5 - 7,9 -> copareTo() to sort by min -> Collections.sort() -> 1,5 - 7,9
out put would be midle of each two entries -> 5,7

 */

}
