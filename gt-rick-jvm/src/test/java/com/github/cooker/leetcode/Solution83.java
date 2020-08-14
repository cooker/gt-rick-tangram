package com.github.cooker.leetcode;

import org.junit.Test;

import java.util.List;

/**
 * grant
 * 13/8/2020 11:36 下午
 * 描述：
 */
public class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prv = head;
        while (prv != null && prv.next != null){
            if (prv.val == prv.next.val){
                prv.next = prv.next.next;
            }else {
                prv = prv.next;
            }
        }
        return head;
    }

    @Test
    public void sa(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(2);
        ListNode ss = deleteDuplicates(node);
        System.out.println(ss);
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
