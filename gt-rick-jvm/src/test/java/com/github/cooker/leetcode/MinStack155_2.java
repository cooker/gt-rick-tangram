package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * ZoomGrant 2020/8/15 9:59
 */
public class MinStack155_2 {


    public MinStack155_2(){

    }

    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;
    public void push(int x) {
        if (min >= x){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
       if(stack.pop() == min)
       {
           min = stack.pop();
       }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }


    @Test
    public void sa(){
        MinStack155_2 minStack = new MinStack155_2();
        minStack.push(0);

        Assert.assertEquals(minStack.getMin(), 0);
    }
}
