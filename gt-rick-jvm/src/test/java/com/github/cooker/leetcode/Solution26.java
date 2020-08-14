package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * grant
 * 9/8/2020 5:28 下午
 * 描述：
 */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        int first = 0;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if(nums[i] != nums[first]){
                nums[first + 1] = nums[i];
                first++;
            }
        }
        return first + 1;
    }

    @Test
    public void sx(){
        Solution26 s = new Solution26();
        Assert.assertEquals(s.removeDuplicates(new int[]{1,1,2}), 2);
        Assert.assertEquals(s.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}), 5);
    }
}
