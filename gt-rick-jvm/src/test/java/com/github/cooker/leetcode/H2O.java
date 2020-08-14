package com.github.cooker.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * grant
 * 12/8/2020 9:45 下午
 * 描述：
 */
public class H2O {

    public H2O() {

    }
    CyclicBarrier c = new CyclicBarrier(3);
    Semaphore h2s = new Semaphore(2);
    Semaphore os = new Semaphore(1);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        h2s.acquire();
        releaseHydrogen.run();
        try {
            c.await();
            h2s.release();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        os.acquire();
        releaseOxygen.run();
        try {
            c.await();
            os.release();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
