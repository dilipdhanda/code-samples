package a_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrack_Subsets_WithDuplicates {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args){

        int[] nums = {3, 2, 2, 5};
        List<List<Integer>> results = subsetsWithDup(nums);
        System.out.println("total Solutions "+results.size()+", elmenst -> "+results);
    }
}
