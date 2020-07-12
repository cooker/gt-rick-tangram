package com.github.cooker.collect;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * grant
 * 9/7/2020 7:46 下午
 * 描述：
 */
public class HashMapTest {
    
    @Test
    public void map(){
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            map.put("key" + i, i + "");
        }
    }

    public static class A{
        transient String arr;

    }

    public static void main(String[] args) {

    }
}
