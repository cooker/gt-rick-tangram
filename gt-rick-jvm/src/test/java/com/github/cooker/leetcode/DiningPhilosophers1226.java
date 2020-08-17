package com.github.cooker.leetcode;

import java.util.concurrent.Semaphore;

/**
 * ZoomGrant 2020/8/16 16:11
 */
public class DiningPhilosophers1226 {

    Semaphore[] semaphore = new Semaphore[]{
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)
    };
    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        if (philosopher % 2 == 0){
            Semaphore s = semaphore[philosopher % 5];
            s.acquire();
            pickLeftFork.run();
            Semaphore s1 = semaphore[(philosopher+1) % 5];
            s1.acquire();
            pickRightFork.run();

            eat.run();


            putRightFork.run();
            putLeftFork.run();

            s1.release();
            s.release();

        }else {
            Semaphore s = semaphore[(philosopher+1) % 5];
            s.acquire();
            pickRightFork.run();
            Semaphore s1 = semaphore[philosopher % 5];
            s1.acquire();
            pickLeftFork.run();

            eat.run();

            putLeftFork.run();
            putRightFork.run();
            s1.release();
            s.release();
        }
    }
}
