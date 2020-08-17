package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * ZoomGrant 2020/8/15 9:34
 */
public class MinStack155 {
    /** initialize your data structure here. */
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) minStack.push(x);
    }

    public void pop() {
        int n = stack.pop();
        if (n == minStack.peek()) minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    @Test
    public void sa(){
        MinStack155 minStack = new MinStack155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(minStack.getMin(), -3);
        minStack.pop();
        minStack.top();
        Assert.assertEquals(minStack.getMin(), -2);
    }



}
