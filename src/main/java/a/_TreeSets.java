package a;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class _TreeSets {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet();
        /* TreeSet is implemented using a self-balancing binary search tree
        (usually a Red-Black Tree), which ensures that the tree remains balanced and
        maintains the logarithmic height. Set stores only keys, no values as in map.
         */
        treeSet.add(1);
        treeSet.add(300);
        treeSet.add(47);
        treeSet.add(6);
        System.out.println(treeSet);
        treeSet.add(6);
        System.out.println(treeSet);

//        Set<String> wordSet = new TreeSet<>(); // [bear, giraffe, tiger, wolf]
        Set<String> wordSet = new TreeSet(Comparator.comparing(String::length)); // [bear, tiger, giraffe]
        wordSet.add("tiger");
        wordSet.add("giraffe");
        wordSet.add("bear");
        System.out.println(wordSet);
        wordSet.add("wolf");
        System.out.println(wordSet);
        wordSet.remove("giraffe");
        wordSet.add("wolf"); // not added as duplicates not allowed, wold is same length as bear
        System.out.println(wordSet);
    }
}
