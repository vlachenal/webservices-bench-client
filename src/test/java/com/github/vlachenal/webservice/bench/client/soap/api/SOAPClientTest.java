/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.soap.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

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
import com.github.vlachenal.webservice.bench.client.utils.ApplicationProfiles;


/**
 * SOAP client unit test
 *
 * @author Vincent Lachenal
 */
@SpringBootTest(classes=ApplicationTest.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
public class SOAPClientTest {

  // Attributes +
  /** {@link SOAPClientTest} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(SOAPClientTest.class);

  /** RESTfull client */
  @Autowired
  private SOAPClientTestSuite client;
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
   *
   * @throws DatatypeConfigurationException XML error
   */
  @Test
  public void testCreateCustomerCustomer() throws DatatypeConfigurationException {
    LOG.debug("Enter in testCreateCustomerCustomer");
    final Customer cust = new Customer();
    cust.setFirstName("Oui");
    cust.setLastName("Oui");
    cust.setEmail("oui.oui@yopmail.com");
    final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    cust.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.parse("1940-03-10", format).atStartOfDay(ZoneId.systemDefault()))));
    final Phone phone = new Phone();
    phone.setPhoneType(PhoneType.MOBILE);
    phone.setNumber("+33836656565");
    cust.getPhones().addAll(Arrays.asList(phone));
    final Address addr = new Address();
    addr.getLines().addAll(Arrays.asList("1 voiture jaune et rouge", "fourriere", "c'est comme ça à Paris"));
    addr.setZipCode("75001");
    addr.setCity("Paris");
    addr.setCountry("France");
    cust.setAddress(addr);
    final ClientCall call = client.createCustomer(cust, -1);
    LOG.info("Call is {}. Error message: {}", call.isOk(), call.getErrMsg());
    assertTrue(call.isOk());
    assertNotNull(cust.getId());
    LOG.info("New customer identifier is {}", cust.getId());
    LOG.debug("Exit testCreateCustomerCustomer");
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
    cust.setId("be457d2f-3fb3-4b0f-a9cb-5a56bb267271");
    final ClientCall call = client.getDetails(cust, -1);
    LOG.info("Call is {}. Error message: {}", call.isOk(), call.getErrMsg());
    assertTrue(call.isOk());
    LOG.debug("Exit testGetDetailsCustomer");
  }
  // Tests -

}
