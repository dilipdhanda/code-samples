package a_1_L;

public class In_Place_Move_Zeros_To_End {
    public static void main(String[] args){
        // Arrays.sort(array);
        Integer nums[] = {1,0,3,5,0,6,7,7,0,0,10}; // idx -1,1,
        int idx = -1;
        for(int i = nums.length -1; i >= 0 ; i--){
            if (nums[i] == 0){
                if (idx == -1){
                    continue;
                } else {
                    nums[i] = nums[idx];
                    nums[idx] = 0;
                    idx += -1;
                }
            } else {
                if (idx == -1){
                    idx = i;
                }
            }
        }
        for(int i = 0; i < nums.length; i++) {
            System.out.printf(nums[i].toString() + ' ');
        }
    }
}
