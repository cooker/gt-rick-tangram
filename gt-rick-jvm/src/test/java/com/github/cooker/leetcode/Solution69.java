package com.github.cooker.leetcode;

import org.junit.Test;

/**
 * grant
 * 13/8/2020 7:27 上午
 * 描述：
 */
public class Solution69 {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double last = 0.0;
        double res = 1.0;
        while (res != last) {
            last = res;
            res = (res + x / res) / 2;
        }
        return (int)res;
    }

    @Test
    public void ma(){
        Solution69 so = new Solution69();
        System.out.println(so.mySqrt(8));
    }
}
