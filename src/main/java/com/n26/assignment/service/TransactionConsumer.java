package com.n26.assignment.service;

import com.n26.assignment.model.Statistics;
import com.n26.assignment.model.Transaction;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Ahmad on 4/15/2017.
 */
public class TransactionConsumer implements Runnable{

    private final BlockingQueue transactionSharedQueue;
    private final BlockingQueue resultSharedQueue;
    private Statistics statistics;

    public TransactionConsumer(BlockingQueue transactionSharedQueue, BlockingQueue resultSharedQueue,Statistics statistics) {
        this.transactionSharedQueue = transactionSharedQueue;
        this.resultSharedQueue=resultSharedQueue;
        this.statistics=statistics;
    }

    @Override
    public void run() {
        while(true){
            try {
                Transaction transaction= (Transaction) transactionSharedQueue.take();
                System.out.println("Consumed: "+ transaction.getTimestamp()+", "+ transaction.getAmount());
                resultSharedQueue.put(transaction);
                statistics.addTransaction(transaction);
            } catch (InterruptedException ex) {

            }
        }
    }


}