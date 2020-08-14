package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * grant
 * 8/8/2020 7:38 下午
 * 描述：
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        boolean[] str = new boolean[128];
        int num = 0;
        int n = s.length();
        int rk = 0;
        for (int i = 0; i < n; i++) {
            while (rk < n && str[s.charAt(rk)] != true){
                str[s.charAt(rk)] = true;
                rk ++;
            }
            num = Math.max(rk - i, num);

            str[s.charAt(i)] = false;

        }
        return num;
    }


    @Test
    public void num(){
        Assert.assertSame(new Solution3().lengthOfLongestSubstring("dvdf"), 3);
        Assert.assertSame(new Solution3().lengthOfLongestSubstring("aab"), 2);
        Assert.assertSame(new Solution3().lengthOfLongestSubstring("abcabcbb"), 3);
        Assert.assertSame(new Solution3().lengthOfLongestSubstring("bbbbb"), 1);
        Assert.assertSame(new Solution3().lengthOfLongestSubstring("pwwkew"), 3);
    }

}
