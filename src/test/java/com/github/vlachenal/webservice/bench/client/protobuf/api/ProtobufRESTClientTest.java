/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.protobuf.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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
import com.github.vlachenal.webservice.bench.protobuf.api.Customer;
import com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address;
import com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone;
import com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType;
import com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall;


/**
 * Protobuf client unit test
 *
 * @author Vincent Lachenal
 */
@SpringBootTest(classes=ApplicationTest.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(ApplicationProfiles.TEST)
public class ProtobufRESTClientTest {

  // Attributes +
  /** {@link ProtobufRESTClientTest logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(ProtobufRESTClientTest.class);

  /** Protobuf client */
  @Autowired
  private ProtobufRESTClient client;
  // Attributes -


  // Initialization +
  /**
   * Before tests
   */
  @Before
  public void before() {
    client.setMapper("manual");
    client.setCustomers(new ArrayList<>());
    client.setNbThread(1);
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
    final ArrayList<ClientCall> calls = new ArrayList<>();
    final ClientCall.Builder call = ClientCall.newBuilder();
    call.setRequestSeq(1);
    call.setClientStart(new Date().getTime());
    call.setClientEnd(new Date().getTime() + 100);
    call.setOk(true);
    call.setMethod("create");
    call.setProtocol("protobuf");
    calls.add(call.build());
    client.setCalls(calls);
    client.consolidateStats();
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#createCustomer(com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer, int)}.
   */
  @Test
  public void testCreateCustomer() {
    final Customer.Builder cust = Customer.newBuilder();
    cust.setFirstName("Chuck");
    cust.setLastName("Norris");
    cust.setEmail("chuck.norris@yopmail.com");
    final LocalDate date = LocalDate.parse("1940-03-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    cust.setBirthDate(date.toEpochDay());
    final Address.Builder addr = Address.newBuilder();
    addr.addLines("1 rue des Nuages");
    addr.setCity("Libourne");
    addr.setZipCode("33500");
    addr.setCountry("France");
    cust.setAddress(addr.build());
    final Phone.Builder phone = Phone.newBuilder();
    phone.setType(PhoneType.MOBILE);
    phone.setNumber("+33836656565");
    cust.addPhones(phone.build());
    final ClientCall call = client.createCustomer(cust, 1);
    assertNotNull("Call result is null", call);
    assertTrue("Call is KO: " + call.getErrMsg(), call.getOk());
    final Customer res = cust.build();
    assertNotNull("Customer UUID is not set", res.hasField(Customer.getDescriptor().findFieldByNumber(Customer.ID_FIELD_NUMBER)));
    LOG.info("New customer UUID is {}", res.getId());
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#listAll(int)}.
   */
  @Test
  public void testListAll() {
    LOG.debug("Enter in testListAll");
    final ClientCall call = client.listAll(-1);
    assertNotNull("Call result is null", call);
    assertTrue("Call is KO: " + call.getErrMsg(), call.getOk());
    LOG.debug("Exit testListAll");
  }

  /**
   * Test method for {@link com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient#getDetails(com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer, int)}.
   */
  @Test
  public void testGetDetailsCustomer() {
    LOG.debug("Enter in testGetDetailsCustomer");
    final Customer.Builder cust = Customer.newBuilder();
    cust.setId(UUID.randomUUID().toString());
    final ClientCall call = client.getDetails(cust, -1);
    assertNotNull("Call result is null", call);
    assertTrue("Call is KO: " + call.getErrMsg(), call.getOk());
    LOG.debug("Exit testGetDetailsCustomer");
  }
  // Tests -

}
