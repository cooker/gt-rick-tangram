package com.github.cooker.algorithm;

import org.junit.Test;

/**
 * ZoomGrant 2020/7/5 18:06
 * 冒泡
 */
public class BubbleTest {
    int[] nums = {4,8,6,1,0,0,10,9,2,3};

    @Test
    public void sort(){
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        this.print();
    }


    @Test
    public void sort2(){
        int low = 0;
        int high = nums.length - 1;

        while (low < high){
            for (int i = low; i < high; i++) {
                if (nums[i] > nums[i+1]){
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                }
            }
            --high;

            for (int i = high; i > low; i--) {
                if (nums[i] < nums[i-1]){
                    int temp = nums[i];
                    nums[i] = nums[i-1];
                    nums[i-1] = temp;
                }
            }
            ++low;
        }

        print();
    }


    public void print(){
        for (int i = 0; i < nums.length; i++) {
            System.out.print("\t" + nums[i]);
        }
    }
}
