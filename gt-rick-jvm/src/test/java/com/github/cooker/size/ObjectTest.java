package com.github.cooker.size;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;

/**
 * grant
 * 23/7/2020 11:29 上午
 * 描述：
 */
public class ObjectTest {

    Object obj = null;

    @Test
    public void size(){
        Float a = new Float(1.3);
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        int h = hash(a.hashCode());
        System.out.println(Integer.toBinaryString(a.hashCode()));
        System.out.println("重计算hash：" + Integer.toBinaryString(h));
        System.out.println("hash重计算" + (hash(a.hashCode()) & 15));
        System.out.println(a.hashCode() & 15);

        a = new Float(3.8);
        h = hash(a.hashCode());
        System.out.println(Integer.toBinaryString(a.hashCode()));
        System.out.println("重计算hash：" + Integer.toBinaryString(h));
        System.out.println("hash重计算" + (hash(a.hashCode()) & 15));
        System.out.println(a.hashCode() & 15);

        System.out.println(Integer.toBinaryString(15));
    }


    public int hash(int h){
        return (h) ^ (h >>> 16);
    }


    @Test
    public void fhash(){
        for (int i = 0; i < 10; i++) {
            String str = "15." + i;
            Float f = Float.parseFloat(str);
            System.out.println("值" + f);

        }
    }



}
