package com.n26.assignment.controller;


import com.n26.assignment.AbstractJSONRestTest;
import com.n26.assignment.TestSetUp;
import com.n26.assignment.model.Transaction;
import com.n26.assignment.model.Wrapper.SecondsStatisticsWrapper;
import com.n26.assignment.util.ScheduledTasks;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by Ahmad on 4/15/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TransactionStatisticsTest extends AbstractJSONRestTest {
    private static final Logger LOG = Logger.getLogger(TransactionStatisticsTest.class);

    @Autowired
    TestSetUp setUp;
    @Autowired
    ScheduledTasks scheduledTasks;
    @Autowired
    SecondsStatisticsWrapper resultStatisticsWrapper;

    @Test
    public void testTreateTransaction() {
        try {
            LOG.info("Test create transaction");
            String json = json(setUp.createTransaction());
            LOG.debug("Data: " + json);
            mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
                    .content(json)).
                    andExpect(status().isCreated());
        } catch (Exception ex) {
            LOG.error(ex);
            fail(ex.getMessage());
        }
    }

    @Test
    public void testReadStatistics() {
        try {
            LOG.info("Test read statistics");
            Transaction transaction = setUp.createTransaction();
            String json = json(transaction);
            mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
                    .content(json)).
                    andExpect(status().isCreated());
            Thread.sleep(4000);
            mockMvc.perform(get("/statistics")).
                    andExpect(status().isFound()).
                    andExpect(jsonPath("sum", Matchers.greaterThanOrEqualTo(transaction.getAmount())));
        } catch (Exception ex) {
            LOG.error(ex);
            fail(ex.getMessage());
        }
    }

}
