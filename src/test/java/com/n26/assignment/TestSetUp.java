package com.n26.assignment;

import com.n26.assignment.model.Transaction;
import com.n26.assignment.model.Wrapper.ResultStatisticsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


/**
 * Created by Ahmad on 4/15/2017.
 */
@Component
@ActiveProfiles("test")
public class TestSetUp {

    @Autowired
    ResultStatisticsWrapper resultStatisticsWrapper;

    public Transaction createTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(1000);
        transaction.setTimestamp(System.currentTimeMillis());
        return transaction;
    }
}
