package com.tutorial.multithreading.producerconsumer;

import java.util.Deque;

public class Producer implements Runnable {

    int capacity;
    Deque<Integer> queue;

    public Producer(int capacity, Deque<Integer> queue) {
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

                    if (queue.size() == capacity) {
                        queue.wait();
                    }

                    value++;
                    System.out.println("Produced :: " + value);
                    queue.addLast(value);

                    queue.notify();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
