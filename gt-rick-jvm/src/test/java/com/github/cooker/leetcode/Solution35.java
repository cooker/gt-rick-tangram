package com.github.cooker.leetcode;

import org.junit.Assert;

/**
 * grant
 * 9/8/2020 6:58 下午
 * 描述：
 */
public class Solution35 {

    //直接查找
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] >= target) break;
        }
        return i;
    }


    public int searchInsert2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
           int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution35 so = new Solution35();

        Assert.assertEquals(so.searchInsert2(new int[]{1,3,5,6}, 5), 2);
        Assert.assertEquals(so.searchInsert2(new int[]{1,3,5,6}, 2), 1);
        Assert.assertEquals(so.searchInsert2(new int[]{1,3,5,6}, 7), 4);
        Assert.assertEquals(so.searchInsert2(new int[]{1,3,5,6}, 0), 0);
    }
}
