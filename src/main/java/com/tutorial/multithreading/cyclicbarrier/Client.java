package com.tutorial.multithreading.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Client {

    public static void main(String[] args) {
        Random random = new Random();
        List<List<Integer>> partialResults = new ArrayList<List<Integer>>();    //this is not thread safe
        Integer NUM_OF_PARTIAL_DATA = 3;
        Integer NUM_OF_THREADS = 3;

        TrippingJob trippingJob = new TrippingJob(partialResults);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM_OF_THREADS, trippingJob);

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            Job job = new Job(random, partialResults, NUM_OF_PARTIAL_DATA, cyclicBarrier);
            Thread thread = new Thread(job);
            thread.setName("Thread " + i);
            thread.start();
        }

        System.out.println("Main finished");

    }
}
