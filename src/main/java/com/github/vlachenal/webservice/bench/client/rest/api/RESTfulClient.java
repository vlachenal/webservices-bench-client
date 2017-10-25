/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.rest.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.ClientCall;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.Mapper;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.TestSuite;


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
  @Value("${webservicebench.server.host}")
  private String host;

  /** Server port */
  @Value("${webservicebench.server.port}")
  private Integer port;

  /** Server port */
  @Value("${webservicebench.server.base.url}")
  private String baseUrl;

  /** The REST template to use */
  private final RestTemplate template;

  /** Service URI */
  private URI serviceUri;

  /** Customer details URI template */
  private UriTemplate detailsTemplate;
  // Attributes -


  // Constructors +
  /**
   * {@link RESTfulClient} constructor
   *
   * @param template the REST template to use
   */
  public RESTfulClient(final RestTemplate template) {
    this.template = template;
  }
  // Constructors -


  // Methods +
  /**
   * Retrieve REST data from dataset
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#initializeTestSuite()
   */
  @Override
  public void initializeTestSuite() {
    customers = data.getRestData();
    try {
      serviceUri = new URI("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT);
    } catch(final URISyntaxException e) {
      throw new RuntimeException("Unable to compute service URI: " + e.getMessage(), e);
    }
    detailsTemplate = new UriTemplate("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT + "/{id}");
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#deleteAll()
   */
  @Override
  public void deleteAll() {
    template.delete(serviceUri);
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
    ResponseEntity<String> res = null;
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Customer> req = RequestEntity.post(serviceUri)
          .accept(MediaType.TEXT_PLAIN)
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .header("request_seq", Integer.toString(requestSeq))
          .header("mapper", mapper.toUpperCase())
          .body(customer);
      res = template.exchange(req, String.class);
    } catch(final RestClientException e) {
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(res.getStatusCode() != HttpStatus.CREATED) {
      call.setErrMsg("<" + res.getStatusCodeValue() + "> " + res.getBody());
    } else {
      call.setOk(true);
      customer.setId(res.getBody());
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
    ResponseEntity<Customer[]> res = null;
    Customer[] customers = null;
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Void> req = RequestEntity.get(serviceUri)
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .header("request_seq", Integer.toString(requestSeq))
          .header("mapper", mapper.toUpperCase())
          .build();
      res = template.exchange(req, Customer[].class);
    } catch(final RestClientException e) {
      call.setErrMsg(e.getMessage());
      throw new RuntimeException(e);
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(res.getStatusCode() != HttpStatus.OK) {
      call.setErrMsg("<" + res.getStatusCodeValue() + "> " + res.getBody());
    } else {
      call.setOk(true);
      customers = res.getBody();
    }
    if(customers.length != this.customers.size()) {
      LOG.warn("Customers size should be " + this.customers.size() + " instead of " + customers.length);
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
    ResponseEntity<Customer> res = null;
    Customer cust = null;
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Void> req = RequestEntity.get(detailsTemplate.expand(customer.getId()))
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .header("request_seq", Integer.toString(requestSeq))
          .header("mapper", mapper.toUpperCase())
          .build();
      res = template.exchange(req, Customer.class);
    } catch(final RestClientException e) {
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(res.getStatusCode() != HttpStatus.OK) {
      call.setErrMsg("<" + res.getStatusCodeValue() + "> " + res.getBody());
    } else {
      call.setOk(true);
      cust = res.getBody();
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
    try {
      final URI uri = new URI("http://" + host + ':' + port + baseUrl + "/rest/stats");
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
      suite.setProtocol("rest");
      suite.setCompression(compression);
      suite.setComment(comment);
      suite.setCalls(calls);
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
      template.put(uri, suite);
      template.delete(uri);
    } catch(final URISyntaxException e) {
      LOG.error("Unable to create URI ... " + e.getMessage(), e);
    }
  }
  // Methods -

}
