package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * ZoomGrant 2020/8/16 8:51
 */
public class Solution844 {

    public boolean backspaceCompare(String S, String T) {
        StringBuilder st1 = new StringBuilder();
        StringBuilder st2 = new StringBuilder();
        int index = -1;
        for (Character ch: S.toCharArray()) {
            if (ch == '#' && index!=-1){
                st1.deleteCharAt(index--);
                continue;
            }

            if (ch != '#'){
                index++;
                st1.append(ch);
            }
        }
        index = -1;
        for (Character ch: T.toCharArray()) {
            if (ch == '#' && index!=-1){
                st2.deleteCharAt(index--);
                continue;
            }
            if (ch != '#'){
                index++;
                st2.append(ch);
            }
        }

        return st1.toString().equals(st2.toString());
    }

    @Test
    public void sa(){
        Assert.assertTrue(backspaceCompare("ab#c","ad#c"));
        Assert.assertTrue(backspaceCompare("y#fo##f","y#f#o##f"));
    }
}
