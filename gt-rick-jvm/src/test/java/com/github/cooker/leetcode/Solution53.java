package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * grant
 * 9/8/2020 8:57 下午
 * 描述：
 */
public class Solution53 {

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int m: nums) {
            if (sum > 0){
                sum += m;
            }else {
                sum = m;
            }
            max = Math.max(max, sum);
        }
        max = Math.max(max, sum);
        return max;
    }

    @Test
    public void sa(){
        Solution53 so = new Solution53();

        Assert.assertEquals(so.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}), 6);
        Assert.assertEquals(so.maxSubArray(new int[]{-2}), -2);
    }
}
