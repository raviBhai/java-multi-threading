package com.tutorial.multithreading.synchronousqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class Client {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        Integer NUM_OF_ELEMENTS = 5;
        Producer producer = new Producer(queue, NUM_OF_ELEMENTS);
        Consumer consumer = new Consumer(queue, NUM_OF_ELEMENTS);
        service.execute(producer);
        service.execute(consumer);
        service.shutdown();
    }
}
