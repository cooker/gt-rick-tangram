package com.github.cooker.juc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * grant
 * 8/8/2020 4:42 下午
 * 描述：
 */
public class CountDownWatchTest {

    CountDownLatch latch = new CountDownLatch(3);

    @Test
    public void run() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            final int x = i;
            new Thread(
                ()->{
                    System.out.println("进入栅栏 " + x);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("已经进入栅栏 " + x);
                    latch.countDown();
                }
            ).start();
        }

        latch.await();

        System.out.println("退出...");
    }
}
