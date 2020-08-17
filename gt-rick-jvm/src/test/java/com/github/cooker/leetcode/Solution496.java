package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * ZoomGrant 2020/8/16 9:55
 */
public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int j = 0;j< nums1.length;j++) {
            int index = map.get(nums1[j]);
            int x = -1;
            for (int i = index + 1; i < nums2.length; i++) {
                if (nums1[j] < nums2[i]){
                    x = nums2[i];
                    break;
                }
            }
            nums[j] = x;
        }
        return nums;
    }

    @Test
    public void sa(){
        Assert.assertTrue(Arrays.equals(nextGreaterElement(new int[]{4,1,2},new int[]{1,3,4,2}), new int[]{-1,3,-1}));
        Assert.assertTrue(Arrays.equals(nextGreaterElement(new int[]{2,4},new int[]{1,2,3,4}), new int[]{3,-1}));
    }
}
