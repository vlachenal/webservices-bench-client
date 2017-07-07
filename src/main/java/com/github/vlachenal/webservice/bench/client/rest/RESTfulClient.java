/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
  /** REST customer endpoint */
  private static final String CUST_ENDPOINT = "/rest/customer";

  /** Server hostname or IP address */
  @Value("server.host")
  private String host;

  /** Server port */
  @Value("server.port")
  private Integer port;

  /** Server port */
  @Value("server.base.url")
  private Integer baseUrl;

  /** The REST template to use */
  private RestTemplate template;
  // Attributes -


  // Methods +
  /**
   * Set REST template
   *
   * @param template the REST template to use
   */
  public void setRestTemplate(final RestTemplate template) {
    this.template = template;
  }

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
    final ClientCall call = new ClientCall();
    call.setMethod("create");
    call.setRequestSeq(requestSeq);
    call.setClientStart(System.nanoTime());
    final HttpHeaders headers = new HttpHeaders();
    headers.add("request_seq", Integer.toString(requestSeq));
    final HttpEntity<Customer> req = new HttpEntity<Customer>(customer, headers);
    final String uuid = template.postForEntity("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT, req, String.class).getBody();
    customer.setId(uuid);
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
    call.setMethod("create");
    call.setRequestSeq(requestSeq);
    call.setClientStart(System.nanoTime());
    final HttpHeaders headers = new HttpHeaders();
    headers.add("request_seq", Integer.toString(requestSeq));
    final HttpEntity<?> req = new HttpEntity<Object>(headers);
    final Customer[] customers = template.exchange("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT, HttpMethod.GET, req, Customer[].class).getBody();
    if(customers.length != this.customers.size()) {
      // TODO plop
    }
    call.setClientEnd(System.nanoTime());
    return call;
  }
  // Methods -

}
