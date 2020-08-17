package com.github.cooker.leetcode;

/**
 * ZoomGrant 2020/8/15 9:04
 */
public class Solution136 {
    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
    }
}
