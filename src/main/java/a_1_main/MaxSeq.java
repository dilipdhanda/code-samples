package a_1_main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Problem: Find the count of longest continuous subsequence that has the same integer value.
 * Input: [1,2,5,8,8,8,5,5,9,10,13,5,5,5]
 * Output: 3
 */
public class MaxSeq{

    public static void main(String[] args){
        ArrayList<Integer> l = new ArrayList<Integer>(Arrays.asList(1,2,5,8,8,8,5,5,9,10,13,5,5,5));
        Iterator<Integer> itr = l.iterator();
        Integer num_max = 0; // assuming all number are > 0, I could use OptionalNull logic = Optional(<Integfer>)
        Integer seq_max = 0;
        Integer curr_num = null;
        Integer curr_seq = 0; // to immediately replace with

        while (itr.hasNext()){
            Integer num = itr.next();
            if (curr_num == null){
                curr_num = num;
                curr_seq = 1;
                continue;
            }
            if (num.equals(curr_num)) {
                ++curr_seq;
            } else {
                if (curr_seq.intValue() > seq_max.intValue()) {
                    num_max = curr_num;
                    seq_max = curr_seq;
                }
                curr_num = num;
                curr_seq = 1;
            }
        }
        // handle last seq of values still in tmp variables
        if (curr_seq > seq_max) {
            num_max = curr_num;
            seq_max = curr_seq;
        }
        System.out.println(num_max.toString() + ", " + seq_max.toString());
    }
}
