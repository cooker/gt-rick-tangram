package com.github.cooker.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * ZoomGrant 2020/8/16 9:48
 */
public class MyStack225 {

    Deque<Integer> deque = new LinkedList();

    public void push(int x) {
        deque.push(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return deque.pop();
    }

    /** Get the top element. */
    public int top() {
        return deque.getFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return deque.isEmpty();
    }


}
