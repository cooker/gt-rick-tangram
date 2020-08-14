package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * grant
 * 9/8/2020 6:03 下午
 * 描述：
 */
public class Solution28 {

    public int strStr(String haystack, String needle) {
        if (needle == null) return -1;
        return haystack.indexOf(needle);
    }

    @Test
    public void say(){
        Solution28 so = new Solution28();

        Assert.assertEquals(so.strStr("hello", "ll"), 2);
    }
}
