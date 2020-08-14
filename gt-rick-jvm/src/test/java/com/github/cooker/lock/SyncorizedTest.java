package com.github.cooker.lock;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * grant
 * 23/7/2020 2:11 下午
 * 描述：
 */
public class SyncorizedTest {

    static boolean isRun = true;
    final Object obj = new Object();

    @Test
    public void sync() throws InterruptedException {
        Runnable run = ()->{
            while (isRun){
                synchronized (obj){}
            }
            System.out.println("stop");
        };

        Executor executor = Executors.newFixedThreadPool(10);
        executor.execute(run);
        executor.execute(run);

        Thread.sleep(3000L);
        isRun = false;
        Thread.sleep(3000L);
    }
}
