/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.webflux.api;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.vlachenal.webservice.bench.client.ApplicationTest;
import com.github.vlachenal.webservice.bench.client.DataSet;
import com.github.vlachenal.webservice.bench.client.rest.api.dto.ClientCall;
import com.github.vlachenal.webservice.bench.client.rest.api.dto.Customer;
import com.github.vlachenal.webservice.bench.client.utils.ApplicationProfiles;


/**
 * RESTfull client unit test
 *
 * @author Vincent Lachenal
 */
@SpringBootTest(classes=ApplicationTest.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
public class RESTfulClientTest {

  // Attributes +
  /** {@link RESTfulClientTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(RESTfulClientTest.class);

  /** RESTfull client */
  @Autowired
  private RESTWebfluxClient client;
  // Attributes -


  // Initialization +
  /**
   * Before tests
   */
  @Before
  public void before() {
    client.setMapper("manual");
    client.setCustomers(new ArrayList<>());
    final DataSet data = new DataSet();
    data.setCustomers(new ArrayList<>());
    client.setData(data);
    client.initializeTestSuite();
  }
  // Initialization -


  // Tests +
  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#deleteAll()}.
   */
  @Test
  public void testDeleteAll() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#initializeTestSuite()}.
   */
  @Test
  public void testInitializeTestSuite() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#consolidateStats()}.
   */
  @Test
  public void testConsolidateStats() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#createCustomer(com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer, int)}.
   */
  @Test
  public void testCreateCustomerCustomer() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#listAll(int)}.
   * @throws InterruptedException
   */
  @Test
  public void testListAll() throws InterruptedException {
    LOG.debug("Enter in testListAll");
    final ClientCall call = client.listAll(-1);
    LOG.info("Call is {}. Error message: {}", call.isOk(), call.getErrMsg());
    assertTrue(call.isOk());
    LOG.debug("Exit testListAll");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#getDetails(com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer, int)}.
   */
  @Test
  public void testGetDetailsCustomer() {
    LOG.debug("Enter in testGetDetailsCustomer");
    final Customer cust = new Customer();
    cust.setId("0277d888-0c99-42a0-85a8-9a963119e8ad");
    final ClientCall call = client.getDetails(cust, -1);
    LOG.info("Call is {}. Error message: {}", call.isOk(), call.getErrMsg());
    assertTrue(call.isOk());
    LOG.debug("Exit testGetDetailsCustomer");
  }
  // Tests -

}
