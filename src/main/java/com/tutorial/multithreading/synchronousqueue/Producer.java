package com.tutorial.multithreading.synchronousqueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Producer implements Runnable {

    SynchronousQueue<Integer> queue;
    Integer NUM_OF_ELEMENTS;

    public Producer(SynchronousQueue<Integer> queue, Integer NUM_OF_ELEMENTS) {
        this.queue = queue;
        this.NUM_OF_ELEMENTS = NUM_OF_ELEMENTS;
    }

    public void run() {
        Random random = new Random();

        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            Integer a = random.nextInt(10);
            System.out.println("Produced :: " + a);
            try {
                queue.put(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
