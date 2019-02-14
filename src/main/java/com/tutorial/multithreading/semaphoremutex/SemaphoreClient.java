package com.tutorial.multithreading.semaphoremutex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreClient {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        Semaphore semaphore = new Semaphore(1);
        Job job1 = new Job(semaphore);
        Job job2 = new Job(semaphore);
        service.execute(job1);
        service.execute(job2);
        service.shutdown();
    }
}
