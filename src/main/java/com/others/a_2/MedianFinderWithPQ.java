package com.others.a_2;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinderWithPQ {

    PriorityQueue<Integer> leftHalf;
    PriorityQueue<Integer> rightHalf;

    public MedianFinderWithPQ(){
        leftHalf = new PriorityQueue<Integer>(Collections.reverseOrder());
        rightHalf = new PriorityQueue<Integer>();
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        leftHalf.offer(num); // offer adds or Inserts the specified element into this priority queue
        rightHalf.offer(leftHalf.poll()); // move top of first heap to second heap

        if(leftHalf.size() < rightHalf.size()){ // balance heaps if needed
            leftHalf.offer(rightHalf.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if(leftHalf.size()==rightHalf.size()){
            return (double)(leftHalf.peek()+(rightHalf.peek()))/2;
        }else{
            return leftHalf.peek();
        }
    }

    public static void main(String[] args){
        MedianFinderWithPQ c = new MedianFinderWithPQ();
        c.addNum(8); c.addNum(9); c.addNum(10); c.addNum(6); c.addNum(7);
        System.out.println("median is " + c.findMedian());
        System.out.println("left half " + c.leftHalf);
        System.out.println("right half " + c.rightHalf);
    }
}

