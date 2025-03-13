package a_1_to_do;

// Java program to implement LRU cache using LinkedHashSet
import java.util.*;

class LRUCache1 {

    Set<Integer> cache;
    int capacity;

    public LRUCache1(int capacity)
    {
        this.cache = new LinkedHashSet<Integer>(capacity);
        this.capacity = capacity;
    }

    public boolean get(int key)
    {
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        return true;
    }

    /* Refers key x with in the LRU cache */
    public void refer(int key)
    {
        if (get(key) == false)
            put(key);
    }

    // displays contents of cache in Reverse Order
    public void display()
    {
        LinkedList<Integer> list = new LinkedList<>(cache);
        // The descendingIterator() to reverse sequential order
        Iterator<Integer> itr = list.descendingIterator();

        while (itr.hasNext())
            System.out.print(itr.next() + " ");
    }

    public void put(int key)
    {

        // remove top key if cache reached capacity
        if (cache.size() == capacity) {
            int firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }

        cache.add(key);
    }

    public static void main(String[] args)
    {
        LRUCache1 ca = new LRUCache1(4);
        ca.refer(1);
        ca.refer(2);
        ca.refer(3);
        ca.refer(1);
        ca.refer(4);
        ca.refer(5);
        ca.refer(1);
        ca.display();
    }
}
