package com.n26.assignment.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ahmad on 4/15/2017.
 */
public class Statistics {
    private static volatile Statistics statistics = new Statistics();
    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;
    private Lock lock = new ReentrantLock();

    private Statistics() {
    }

    public static Statistics getInstance() {
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    public void addTransaction(Transaction transaction) {
        lock.lock();
        try {
            this.sum += transaction.getAmount();
            this.count++;
            this.avg = sum / count;
            if (transaction.getAmount() > this.max) {
                this.max = transaction.getAmount();
            } else if (transaction.getAmount() < this.min) {
                this.min = transaction.getAmount();
            }
        } finally {
            lock.unlock();
        }
    }

    public void removeTransaction(Transaction transaction) {
        lock.lock();
        try {
            this.sum -= transaction.getAmount();
            this.count--;
            this.avg = sum / count;
            if (transaction.getAmount() > this.max) {
                this.max = transaction.getAmount();
            } else if (transaction.getAmount() < this.min) {
                this.min = transaction.getAmount();
            }
        } finally {
            lock.unlock();
        }
    }

    public Statistics readStatistics() {
        synchronized (lock){
            return this;
        }
    }
}
