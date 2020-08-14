package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * grant
 * 11/8/2020 6:58 上午
 * 描述：
 */
public class Solution66 {
    public int[] plusOne(int[] digits) {
        int jw = 1;
        for (int i = digits.length - 1; i >= 0 ; i--) {
             digits[i] += jw;
             jw = digits[i] / 10;
             digits[i] = digits[i] % 10;
        }
        if (jw > 0){
            int[] nx = new int[digits.length + 1];
            nx[0] = jw;
            System.arraycopy(digits, 0, nx, 1, digits.length);
            return nx;
        }
        return digits;
    }


    @Test
    public void add(){
        Solution66 so = new Solution66();
        Arrays.equals(so.plusOne(new int[]{1,2,3}), new int[]{1,2,4});
        Arrays.equals(so.plusOne(new int[]{0}), new int[]{1});
        Arrays.equals(so.plusOne(new int[]{9}), new int[]{1,0});
    }
}
