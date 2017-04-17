package com.n26.assignment;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Ahmad on 4/15/2017.
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    @Value("${statistics.interval.seconds}")
    private int statisticsIntervalSeconds;

    public static void main(String args[]) throws Exception {
        SpringApplication.run(Application.class);
    }

}
