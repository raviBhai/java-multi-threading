package com.tutorial.multithreading.producerconsumer;

import java.util.Deque;

public class Consumer implements Runnable {
    int capacity;
    Deque<Integer> queue;

    public Consumer(int capacity, Deque<Integer> queue) {
        this.capacity = capacity;
        this.queue = queue;
    }


    public void run() {

        int value = 0;

        while (true) {

            if (value > 10) {
                break;
            }

            try {
                synchronized (queue) {

                    if (queue.isEmpty()) {
                        queue.wait();
                    }

                    value = queue.removeFirst();

                    queue.notify();

                    System.out.println("Consumed :: " + value);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
