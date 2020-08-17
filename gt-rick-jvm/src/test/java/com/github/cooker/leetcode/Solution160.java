package com.github.cooker.leetcode;

import org.junit.Test;

import java.util.HashSet;

/**
 * ZoomGrant 2020/8/15 8:24
 */
public class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        HashSet set = new HashSet<ListNode>();
//        ListNode pre = headA;
//        do {
//            set.add(pre);
//        }while ((pre = pre.next) != null);
//        pre = headB;
//        do {
//            if (set.contains(pre)) break;
//        }while ((pre = pre.next) != null);
//        return pre;
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    @Test
    public void sa(){
        ListNode node = new ListNode(8);
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(1);
        node1.next.next = new ListNode(8);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        ListNode x = getIntersectionNode(node, node1);
        System.out.println(x);
    }
}
