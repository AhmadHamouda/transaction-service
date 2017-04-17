package com.n26.assignment.util;

import com.n26.assignment.model.Statistic;
import com.n26.assignment.model.Wrapper.ResultStatisticsWrapper;
import com.n26.assignment.model.Wrapper.SecondsStatisticsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Ahmad on 4/15/2017.
 */
@Component
public class ScheduledTasks {

    @Value("${statistics.interval.seconds}")
    private int statisticsIntervalSeconds;
    @Autowired
    private ResultStatisticsWrapper resultStatisticsWrapper;
    @Autowired
    private SecondsStatisticsWrapper secondsStatisticsWrapper;

    @Scheduled(fixedRate = 1000)
    public void deleteStatisticsExpiredTransactions() {
        int currentTimeMillisIndex = Math.toIntExact(((System.currentTimeMillis() / 1000) + 1) % (statisticsIntervalSeconds + 1));
        secondsStatisticsWrapper.setSecondStatistic(currentTimeMillisIndex, null);
        updateStatistics();
    }

    private void updateStatistics() {
        resultStatisticsWrapper.getResultStatistics().resetStatistics();
        for (int i = 0; i < secondsStatisticsWrapper.getSecondsStatisticsSize(); i++) {
            Statistic statistic = secondsStatisticsWrapper.getSecondStatistics(i);
            if (statistic != null) {
                resultStatisticsWrapper.getResultStatistics().updateStatistic(statistic);
            }
        }
    }

}