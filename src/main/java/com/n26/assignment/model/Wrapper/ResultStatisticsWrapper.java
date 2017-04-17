package com.n26.assignment.model.Wrapper;

import com.n26.assignment.model.Statistic;
import org.springframework.stereotype.Component;

/**
 * Created by Ahmad on 4/16/2017.
 */
@Component
public class ResultStatisticsWrapper {

    private Statistic resultStatistics = new Statistic();

    public Statistic getResultStatistics() {
        return resultStatistics;
    }

}
