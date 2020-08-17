package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * ZoomGrant 2020/8/15 20:41
 */
public class Solution1544 {
    public String makeGood(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        int index = -1;
        for (; i <= len; i++) {
            if (sb.length() != 0)
            if ((sb.charAt(index) != s.charAt(i-1))
                    &&(sb.charAt(index) - s.charAt(i-1) == 32
                        ||sb.charAt(index) - s.charAt(i-1) == -32 )){
                sb.deleteCharAt(index);
                index--;
                continue;
            }
            index++;
            sb.append(s.charAt(i-1));
        }
        return sb.toString();
    }

    @Test
    public void sa(){
        Assert.assertEquals(makeGood("leEeetcode"), "leetcode");
        Assert.assertEquals(makeGood("abBAcC"), "");
        Assert.assertEquals(makeGood("s"), "s");
    }
}
