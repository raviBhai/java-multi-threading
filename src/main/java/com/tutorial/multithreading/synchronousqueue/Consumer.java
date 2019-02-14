package com.tutorial.multithreading.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

public class Consumer implements Runnable {

    SynchronousQueue<Integer> queue;
    Integer NUM_OF_ELEMENTS;

    public Consumer(SynchronousQueue<Integer> queue, Integer NUM_OF_ELEMENTS) {
        this.queue = queue;
        this.NUM_OF_ELEMENTS = NUM_OF_ELEMENTS;
    }


    public void run() {

        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            try {
                Integer a = queue.take();
                System.out.println("Consumed :: " + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
