package com.n26.assignment.model;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ahmad on 4/15/2017.
 */
public class Statistic implements Serializable {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;
    private Lock lock = new ReentrantLock();

    public Statistic createStatistic(double sum) {
        lock.lock();
        try {
            this.sum = sum;
            this.count = 1;
            this.max = sum;
            this.min = sum;
        } finally {
            lock.unlock();
            return this;
        }
    }

    public void addTransaction(Transaction transaction) {
        lock.lock();
        try {
            this.sum += transaction.getAmount();
            this.count++;
            if (this.count == 0 || transaction.getAmount() > this.max) {
                this.max = transaction.getAmount();
            } else if (this.count == 0 || transaction.getAmount() < this.min) {
                this.min = transaction.getAmount();
            }
        } finally {
            lock.unlock();
        }
    }

    public void updateStatistic(Statistic statistic) {
        lock.lock();
        try {
            this.sum += statistic.sum;
            if (this.count == 0 || statistic.max > this.max) {
                this.max = statistic.max;
            }
            if (this.count == 0 || statistic.min < this.min) {
                this.min = statistic.min;
            }
            this.count += statistic.count;
            this.avg = sum / count;
        } finally {
            lock.unlock();
        }
    }

    public void resetStatistics() {
        lock.lock();
        try {
            this.sum = 0;
            this.count = 0;
            this.avg = 0;
            this.min = 0;
            this.max = 0;
        } finally {
            lock.unlock();
        }
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public long getCount() {
        return count;
    }
}
