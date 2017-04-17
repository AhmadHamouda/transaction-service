package com.n26.assignment.model.Wrapper;

import com.n26.assignment.model.Statistic;
import org.springframework.stereotype.Component;

/**
 * Created by Ahmad on 4/16/2017.
 */
@Component
public class SecondsStatisticsWrapper {

    private Statistic[] secondsStatistics = new Statistic[61];

    public void setSecondStatistic(int index, Statistic statistic) {
        secondsStatistics[index] = statistic;
    }

    public Statistic getSecondStatistics(int index) {
        return secondsStatistics[index];
    }

    public int getSecondsStatisticsSize() {
        return secondsStatistics.length;
    }

}
