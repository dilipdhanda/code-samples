package a_0_lang;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class _Queue_plain_and_PriorityQueue {
    public static void main(String[] args) {
        /*
        LinkedList is pretty standard Queue impl
        Queue<String> q = new ArrayDeque<>();  // faster to access - more common Queue impl
        Queue<String> q = new LinkedList<>(); // faster for add and delete, esp id add/delete in loop
         */
        queue_as_LinkedList();
        queue_as_PriorityQueue();


    }

    public static void queue_as_PriorityQueue() {
        Queue<String> q = new PriorityQueue<>(); // Retrieval Order is Natural Order - Queue is always sorted, and allows duplicates
        q.offer("Person One");q.offer("Person Two");q.offer("Person Three");
        q.offer("Person Two"); // allows duplicates
        System.out.println(q);
        System.out.println(q.peek()); // wont remove
        System.out.println(q.poll()); // will remove - poll in Queue, pop in Stack
        System.out.println(q);
    }
    public static void queue_as_LinkedList(){
        Queue<String> queue = new LinkedList();
        queue.add("Sam");
        queue.add("Anna");
        queue.add("Heidi");
        queue.add("Sally");
        queue.add("Charlotte");
        System.out.println(queue);
        System.out.println("Next up! " + queue.peek());
        while(!queue.isEmpty()) {
            String removed = queue.remove();
            System.out.println(removed);
        }
    }
}
