package com.github.cooker.collect;

import org.junit.Test;

import java.util.*;

/**
 * grant
 * 9/7/2020 7:46 下午
 * 描述：
 */
public class HashMapTest {
    
    @Test
    public void map(){
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String xx = map.put("key", i + "");
            if (xx != null){
                System.out.println(i);
            }
        }
    }

    public static class A{
        transient String arr;

    }

    public static void main(String[] args) {

    }
}
