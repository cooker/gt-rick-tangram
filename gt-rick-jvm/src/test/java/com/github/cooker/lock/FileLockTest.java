package com.github.cooker.lock;

import com.github.cooker.utils.LockUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

/**
 * grant
 * 21/7/2020 10:23 上午
 * 描述：
 */
public class FileLockTest {

    private Executor executor = Executors.newFixedThreadPool(20);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(20);

    @Test
    public void pool() throws InterruptedException {
        if (Files.notExists(Paths.get("./lock"))){
            try {
                Files.createDirectory(Paths.get("./lock"));
            } catch (IOException e) {

            };
        }

        Runnable run = ()->{
            System.out.println("等待>>" + Thread.currentThread().getId());
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("启动>>" + Thread.currentThread().getId());
            LockUtils.TFileLock tFileLock = LockUtils.tryLockFile("123456");
            System.out.println(Thread.currentThread().getId() + ">>>" + tFileLock.getFileLock());
            LockUtils.unLockFile(tFileLock);
        };

        for (int i = 0; i < 20; i++) {
            executor.execute(run);
//            Thread.sleep(1000L);
        }
        System.out.println("退出");
        Thread.sleep(15000L);

    }
}
