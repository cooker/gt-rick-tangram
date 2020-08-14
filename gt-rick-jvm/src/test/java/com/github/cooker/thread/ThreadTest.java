package com.github.cooker.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * grant
 * 26/7/2020 12:12 上午
 * 描述：
 */
public class ThreadTest {

    @Test
    public void th(){
        IntStream.range(1, 10).parallel().forEach((a)->{
            System.out.println(a);
        });
    }
}
