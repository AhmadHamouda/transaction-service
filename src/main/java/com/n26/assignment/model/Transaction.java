package com.n26.assignment.model;

/**
 * Created by Ahmad on 4/15/2017.
 */
public class Transaction implements Comparable<Transaction>{
    private double amount;
    private Long timestamp;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }
}
