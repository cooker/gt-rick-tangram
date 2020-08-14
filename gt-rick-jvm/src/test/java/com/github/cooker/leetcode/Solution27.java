package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * grant
 * 9/8/2020 5:51 下午
 * 描述：
 */
public class Solution27 {

    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int first = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[first + 1] = nums[i];
                first ++;
            }
        }

        return first + 1;
    }


    @Test
    public void sa(){
        Solution27 s = new Solution27();
        Assert.assertEquals(s.removeElement(new int[]{0,1,2,2,3,0,4,2}, 2), 5);
    }
}
