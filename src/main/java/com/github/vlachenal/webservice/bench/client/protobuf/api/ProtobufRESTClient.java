/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.protobuf.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
import com.github.vlachenal.webservice.bench.client.protobuf.ProtobufType;
import com.github.vlachenal.webservice.bench.protobuf.api.Customer;
import com.github.vlachenal.webservice.bench.protobuf.api.ListAllResponse;
import com.github.vlachenal.webservice.bench.protobuf.api.Mapper;
import com.github.vlachenal.webservice.bench.protobuf.api.TestSuite;
import com.github.vlachenal.webservice.bench.protobuf.api.TestSuite.ClientCall;


/**
 * RESTful client test
 *
 * @author Vincent Lachenal
 */
@Component
public class ProtobufRESTClient extends AbstractClientTestSuite<Customer.Builder,ClientCall> {

  // Attributes +
  /** {@link ProtobufRESTClient} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(ProtobufRESTClient.class);

  /** REST customer endpoint */
  private static final String CUST_ENDPOINT = "/protobuf/customer";

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
   * {@link ProtobufRESTClient} constructor
   *
   * @param template the REST template to use
   */
  public ProtobufRESTClient(final RestTemplate template) {
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
    customers = data.getProtobufData();
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
  public ClientCall createCustomer(final Customer.Builder customer, final int requestSeq) {
    final ClientCall.Builder call = ClientCall.newBuilder();
    call.setMethod("create");
    call.setRequestSeq(requestSeq);
    call.setProtocol("protobuf");
    ResponseEntity<String> res = null;
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Customer> req = RequestEntity.post(serviceUri)
          .accept(MediaType.TEXT_PLAIN).contentType(ProtobufType.PROTOBUF_UTF8)
          .header("request_seq", Integer.toString(requestSeq)).header("mapper", mapper.toUpperCase())
          .body(customer.build());
      res = template.exchange(req, String.class);
    } catch(final RestClientException e) {
      call.setOk(false);
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(res != null) {
      if(res.getStatusCode() != HttpStatus.CREATED) {
        call.setOk(false);
        call.setErrMsg("<" + res.getStatusCodeValue() + "> " + res.getBody());
      } else {
        call.setOk(true);
        customer.setId(res.getBody());
      }
    }
    return call.build();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#listAll(int)
   */
  @Override
  public ClientCall listAll(final int requestSeq) {
    final ClientCall.Builder call = ClientCall.newBuilder();
    call.setMethod("list");
    call.setRequestSeq(requestSeq);
    call.setProtocol("protobuf");
    ResponseEntity<ListAllResponse> res = null;
    List<Customer> customers = null;
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Void> req = RequestEntity.get(serviceUri)
          .accept(ProtobufType.PROTOBUF,ProtobufType.PROTOBUF_UTF8)
          .header("request_seq", Integer.toString(requestSeq)).header("mapper", mapper.toUpperCase())
          .build();
      res = template.exchange(req, ListAllResponse.class);
    } catch(final RestClientException e) {
      call.setOk(false);
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(res != null) {
      if(res.getStatusCode() != HttpStatus.OK) {
        call.setOk(false);
        call.setErrMsg("<" + res.getStatusCodeValue() + "> " + res.getBody());
      } else {
        call.setOk(true);
        customers = res.getBody().getCustomersList();
        if(customers == null || customers.size() != this.customers.size()) {
          LOG.warn("Customers size should be " + this.customers.size() + " instead of " + customers.size());
        }
      }
    }
    return call.build();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#getDetails(java.lang.Object, int)
   */
  @Override
  public ClientCall getDetails(final Customer.Builder customer, final int requestSeq) {
    final ClientCall.Builder call = ClientCall.newBuilder();
    call.setMethod("get");
    call.setRequestSeq(requestSeq);
    call.setProtocol("protobuf");
    ResponseEntity<Customer> res = null;
    Customer cust = null;
    call.setClientStart(System.nanoTime());
    try {
      final RequestEntity<Void> req = RequestEntity.get(detailsTemplate.expand(customer.getId()))
          .accept(ProtobufType.PROTOBUF,ProtobufType.PROTOBUF_UTF8)
          .header("request_seq", Integer.toString(requestSeq)).header("mapper", mapper.toUpperCase())
          .build();
      res = template.exchange(req, Customer.class);
    } catch(final RestClientException e) {
      call.setOk(false);
      call.setErrMsg(e.getMessage());
    } finally {
      call.setClientEnd(System.nanoTime());
    }
    if(res != null) {
      if(res.getStatusCode() != HttpStatus.OK) {
        call.setOk(false);
        call.setErrMsg("<" + res.getStatusCodeValue() + "> " + res.getBody());
      } else {
        call.setOk(true);
        cust = res.getBody();
        if(!cust.getId().equals(customer.getId())) {
          LOG.warn("Customer should have " + customer.getId() + " instead of " + cust.getId());
        }
      }
    }
    return call.build();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#consolidateStats()
   */
  @Override
  public void consolidateStats() {
    try {
      final URI uri = new URI("http://" + host + ':' + port + baseUrl + "/protobuf/stats");
      final TestSuite.Builder builder = TestSuite.newBuilder();
      // Gather system informations +
      builder.setJvm(System.getProperty("java.version"));
      builder.setVendor(System.getProperty("java.vendor"));
      builder.setOsFamily(System.getProperty("os.name"));
      builder.setOsVersion(System.getProperty("os.version"));
      builder.setCpu(cpu);
      builder.setMemory(memory);
      // Gather system informations -
      // Gather test suite informations +
      builder.setNbThread(nbThread);
      builder.setProtocol("protobuf");
      if(compression != null) {
        builder.setCompression(compression);
      }
      if(comment != null) {
        builder.setComment(comment);
      }
      builder.addAllCalls(calls);
      if(mapper != null) {
        switch(mapper) {
          case "dozer":
            builder.setMapper(Mapper.DOZER);
            break;
          case "mapstruct":
            builder.setMapper(Mapper.MAPSTRUCT);
            break;
          default:
            builder.setMapper(Mapper.MANUAL);
        }
      } else {
        builder.setMapper(Mapper.MANUAL);
      }
      // Gather test suite informations -
      template.exchange(RequestEntity.put(uri).contentType(ProtobufType.PROTOBUF).body(builder.build()),
                        Void.class);
      template.delete(uri);
    } catch(final URISyntaxException e) {
      LOG.error("Unable to create URI ... " + e.getMessage(), e);
    }
  }
  // Methods -

}
