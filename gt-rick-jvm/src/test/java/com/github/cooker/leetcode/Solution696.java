package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * grant
 * 10/8/2020 10:09 下午
 * 描述：
 */
public class Solution696 {

    //超时
//    public int countBinarySubstrings(String s) {
//        int st = 0;
//        char[] chs = s.toCharArray();
//        char ch = 0;
//        int len = chs.length;
//        int sum = 0;
//        for (int i = 0; i < len - 1; i++) {
//            st = 1;
//            ch = chs[i];
//            int j = i+1;
//            boolean isPop = false;
//            for (; j < len; j++) {
//                if (ch != chs[j]) {
//                    st --;
//                    isPop = true;
//                } else if (ch == chs[j] && isPop ){
//                    break;
//                } else if (ch == chs[j] && !isPop ){
//                    st++;
//                }
//
//                if (st == 0) {
//                    sum ++;
//                    break;
//                }
//            }
//        }
//
//        return sum;
//    }


    public int countBinarySubstrings(String s) {
        int n = s.length();

        //连续相同的字符个数
        int count = 0;
        int lastCount = 0;
        int total = 0;

        for (int i = 0; i < n; i++) {
            count++;
            if (i == n - 1 || s.charAt(i) != s.charAt(i+1)){
                if (lastCount > 0){
                    total += Math.min(count, lastCount);
                }
                lastCount = count;
                count = 0;
            }
        }
        return total;
    }

    @Test
    public void sa(){
        Solution696 so = new Solution696();

        Assert.assertEquals(so.countBinarySubstrings("00110011"), 6);
        System.out.println();
        Assert.assertEquals(so.countBinarySubstrings("10101"), 4);
        System.out.println();
        Assert.assertEquals(so.countBinarySubstrings("001101000"), 5);
    }
}
