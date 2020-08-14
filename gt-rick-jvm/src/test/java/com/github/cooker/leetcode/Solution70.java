package com.github.cooker.leetcode;

import org.junit.Test;

/**
 * grant
 * 13/8/2020 10:34 下午
 * 描述：
 */
public class Solution70 {

    public int climbStairs(int n) {
        int[] a = new int[n+1];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i < n; i++) {
            a[i] = a[i-2] + a[i-1];
        }
        return a[n];
    }



    @Test
    public void sa(){
        Solution70 so = new Solution70();
        System.out.println(so.climbStairs(5));
    }
}
