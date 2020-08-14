package com.github.cooker.leetcode;

import org.junit.Assert;

/**
 * grant
 * 8/8/2020 10:02 下午
 * 描述：
 */
public class Solution14 {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) return strs[0];
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            min = Math.min(str.length(), min);
        }
        if (min == Integer.MAX_VALUE || min < 0) return "";
        String xx = strs[0].substring(0, min);

        for (int i=0;i<min;i++){
            String tmp = xx.substring(0, min - i);
            int j = 0;
            for (;j<strs.length;j++){
                if (!strs[j].substring(0, min - i).equals(tmp)) break;
            }
            if (j>=strs.length) return tmp;
        }

        return "";
    }


    public static void main(String[] args) {
        Assert.assertEquals(new Solution14().longestCommonPrefix(new String[]{"flower","flow","flight"}),  "fl");
        Assert.assertEquals(new Solution14().longestCommonPrefix(new String[]{"dog","racecar","car"}),  "");
        Assert.assertEquals(new Solution14().longestCommonPrefix(new String[]{"a"}),  "a");
        Assert.assertEquals(new Solution14().longestCommonPrefix(new String[]{"c","acc","ccc"}),  "");
        Assert.assertEquals(new Solution14().longestCommonPrefix(new String[]{"c","c"}),  "c");
    }
}
