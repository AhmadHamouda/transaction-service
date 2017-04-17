package com.n26.assignment.controller;

import com.n26.assignment.model.Statistic;
import com.n26.assignment.model.Transaction;
import com.n26.assignment.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ahmad on 4/15/2017.
 */
@Controller
public class TransactionStatistics {

    @Autowired
    TransactionService transactionService;

    @ApiOperation(value = "Add transaction", notes = "called every time with new transaction.")
    @RequestMapping(value = "/transactions", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }


    @ApiOperation(value = "Get statistics", notes = "Get real time statistics for last 60 seconds.")
    @RequestMapping(value = "/statistics", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public Statistic getStatistics() {
        return transactionService.getMinStatistics();
    }
}
