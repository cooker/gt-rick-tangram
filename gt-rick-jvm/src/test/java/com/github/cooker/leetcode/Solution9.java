package com.github.cooker.leetcode;

/**
 * grant
 * 8/8/2020 9:09 下午
 * 描述：
 */
public class Solution9 {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        char[] num = Integer.toString(x).toCharArray();
        int index = num.length / 2;
        int i = num.length % 2 == 0 ? index - 1 : index, j = index;

        while (i >=0 && j < num.length && num[i] == num[j]){
            i--;
            j++;
        }

        if (i == -1) return true;
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new Solution9().isPalindrome(-11));
    }

}
