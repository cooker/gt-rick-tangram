package com.github.cooker.leetcode;

/**
 * grant
 * 8/8/2020 8:49 下午
 * 描述：
 */
public class Solution7 {
    public int reverse(int x) {
        int rev = 0;
        int max = Integer.MAX_VALUE/10;
        int min = Integer.MIN_VALUE/10;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > max || (rev == max && pop > 7)) return 0;
            if (rev < min || (rev == min && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
