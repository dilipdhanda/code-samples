package com.others.a_1_main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class LRUCache2 {
    PriorityQueue<Integer> queue = null;
    HashMap<Integer, Pair> cache = new HashMap();
    int cap = 0;
    int globalAge = 0;
    private class Pair
    {
        int value;
        int age;
        public Pair(int value, int age)
        {
            this.value = value;
            this.age = age;
        }

        @Override
        public String toString()
        {
            return "" + value + ":" + age;
        }
    }
    class TComparator<Integer> implements Comparator<Integer>
    {

        @Override
        public int compare(Integer arg0, Integer arg1)
        {
            if (cache.get(arg0).age < cache.get(arg1).age)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
    public LRUCache2(int capacity)
    {
        cache = new HashMap();
        cap = capacity;
        TComparator<Integer> comparator = new TComparator();
        queue = new PriorityQueue<Integer>(capacity, comparator);
    }

    public int get(int key)
    {
        Pair entry = cache.get(key);
        if (null != entry)
        {
            cache.put(key, new Pair(entry.value, ++globalAge));
            queue.remove(key);
            queue.add(key);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value)
    {
        cache.put(key, new Pair(value, ++globalAge));
        if (cache.size() > cap)
        {
            Integer expired = queue.poll();
            cache.remove(expired);
        }
        queue.remove(key);
        queue.add(key);
    }

    public static void main(String[] args)
    {
        LRUCache2 ca = new LRUCache2(4);
        ca.put(1,10);
        ca.put(2,10);
        ca.put(3,10);
        ca.put(1,10);
        ca.put(4,10);
        ca.put(5,10);
        ca.put(1,10);
    }
}