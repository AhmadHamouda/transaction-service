package com.n26.assignment.service;

import com.n26.assignment.exception.TransactionExpired;
import com.n26.assignment.model.Statistic;
import com.n26.assignment.model.Transaction;
import com.n26.assignment.model.Wrapper.ResultStatisticsWrapper;
import com.n26.assignment.model.Wrapper.SecondsStatisticsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Ahmad on 4/16/2017.
 */
@Service
public class TransactionService {

    @Value("${statistics.interval.seconds}")
    private int statisticsIntervalSeconds;
    @Autowired
    private ResultStatisticsWrapper resultStatisticsWrapper;
    @Autowired
    private SecondsStatisticsWrapper secondsStatisticsWrapper;

    public void addTransaction(Transaction transaction) {

        if (transaction.getTimestamp() <= System.currentTimeMillis() && transaction.getTimestamp() >= (System.currentTimeMillis() - ((statisticsIntervalSeconds + 1) * 1000))) {
            int currentTimeMillisIndex = Math.toIntExact((transaction.getTimestamp() / 1000) % (statisticsIntervalSeconds + 1));
            if (secondsStatisticsWrapper.getSecondStatistics(currentTimeMillisIndex) != null) {
                secondsStatisticsWrapper.getSecondStatistics(currentTimeMillisIndex).addTransaction(transaction);
            } else {
                secondsStatisticsWrapper.setSecondStatistic(currentTimeMillisIndex, new Statistic().createStatistic(transaction.getAmount()));
            }
        } else {
            throw new TransactionExpired();
        }
    }

    public Statistic getMinStatistics() {
        return resultStatisticsWrapper.getResultStatistics();
    }

}
