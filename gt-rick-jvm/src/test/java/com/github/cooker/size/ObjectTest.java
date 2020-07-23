package com.github.cooker.size;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * grant
 * 23/7/2020 11:29 上午
 * 描述：
 */
public class ObjectTest {

    @Test
    public void size(){
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }



}
