/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.n26.assignment.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ahmad on 4/15/2017.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Transaction expired for statistics")
public class TransactionExpired extends RuntimeException {

    private static final Logger LOG = Logger.getLogger(TransactionExpired.class);

    @Override
    public String getMessage() {
        LOG.error("Transaction expired for statistics");
        return "Transaction expired for statistics";
    }

}
