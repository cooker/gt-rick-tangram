package com.github.cooker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * grant
 * 24/6/2020 8:47 上午
 * 描述：
 */
public class SubrectangleQueries {

    public void backtrack(int n,
                          ArrayList<Integer> output,
                          List<List<Integer>> res,
                          int first) {
        // 所有数都填完了
        if (first == n)
            res.add(new ArrayList<Integer>(output));
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList();

        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int num : nums)
            output.add(num);

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public static void main(String[] args) {
        SubrectangleQueries sq = new SubrectangleQueries();

        System.out.println(sq.permute(new int[]{1,2,3}));
    }
}
