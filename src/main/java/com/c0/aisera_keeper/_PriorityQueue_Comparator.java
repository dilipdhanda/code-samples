package com.c0.aisera_keeper;

import java.util.PriorityQueue;

/*
Operation	- Method	                Big-O Complexity	Description
Insertion	- add() / offer()	        O(log n)	Because it must maintain the heap property after insertion.
Remove - Min / Max	poll()	        O(log n)	Removes the root element (smallest/largest) and re-heaps.
Peek (find min / max)	- peek()	    O(1)	Simply returns the root without modifying the heap.
Contains(element)	- contains()	    O(n)	Linear scan — heap is not sorted, only partially ordered.
Remove(element)	- remove(Object o)	O(n)	Needs to find the element first (linear), then re-heapify.
Build heap from collection (constructor)	new PriorityQueue<>(collection)	O(n)	Uses the heapify algorithm.
 */
public class _PriorityQueue_Comparator {
    public static void main(String[] args) {

        PriorityQueue<Task> pq = new PriorityQueue<>(); // Task implements Comparable<Task>
        pq.add(new Task("Write report", 3));
        pq.add(new Task("Fix bug", 1));
        pq.add(new Task("Attend meeting", 2));

        // Retrieve tasks in order of priority
        while (!pq.isEmpty()) {
            Task t = pq.poll(); // removes and returns highest priority
            System.out.println(t.name);
        }
    }
}

class Task implements Comparable<Task> {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    // Compare by priority (lower number = higher priority)
    @Override
    public int compareTo(Task other) {
        return this.priority - other.priority;
    }
}

