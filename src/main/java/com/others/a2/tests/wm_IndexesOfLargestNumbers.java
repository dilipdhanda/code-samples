package com.others.a2.tests;
import java.util.List;
import java.util.Arrays;

// Walmart - Pratik shah - you struggled on a simple problem actually and gave him reason to doubt
public class wm_IndexesOfLargestNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 8, 2, 7);

        int[] result = findTwoLargestIndexes(numbers);
        System.out.println("Index of the largest number: " + result[0]);
        System.out.println("Index of the second largest number: " + result[1]);
    }

    public static int[] findTwoLargestIndexes(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two elements.");
        }
        int largestIndex = -1;
        int secondLargestIndex = -1;
        // Integer.MIN_VALUE - Instead of null, use Integer.MIN_VALUE, etc. to avoid ugly code,
        // using nulls for largest negative/positive values
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > largest) {
                secondLargest = largest;
                secondLargestIndex = largestIndex;
                largest = numbers.get(i);
                largestIndex = i;
            } else if (numbers.get(i) > secondLargest && i != largestIndex) {
                secondLargest = numbers.get(i);
                secondLargestIndex = i;
            }
        }
        return new int[]{largestIndex, secondLargestIndex};
    }

    /* maybe Pratik Shah - Walmart - you struggled on a simple problem actually and gave him reason to doubt,
    that's why no clear answer even after 2+ weeks
    ArrayList<Integer> l1 = 3, 6, 8, 1, 9
    second largest
    Integer i1 = null; // one of two largest #
    Integer i2 = null; // one of two largest #
    Integer idx = null; // 1 or 2 - depicting the max index
    loop int i in l1 # list
        if i1 == null
            i1 = i # i1 = 3
            continue
        if i2 == null
            i2 = i  # i2 = 6

        i1 3 8
        i2 6
        i  8   1 9

        if i > i2      # i = 8
            if i1 > i2  # add in between check
                i2 = i
                continue
             else
                i1 = i
                continue;

         else if i > i1
            if i1 > i2
                i1 = i
                continue
             else
                i2 = i
                continue;

         if i1 > i2
            output i1
         else
            output i2
     */
}
