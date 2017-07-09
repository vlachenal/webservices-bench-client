/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.rest;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.ClientCall;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer;


/**
 * RESTful client test
 *
 * @author Vincent Lachenal
 */
@Component
public class RESTfulClient extends AbstractClientTestSuite<Customer,ClientCall> {

  // Attributes +
  /** {@link RESTfulClient} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(RESTfulClient.class);

  /** REST customer endpoint */
  private static final String CUST_ENDPOINT = "/rest/customer";

  /** Server hostname or IP address */
  @Value("${server.host}")
  private String host;

  /** Server port */
  @Value("${server.port}")
  private Integer port;

  /** Server port */
  @Value("${server.base.url}")
  private String baseUrl;

  /** The REST template to use */
  private final RestTemplate template;
  // Attributes -


  // Constructors +
  public RESTfulClient(final RestTemplate template) {
    this.template = template;
  }
  // Constructors -


  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#deleteAll()
   */
  @Override
  public void deleteAll() {
    template.delete("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT);
  }

  /**
   * Retrieve REST data from dataset
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#initializeTestSuite()
   */
  @Override
  public void initializeTestSuite() {
    customers = data.getRestData();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#createCustomer(java.lang.Object, int)
   */
  @Override
  public ClientCall createCustomer(final Customer customer, final int requestSeq) {
    LOG.info("Create customer {} {}", customer.getFirstName(), customer.getLastName());
    final ClientCall call = new ClientCall();
    call.setMethod("create");
    call.setRequestSeq(requestSeq);
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Customer> req = RequestEntity.post(new URI("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT))
          .accept(MediaType.TEXT_PLAIN)
          .contentType(MediaType.APPLICATION_JSON)
          .header("request_seq", Integer.toString(requestSeq))
          .body(customer);
      final ResponseEntity<String> res = template.exchange(req, String.class);
      customer.setId(res.getBody());
    } catch(final Exception e) {
      // TODO plop
    }
    call.setClientEnd(System.nanoTime());
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
    call.setClientStart(System.nanoTime());
    Customer[] customers = null;
    try {
      final RequestEntity<Void> req = RequestEntity.get(new URI("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT))
          .accept(MediaType.APPLICATION_JSON)
          .header("request_seq", Integer.toString(requestSeq)).build();
      final ResponseEntity<Customer[]> res = template.exchange(req, Customer[].class);
      customers = res.getBody();
    } catch(final Exception e) {
      // TODO plop
    }
    if(customers.length != this.customers.size()) {
      // TODO plop
    }
    call.setClientEnd(System.nanoTime());
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
    call.setClientStart(System.nanoTime());
    Customer cust = null;
    try {
      final RequestEntity<Void> req = RequestEntity.get(new URI("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT + '/' + customer.getId()))
          .accept(MediaType.APPLICATION_JSON)
          .header("request_seq", Integer.toString(requestSeq)).build();
      final ResponseEntity<Customer> res = template.exchange(req, Customer.class);
      cust = res.getBody();
    } catch(final Exception e) {
      // TODO plop
    }
    if(!cust.getId().equals(customer.getId())) {
      // TODO plop
    }
    call.setClientEnd(System.nanoTime());
    return call;
  }
  // Methods -

}
