package com.tutorial.multithreading.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    int amount;
    ReentrantLock lock;

    public Account(int amount, ReentrantLock lock) {
        this.amount = amount;
        this.lock = lock;
    }

    public int getCurrentAmount() {
        return amount;
    }

    public void debit(int amount) {
        this.amount -= amount;
    }

    public void credit(int amount) {
        this.amount += amount;
    }
}
