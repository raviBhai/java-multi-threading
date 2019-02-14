package com.tutorial.multithreading.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Client {

    public static void main(String[] args) {
        Random random = new Random();
        List<List<Integer>> partialResults = new ArrayList<List<Integer>>();    //this is not thread safe
        Integer NUM_OF_PARTIAL_DATA = 30;
        Integer NUM_OF_THREADS = 3;

        CountDownLatch latch = new CountDownLatch(NUM_OF_THREADS);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            Job job = new Job(random, partialResults, NUM_OF_PARTIAL_DATA, latch);
            Thread thread = new Thread(job);
            thread.setName("Thread " + i);
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Latch awaiting done");

        TrippingJob trippingJob = new TrippingJob(partialResults);
        Thread thread = new Thread(trippingJob);
        thread.start();

        System.out.println("Main finished");
    }
}
