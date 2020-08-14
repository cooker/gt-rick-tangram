package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * grant
 * 14/8/2020 1:29 下午
 * 描述：
 */
public class Solution100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    @Test
    public void se(){
        TreeNode p1 = new TreeNode();
        p1.val = 1;
        TreeNode p2 = new TreeNode();
        p1.val = 2;
        Assert.assertEquals(isSameTree(p1, p2), false);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
