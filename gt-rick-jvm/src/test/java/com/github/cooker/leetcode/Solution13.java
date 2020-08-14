package com.github.cooker.leetcode;

/**
 * grant
 * 8/8/2020 9:30 下午
 * 描述：
 */
public class Solution13 {
    public int romanToInt(String s) {
        int sum = 0;

        int len = s.length();
        char[] str = s.toCharArray();
        for (int i = 0;i<len;i++){
            int n = getVal(str[i]);
            if (i+1 < len){
                int num = getVal(str[i+1]);
                if (num <= n){
                    sum += n;
                }else {
                    sum += num - n;
                    i++;
                }
            }else {
                sum+=n;
            }
        }

        return sum;
    }

    public int getVal(char ch){
        switch (ch){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution13().romanToInt("IV"));
    }
}
