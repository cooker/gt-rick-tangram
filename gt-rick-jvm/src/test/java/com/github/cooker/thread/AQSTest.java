package com.github.cooker.thread;

/**
 * grant
 * 12/7/2020 3:14 下午
 * 描述：
 */
public class AQSTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            Thread.sleep(1000L);

            say(i+"");
        }

    }


    public static void say(String ss){
        System.out.println(ss);
    }
}
