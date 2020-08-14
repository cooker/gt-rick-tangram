package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * grant
 * 9/8/2020 7:25 下午
 * 描述：
 */
public class Solution38 {

//    public String countAndSay(int n) {
//        StringBuilder sb = new StringBuilder();
//
//        if (n == 1) return "1";
//
//        String str = countAndSay(n - 1);
//        int len = str.length();
//        int first = 0;
//        int i = 0;
//        for (;i<len;i++){
//            if (str.charAt(i) != str.charAt(first)){
//                sb.append((i-first)).append(str.charAt(first));
//                first = i ;
//            }
//        }
//
//        sb.append((i-first)).append(str.charAt(first));
//
//        return sb.toString();
//    }

    public String countAndSay(int n) {
        String page1 = "1";
        String page2 = "";
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                page2 = switchPage(page1);
            }else {
                page1 = switchPage(page2);
            }
        }
        return n % 2 == 1 ? page1 : page2;
    }

    public String switchPage(String page){
        StringBuilder sb = new StringBuilder();
        int len = page.length();
        int first = 0;
        int i = 0;
        for (;i<len;i++){
            if (page.charAt(i) != page.charAt(first)){
                sb.append((i-first)).append(page.charAt(first));
                first = i ;
            }
        }
        sb.append((i-first)).append(page.charAt(first));
        return sb.toString();
    }


    @Test
    public void sa(){
        Solution38 so = new Solution38();
        Assert.assertEquals(so.countAndSay(1), "1");
        Assert.assertEquals(so.countAndSay(2), "11");
        Assert.assertEquals(so.countAndSay(3), "21");
        Assert.assertEquals(so.countAndSay(4), "1211");
        Assert.assertEquals(so.countAndSay(5), "111221");
        Assert.assertEquals(so.countAndSay(6), "312211");
    }

}
