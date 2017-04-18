package com.n26.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Ahmad on 4/15/2017.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String args[]) throws Exception {
        SpringApplication.run(Application.class);
    }

}
