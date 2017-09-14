/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.soap.api;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite;


/**
 * SOAP client
 *
 * @author Vincent Lachenal
 */
@Component
public class SOAPClientTestSuite extends AbstractClientTestSuite<Customer, ClientCall> {

  // Attributes +
  /** {@link SOAPClientTestSuite logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(SOAPClientTestSuite.class);

  /** SOAP customer client */
  @Autowired
  private CustomerClient custClient;

  /** SOAP statistics client */
  @Autowired
  private StatisticsClient statClient;
  // Attributes -


  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#deleteAll()
   */
  @Override
  public void deleteAll() {
    custClient.deleteAll();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#initializeTestSuite()
   */
  @Override
  public void initializeTestSuite() {
    try {
      customers = data.getSoapData();
    } catch(final DatatypeConfigurationException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#createCustomer(java.lang.Object, int)
   */
  @Override
  public ClientCall createCustomer(final Customer customer, final int requestSeq) {
    final ClientCall call = new ClientCall();
    call.setMethod("create");
    call.setRequestSeq(requestSeq);
    call.setOk(false);
    call.setProtocol("soap");
    call.setClientStart(System.nanoTime());
    try {
      final String id = custClient.create(customer, requestSeq, mapper);
      call.setOk(true);
      customer.setId(id);
    } catch(final Exception e) {
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    return call;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#listAll(int)
   */
  @Override
  public ClientCall listAll(final int requestSeq) {
    final ClientCall call = new ClientCall();
    call.setMethod("list");
    call.setRequestSeq(requestSeq);
    call.setOk(false);
    call.setProtocol("soap");
    call.setClientStart(System.nanoTime());
    List<Customer> customers = null;
    try {
      customers = custClient.listCustomers(requestSeq, mapper);
      call.setOk(true);
    } catch(final Exception e) {
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(customers == null) {
      call.setOk(false);
    } else if(customers.size() != this.customers.size()) {
      LOG.warn("Customers size should be " + this.customers.size() + " instead of " + customers.size());
    }
    return call;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#getDetails(java.lang.Object, int)
   */
  @Override
  public ClientCall getDetails(final Customer customer, final int requestSeq) {
    final ClientCall call = new ClientCall();
    call.setMethod("get");
    call.setRequestSeq(requestSeq);
    call.setOk(false);
    call.setProtocol("soap");
    call.setClientStart(System.nanoTime());
    Customer cust = null;
    try {
      cust = custClient.getDetails(customer.getId(), requestSeq, mapper);
      call.setOk(true);
    } catch(final Exception e) {
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(!cust.getId().equals(customer.getId())) {
      LOG.warn("Customer should have " + customer.getId() + " instead of " + cust.getId());
    }
    return call;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#consolidateStats()
   */
  @Override
  public void consolidateStats() {
    final TestSuite suite = new TestSuite();
    // Gather system informations +
    suite.setJvm(System.getProperty("java.version"));
    suite.setVendor(System.getProperty("java.vendor"));
    suite.setOsFamily(System.getProperty("os.name"));
    suite.setOsVersion(System.getProperty("os.version"));
    suite.setCpu(cpu);
    suite.setMemory(memory);
    // Gather system informations -
    // Gather test suite informations +
    suite.setNbThread(nbThread);
    suite.setProtocol("soap");
    suite.setCompression(compression);
    suite.setComment(comment);
    suite.getCalls().addAll(calls);
    if(mapper != null) {
      switch(mapper) {
        case "dozer":
          suite.setMapper(Mapper.DOZER);
          break;
        case "mapstruct":
          suite.setMapper(Mapper.MAPSTRUCT);
          break;
        default:
          suite.setMapper(Mapper.MANUAL);
      }
    } else {
      suite.setMapper(Mapper.MANUAL);
    }
    // Gather test suite informations -
    statClient.consolidate(suite);
    statClient.purge();
  }
  // Methods -

}
