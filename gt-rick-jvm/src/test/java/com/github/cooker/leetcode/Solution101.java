package com.github.cooker.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * grant
 * 14/8/2020 2:00 下午
 * 描述：
 */
public class Solution101 {
    //递归解法
//    public boolean isSymmetric(TreeNode root) {
//        if (root == null) return false;
//
//        return false;
//    }
//
//    private boolean check(TreeNode l, TreeNode r){
//        if (l == null && r == null) return true;
//        if (l == null || r == null) return false;
//        return l.val == r.val && check(l.left, r.right) && check(l.right, r.left);
//    }

    public boolean isSymmetric(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        q.add(root.left);
        q.add(root.right);

        while (!q.isEmpty()){

        }

        return true;
    }


    @Test
    public void sa(){
        TreeNode tree = new TreeNode();
        tree.val = 1;
        Assert.assertEquals(isSymmetric(tree), true);
    }
}




