package com.github.cooker.juc;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * grant
 * 8/8/2020 4:55 下午
 * 描述：
 */
public class CyclicBarrierTest {
    CyclicBarrier barrier = new CyclicBarrier(3);

    @Test
    public void run() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
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
                        try {
                            barrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        System.out.println("栅栏 " + x + " 退出");
                    }
            ).start();
        }


        Thread.sleep(3000L);
    }
}
