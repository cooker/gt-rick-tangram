package com.github.cooker.leetcode;

import java.util.Objects;
import java.util.Stack;

import org.apache.activemq.transport.nio.SelectorSelection;
import org.junit.Assert;

/**
 * grant
 * 9/8/2020 9:14 上午
 * 描述：
 */
public class Solution20 {

    public boolean isValid(String s) {
        int len = s.length();
        if (len % 2 == 1) return false ;
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            if (cur == '(' || cur== '{' || cur == '[' )
                st.push(cur);
            else{
                if (st.isEmpty()) return false;
                Character pp = st.pop();
                if(pp == '(' && cur ==')' || pp == '{' && cur =='}'||pp == '[' && cur ==']')
                    continue;
                else
                    return false;
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        Solution20 s = new Solution20();
        Assert.assertEquals(s.isValid("()"), true);
        Assert.assertEquals(s.isValid("()[]{}"), true);
        Assert.assertEquals(s.isValid("(]"), false);
        Assert.assertEquals(s.isValid("]"), false);

        int sum = 9;
        System.out.println((int)'(' % sum);
        System.out.println((int)'[' % sum);
        System.out.println((int)'{' % sum);
        System.out.println();
        System.out.println((int)')' % sum);
        System.out.println((int)']' % sum);
        System.out.println((int)'}' % sum);
    }
}
