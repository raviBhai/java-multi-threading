package com.tutorial.multithreading.reentrantlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

    public static void main(String[] args) {

        ReentrantLock lock1 = new ReentrantLock();
        Account a1 = new Account(100, lock1);

        ReentrantLock lock2 = new ReentrantLock();
        Account a2 = new Account(200, lock2);

        CountDownLatch latch = new CountDownLatch(4);

        ExecutorService service = Executors.newFixedThreadPool(4);
        TransferAmountJob job1 = new TransferAmountJob(a1, a2, 10, latch);
        TransferAmountJob job2 = new TransferAmountJob(a2, a1, 20, latch);
        TransferAmountJob job3 = new TransferAmountJob(a1, a2, 30, latch);
        TransferAmountJob job4 = new TransferAmountJob(a2, a1, 40, latch);

        //a1 should have 20 rs more
        service.execute(job1);
        service.execute(job2);
        service.execute(job3);
        service.execute(job4);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a1.amount);
        System.out.println(a2.amount);

        service.shutdown();
    }
}
