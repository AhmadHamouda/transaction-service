package com.n26.assignment.service;

import com.n26.assignment.model.Statistics;
import com.n26.assignment.model.Transaction;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Ahmad on 4/15/2017.
 */
public class ResultConsumer implements Runnable {

    private final PriorityBlockingQueue sharedQueue;
    private final Statistics statistics;

    public ResultConsumer(PriorityBlockingQueue sharedQueue, Statistics statistics) {
        this.sharedQueue = sharedQueue;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        while (true) {
            Transaction transaction = (Transaction) sharedQueue.peek();
            if (transaction.getTimestamp() < (System.currentTimeMillis() - (60 * 1000))) {
                System.out.println("Result Consumed: " + transaction.getTimestamp() + ", " + transaction.getAmount());
                sharedQueue.poll();
                statistics.removeTransaction(transaction);
            }
        }
    }


}