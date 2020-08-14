package com.github.cooker.leetcode;

/**
 * grant
 * 10/8/2020 8:00 上午
 * 描述：
 */
public class Solution58 {
    public int lengthOfLastWord(String s) {
        if (s.length() == 1 && s.charAt(0) != ' ') return 1;
        StringBuilder sb = new StringBuilder();
        boolean isFirstWord = false;
        for (char ch: s.toCharArray()) {
            if (isFirstWord) {
                sb.append(ch);
                continue;
            }
            if (ch != ' ' && isFirstWord == false){
                isFirstWord = true;
                sb.append(ch);
            }
        }
        s = sb.toString();
        int len = s.length();
        int i = len - 1;
        isFirstWord = false;
        for (;i>0;i--){
            if (s.charAt(i) != ' ' && isFirstWord == false)
                isFirstWord = true;
            if (s.charAt(i) == ' ' && isFirstWord) {
                break;
            }
        }
        return s.charAt(0) == ' ' ? len - i - 1 : len - i;
    }

    public static void main(String[] args) {
        Solution58 so = new Solution58();
//        System.out.println(so.lengthOfLastWord("Today is a nice day"));
//        System.out.println(so.lengthOfLastWord(""));
//        System.out.println(so.lengthOfLastWord("        "));
        System.out.println(so.lengthOfLastWord("a "));
        System.out.println(so.lengthOfLastWord(" aa"));
//        System.out.println(so.lengthOfLastWord("a"));
    }
}
