package com.tutorial.multithreading.reentrantlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TransferAmountJob implements Runnable {

    Account fromAccount;
    Account toAccount;
    int amount;
    CountDownLatch latch;

    public TransferAmountJob(Account fromAccount, Account toAccount, int amount, CountDownLatch latch) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.latch = latch;
    }

    public void run() {
        try {
            transferMoneyWithTryLock(fromAccount, toAccount, amount);
            latch.countDown();
        } catch (InsufficientAmountException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void transferMoneyWithTryLock(Account fromAccount, Account toAccount, int amount)
            throws InsufficientAmountException, InterruptedException {

        try {
            if (fromAccount.lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    if (toAccount.lock.tryLock(3, TimeUnit.SECONDS)) {
                        if (amount > fromAccount.getCurrentAmount()) {
                            throw new InsufficientAmountException("Insufficient Balance");
                        } else {
                            fromAccount.debit(amount);
                            toAccount.credit(amount);
                        }
                    }
                } finally {
                    toAccount.lock.unlock();
                }
            }
        } finally {
            fromAccount.lock.unlock();
        }
    }
}
