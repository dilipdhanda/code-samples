package com.c7.a_1_c;

public class TrappingRainWater {

    public int trap(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        // Compute the maximum height to the left of each index
        maxLeft[0] = height[0];
        for(int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }

        // Compute the maximum height to the right of each index
        maxRight[n-1] = height[n-1];
        for(int i = n-2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }

        // Compute the trapped water at each index and sum it up
        int totalWater = 0;
        for(int i = 0; i < n; i++) {
            totalWater += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trap(height));  // Output: 6
    }
}

