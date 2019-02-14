package com.tutorial.multithreading.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Job implements Runnable {
    Random random;
    List<List<Integer>> partialResults;
    Integer NUM_OF_PARTIAL_DATA;
    CountDownLatch latch;

    public Job(Random random, List<List<Integer>> partialResults, Integer NUM_OF_PARTIAL_DATA, CountDownLatch latch) {
        this.random = random;
        this.partialResults = partialResults;
        this.NUM_OF_PARTIAL_DATA = NUM_OF_PARTIAL_DATA;
        this.latch = latch;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        List<Integer> partialList = new ArrayList<Integer>();

        for (int i = 0; i < NUM_OF_PARTIAL_DATA; i++) {
            Integer a = random.nextInt(10);
            System.out.println("Generated :: " + a + " :: in " + threadName);
            partialList.add(a);
        }

        partialResults.add(partialList);

        latch.countDown();
        System.out.println("Awaiting :: " + threadName);
    }
}
