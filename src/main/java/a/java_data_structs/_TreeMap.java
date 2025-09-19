package a.java_data_structs;

import java.util.TreeMap;

public class _TreeMap {

    public static void main (String[] args)
    {
        // find frequency of smallest integer in list, use TreeMap
        TreeMap<Integer, Integer> tMap = new TreeMap<Integer, Integer>();
    /*
    TreeMap stores its entries in a red-black tree, which means that the keys are sorted according to their
    natural ordering, or by a Comparator provided at map creation time. This ordering is maintained as entries
    are inserted and removed.
    Performance: The performance of TreeMap is generally O(log n) for containsKey, get, put, and remove operations,
    which is consistent due to the nature of the tree structure.
    TreeMap is a good choice when you need a map whose entries are sorted by key, or when you need to retrieve keys
    in a sorted order.

    HashMap stores its entries in a hash table and does not guarantee any specific order of the entries over time.
    The order can appear random and may change when items are inserted or removed.
    performance of HashMap operations (containsKey, get, put, remove) is typically O(1), assuming the hash function
    disperses the elements properly among the buckets. However, in the worst case (e.g., poor hash function
    leading to collisions), the performance can degrade to O(n).

    TreeSet is implemented using a self-balancing binary search tree (usually a Red-Black Tree),
    which ensures that the tree remains balanced and maintains the logarithmic height.

    HashMap Suitable for cases where order doesn't matter, and fast access is important, O(1) for read, write, delete

    LinkedHashMap maintains the insertion order, and achieves this by using a doubly-linked list in addition to the
    hash table. Bit slower due to the linked list maintenance and more memory.
     */

        int arr[] = {10, 34, 5, 10, 3, 5, 34, 5, 10, 3};
        for (int i = 0; i < arr.length; i++)
        {
            Integer count = tMap.get(arr[i]);
            if (tMap.get(arr[i]) == null) tMap.put(arr[i], 1); // first occur
            else                          tMap.put(arr[i], ++count); // elements exists
        }
        tMap.entrySet().stream().forEach(System.out::println);
        System.out.println("Frequency of smallest integer in list "
                +tMap.firstKey()+": "+tMap.get(tMap.firstKey())); // .lastKey()
        Long myLong = 21L; // Autoboxing
        System.out.println(myLong);
    }
}