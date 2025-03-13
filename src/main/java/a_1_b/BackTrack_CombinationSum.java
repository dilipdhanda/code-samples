package a_1_b;

import java.util.ArrayList;
import java.util.List;

public class BackTrack_CombinationSum {

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
//        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> solutions, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return; // Error - stop this path and backtrack by removing last added element
        else if(remain == 0) solutions.add(new ArrayList<>(tempList)); // FOUND SOLUTION - deep copy as tempList has a solution
        else{ // Solution STLL POSSIBLE, so continue this path
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(solutions, tempList, nums, remain - nums[i], i+1); // start not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args){

        int[] nums = {3, 2, 5, 2, 10, 7}; // [[2, 2, 5], [2, 7], [2, 7]]
        int target = 9;
        List<List<Integer>> results = combinationSum(nums, target);
        System.out.println("total Solutions "+results.size()+", elements -> "+results);
    }
}
