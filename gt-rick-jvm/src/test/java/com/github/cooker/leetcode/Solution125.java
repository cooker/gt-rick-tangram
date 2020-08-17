package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * ZoomGrant 2020/8/15 7:59
 */
public class Solution125 {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;
        if (s.length() == 1) return true;
        int right = s.length() - 1;
        int left = 0;
        while (left < right){
            while ( left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
        }
        return true;
    }

    @Test
    public void sa(){
        Assert.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome("race a car"));
    }
}
