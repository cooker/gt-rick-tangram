package com.github.cooker.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ZoomGrant 2020/8/15 17:00
 */
public class Solution1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        for (int i : candies) {
            if(max < i ) max = i;
        }
        List<Boolean> datas = new ArrayList<>();
        for (int i = 0, len = candies.length; i < len; i++) {
            datas.add(max <= candies[i]+extraCandies);
        }
        return datas;
    }
}
