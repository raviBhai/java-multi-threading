package com.tutorial.multithreading.semaphoremutex;

import java.util.concurrent.Semaphore;

public class Job implements Runnable {

    Semaphore semaphore;

    public Job(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " inside critical region");
            System.out.println(Thread.currentThread().getName() + " :: Available permits :: " + semaphore.availablePermits());
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " outside of critical region");
        }
    }
}
