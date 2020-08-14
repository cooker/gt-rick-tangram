package com.github.cooker.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * grant
 * 9/8/2020 10:28 上午
 * 描述：
 */
public class Solution21 {

    //解法 1，25ms
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode tmp = new ListNode();
        ListNode first = tmp;
        //TODO
        List<Integer> datas = new ArrayList<>();
        while (l1 != null){
            datas.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null){
            datas.add(l2.val);
            l2 = l2.next;
        }
        int len = datas.size();
        datas = datas.stream().sorted().parallel().collect(Collectors.toList());
        for (int i = 0; i < len; i++) {
            tmp.val = datas.get(i);

            if (i+1 < len){
                tmp.next = new ListNode();
                tmp = tmp.next;
            }
        }
        return first;
    }


    //解法 2
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode next = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val){
                next.next = l1;
                l1 = l1.next;
            }else {
                next.next = l2;
                l2 = l2.next;
            }
            next = next.next;
        }
        next.next = l1 == null ? l2 : l1;
        return head.next;
    }

   static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    public static void main(String[] args) {
//        1->2->4, 1->3->4

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(4);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        Solution21 solution21 = new Solution21();
        node = solution21.mergeTwoLists2(node, node2);
        System.out.println(node);
    }
}
