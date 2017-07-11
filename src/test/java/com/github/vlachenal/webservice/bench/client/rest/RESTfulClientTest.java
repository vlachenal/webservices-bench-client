/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.rest;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer;


/**
 * RESTful client unit tests
 *
 * @author Vincent Lachenal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RESTfulClientTest {

  // Attributes +
  /** {@link RESTfulClientTest logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(RESTfulClientTest.class);

  /** RESTful client */
  @Autowired
  private RESTfulClient client;
  // Attributes -


  // (Un)Initialization +
  /**
   * Test initialization
   */
  @BeforeClass
  public static void setUpBeforeClass() {
    // Nothing for now
  }

  /**
   * Test uninitialization
   */
  @AfterClass
  public static void tearDownAfterClass() {
    // Nothing for now
  }
  // (Un)Initialization -


  // Tests +
  @Test
  public void testCreate() {
    LOG.info("Begin testCreate");
    final Customer cust = new Customer();
    cust.setEmail("chuck.norris@yopmail.com");
    client.createCustomer(cust, -1);
    fail("Not yet implemented");
    LOG.info("End testCreate");
  }
  // Tests -

}
