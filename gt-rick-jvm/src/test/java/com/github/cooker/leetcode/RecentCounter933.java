package com.github.cooker.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * grant
 * 10/8/2020 7:42 上午
 * 描述：
 */
public class RecentCounter933 {
    Queue<Integer> q;
    public RecentCounter933() {
        q = new LinkedList<>();
    }

    public int ping(int t) {
        q.add(t);
        while (q.peek() < t- 3000){
            q.poll();
        }
        return q.size();
    }

    @Test
    public void sa(){
        RecentCounter933 r = new RecentCounter933();
        System.out.println(r.ping(1));
    }
}
