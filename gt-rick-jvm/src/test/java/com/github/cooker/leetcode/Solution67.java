package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Stack;

/**
 * grant
 * 13/8/2020 7:46 上午
 * 描述：
 */
public class Solution67 {
    //解法1
//    public String addBinary(String a, String b) {
//        StringBuilder sb = new StringBuilder();
//        int aLen = a.length();
//        int bLen = b.length();
//        int len = Math.min(aLen, bLen);
//        int num = 0;
//        for (int i = 0; i < len; i++) {
//            num = (a.charAt(aLen - i - 1) - '0') + (b.charAt(bLen - i - 1) - '0') + num;
//            sb.append(num%2 + "");
//            num = num / 2;
//        }
//
//        if (aLen > bLen){
//            for (int i = 0, llen = aLen - bLen; i < llen; i++) {
//                num = (a.charAt(llen - i - 1) - '0') + num;
//                sb.append(num%2 + "");
//                num = num / 2;
//            }
//        }else if (aLen < bLen){
//            for (int i = 0, llen = bLen - aLen; i < llen; i++) {
//                num = (b.charAt(llen - i - 1) - '0') + num;
//                sb.append(num%2 + "");
//                num = num / 2;
//            }
//        }
//
//
//        if (num == 1) sb.append(num  + "");
//
//        return sb.reverse().toString();
//    }

    //解法2
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aLen = a.length();
        int bLen = b.length();
        int len = Math.max(aLen, bLen);
        int num = 0;
        for (int i = 0; i < len; i++) {
            num += i < aLen ? a.charAt(aLen - i -1) - '0' : 0;
            num += i < bLen ? b.charAt(bLen - i -1) - '0' : 0;
            sb.append(num%2);
            num /= 2;
        }

        if (num == 1) sb.append(num);
        return sb.reverse().toString();
    }



    @Test
    public void add(){
        Solution67 so = new Solution67();
        Assert.assertEquals(so.addBinary("1", "1"), "10");
        Assert.assertEquals(so.addBinary("11", "1"), "100");
        Assert.assertEquals(so.addBinary("10", "1"), "11");
        Assert.assertEquals(so.addBinary("1010", "1011"), "10101");
        Assert.assertEquals(so.addBinary("100", "110010"), "110110");
    }
}
