package com.tutorial.multithreading.cyclicbarrier;

import java.util.List;

public class TrippingJob implements Runnable {


    List<List<Integer>> partialResults;

    public TrippingJob(List<List<Integer>> partialResults) {
        this.partialResults = partialResults;
    }

    public void run() {

        int sum = 0;
        for (List<Integer> list : partialResults) {
            System.out.println("Adding :: " + list);
            for (int i : list) {
                sum += i;
            }
        }

        System.out.println("Final sum :: " + sum);
    }
}
