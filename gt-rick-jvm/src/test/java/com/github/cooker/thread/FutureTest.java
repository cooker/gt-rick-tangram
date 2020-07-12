package com.github.cooker.thread;

import org.junit.Test;


import java.util.concurrent.*;

/**
 * grant
 * 26/6/2020 7:16 上午
 * 描述：
 * FutureTask
 * ScheduledFutureTask
 * ForkJoinTask
 */
public class FutureTest {

    @Test
    public void futureTask() throws ExecutionException, InterruptedException {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("xxxxxxx");
                return "sa";
            }
        };
        FutureTask future = new FutureTask(callable);
        
        Thread thread = new Thread(future);

        thread.start();

        future.get();
    }

    @Test
    public void scheduledFutureTask() throws ExecutionException, InterruptedException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture task =  executor.schedule(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("xxxxa");
                return "xxx";
            }
        }, 10, TimeUnit.SECONDS);


        Thread thread = new Thread(()->{

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            task.cancel(true);
        });
        thread.start();
        task.get();
    }
    
    @Test
    public void forkJoinTask() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("1111");
                return "xxxx";
            }
        };
    }


    @Test
    public void done() throws InterruptedException {
        FutureTask target = new FutureTask(()->{
            System.out.println("=======");
            return "sa";
        });
        Thread thread = new Thread(target);
        thread.start();
        Thread.sleep(1000L);
        System.out.println(target.isDone());
        System.out.println(target.isCancelled());
    }
    
    @Test
    public void km(){

    }

}
