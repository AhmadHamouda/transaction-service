package com.n26.assignment.service;

import com.n26.assignment.model.Transaction;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Ahmad on 4/15/2017.
 */
public class TransactionProducer implements Runnable {

    private final BlockingQueue transactionSharedQueue;
    private Transaction transaction;

    public TransactionProducer(BlockingQueue transactionSharedQueue, Transaction transaction) {
        this.transactionSharedQueue = transactionSharedQueue;
        this.transaction=transaction;
    }

    @Override
    public void run() {
        try {
            System.out.println("Transaction Produced: " + transaction.getTimestamp()+", "+transaction.getAmount());
            if(transaction.getTimestamp()<=System.currentTimeMillis()&&transaction.getTimestamp()>=(System.currentTimeMillis()-(60*1000))){
                transactionSharedQueue.put(transaction);
            }
        } catch (InterruptedException ex) {

        }
    }

}