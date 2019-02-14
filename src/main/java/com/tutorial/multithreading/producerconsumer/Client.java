package com.tutorial.multithreading.producerconsumer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        int capacity = 3;
        Deque<Integer> deque = new LinkedList<>();

        Producer producer = new Producer(capacity, deque);
        Consumer consumer = new Consumer(capacity, deque);
        service.execute(producer);
        service.execute(consumer);
        service.shutdown();
    }
}
