/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.webflux.api;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite;
import com.github.vlachenal.webservice.bench.client.rest.api.dto.ClientCall;
import com.github.vlachenal.webservice.bench.client.rest.api.dto.Customer;
import com.github.vlachenal.webservice.bench.client.rest.api.dto.Mapper;
import com.github.vlachenal.webservice.bench.client.rest.api.dto.TestSuite;

import reactor.core.publisher.Flux;


/**
 * RESTful reactive client test
 *
 * @author Vincent Lachenal
 */
@Component
public class RESTWebfluxClient extends AbstractClientTestSuite<Customer,ClientCall> {

  // Attributes +
  /** {@link RESTWebfluxClient} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(RESTWebfluxClient.class);

  /** REST customer endpoint */
  private static final String CUST_ENDPOINT = "/webflux/customer";

  /** REST customer endpoint */
  private static final String STAT_ENDPOINT = "/webflux/stats";

  /** Server hostname or IP address */
  @Value("${webservicebench.server.host}")
  private String host;

  /** Server port */
  @Value("${webservicebench.server.port}")
  private Integer port;

  /** Server port */
  @Value("${webservicebench.server.base.url}")
  private String baseUrl;

  /** The web client to use */
  private WebClient client;

  /** The statistics web client to use */
  private WebClient statsClient;
  // Attributes -


  // Methods +
  /**
   * Retrieve REST data from dataset.<br>
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#initializeTestSuite()
   */
  @Override
  public void initializeTestSuite() {
    customers = data.getRestData();
    client = WebClient.create("http://" + host + ':' + port + baseUrl + CUST_ENDPOINT);
    statsClient = WebClient.create("http://" + host + ':' + port + baseUrl + STAT_ENDPOINT);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#deleteAll()
   */
  @Override
  public void deleteAll() {
    client.mutate().build().delete().exchange().doOnError(e -> {
      LOG.error("Error while deleting customers: " + e.getMessage(), e);
    }).subscribe();
  }

  /**
   * Manage client error
   *
   * @param error the error
   * @param call the call
   * @param mutex the mutex to unlock
   */
  private void manageClientError(final Throwable error, final ClientCall call, final Semaphore mutex) {
    call.setOk(false);
    call.setErrMsg(error.getMessage());
    call.setClientEnd(System.nanoTime());
    mutex.release();
  }

  /**
   * Manager response
   *
   * @param res the {@link ClientResponse}
   * @param clazz the item(s) type
   * @param onSuccess the on success consumer
   * @param flux is a {@code Flux} or not
   * @param call the client call
   * @param mutex the mutex to unlock
   */
  private <T> void manageResponse(final ClientResponse res, final Class<T> clazz, final Consumer<T> onSuccess, final boolean flux, final ClientCall call, final Semaphore mutex) {
    manageResponse(res, clazz,  HttpStatus.OK, onSuccess, flux, call, mutex);
  }

  /**
   * Manager response
   *
   * @param res the {@link ClientResponse}
   * @param clazz the item(s) type
   * @param expStatus the expected HTTP status code
   * @param onSuccess the on success consumer
   * @param flux is a {@code Flux} or not
   * @param call the client call
   * @param mutex the mutex to unlock
   */
  private <T> void manageResponse(final ClientResponse res, final Class<T> clazz, final HttpStatus expStatus, final Consumer<T> onSuccess, final boolean flux, final ClientCall call, final Semaphore mutex) {
    if(res.statusCode() == expStatus) {
      if(flux) {
        res.bodyToFlux(clazz).doOnNext(onSuccess::accept)
        .doOnError(e -> {
          call.setOk(false);
          call.setErrMsg(e.getMessage());
        }).doFinally(t -> {
          call.setClientEnd(System.nanoTime());
          mutex.release();
        }).subscribe();

      } else {
        res.bodyToMono(clazz).doOnNext(onSuccess::accept)
        .doOnError(e -> {
          call.setOk(false);
          call.setErrMsg(e.getMessage());
        }).doFinally(t -> {
          call.setClientEnd(System.nanoTime());
          mutex.release();
        }).subscribe();
      }

    } else {
      call.setOk(false);
      res.bodyToMono(String.class).doOnNext(err -> {
        call.setErrMsg("<" + res.statusCode() + "> " + err);
      }).doFinally( t-> {
        call.setClientEnd(System.nanoTime());
        mutex.release();
      }).subscribe();
    }
  }

  /**
   * Lock mutex
   *
   * @param mutex the semaphore
   */
  private void mutexLock(final Semaphore mutex) {
    try {
      mutex.acquire();
    } catch(final InterruptedException e) {
      // Nothing to do
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
    call.setOk(true);
    call.setProtocol("webflux");
    final Semaphore mutex = new Semaphore(1);
    mutexLock(mutex);
    final Consumer<ClientResponse> okFunc = res -> manageResponse(res, String.class, HttpStatus.CREATED, str -> customer.setId(str), false, call, mutex);
    final Consumer<Throwable> koFunc = e -> manageClientError(e, call, mutex);
    call.setClientStart(System.nanoTime());
    client.mutate().build().post().accept(MediaType.TEXT_PLAIN).contentType(MediaType.APPLICATION_JSON_UTF8)
    .header("request_seq", Integer.toString(requestSeq)).header("mapper", mapper.toUpperCase())
    .body(BodyInserters.fromObject(customer)).exchange()
    .doOnNext(okFunc).doOnError(koFunc).subscribe();
    mutexLock(mutex);
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
    call.setOk(true);
    call.setProtocol("webflux");
    final ArrayList<Customer> customers = new ArrayList<>();
    final Semaphore mutex = new Semaphore(1);
    mutexLock(mutex);
    final Consumer<ClientResponse> okFunc = res -> this.manageResponse(res, Customer.class, cust -> customers.add(cust), true, call, mutex);
    final Consumer<Throwable> koFunc = e -> manageClientError(e, call, mutex);
    call.setClientStart(System.nanoTime());
    client.mutate().build().get().accept(MediaType.APPLICATION_STREAM_JSON)
    .header("request_seq", Integer.toString(requestSeq)).header("mapper", mapper.toUpperCase())
    .exchange().doOnNext(okFunc).doOnError(koFunc).subscribe();
    mutexLock(mutex);
    if(customers.size() != this.customers.size()) {
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
    call.setOk(true);
    call.setProtocol("webflux");
    final Semaphore mutex = new Semaphore(1);
    mutexLock(mutex);
    final Consumer<ClientResponse> okFunc = res -> manageResponse(res, Customer.class, cust -> {
      if(!cust.getId().equals(customer.getId())) {
        LOG.warn("Customer should have " + customer.getId() + " instead of " + cust.getId());
      }
    }, false, call, mutex);
    final Consumer<Throwable> koFunc = e -> manageClientError(e, call, mutex);
    call.setClientStart(System.nanoTime());
    client.mutate().build().get().uri("/{id}", customer.getId()).accept(MediaType.APPLICATION_JSON_UTF8)
    .header("request_seq", Integer.toString(requestSeq)).header("mapper", mapper.toUpperCase())
    .exchange().doOnNext(okFunc).doOnError(koFunc).subscribe();
    mutexLock(mutex);
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
    suite.setProtocol("webflux");
    suite.setCompression(compression);
    suite.setComment(comment);
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

    final Semaphore mutex = new Semaphore(1);
    mutexLock(mutex);
    // Insert test suite general informations +
    LOG.info("Register a new test suite");
    statsClient.mutate().build().post().contentType(MediaType.APPLICATION_JSON_UTF8)
    .body(BodyInserters.fromObject(suite))
    .exchange().doOnNext(res -> {
      res.bodyToMono(String.class).doOnNext(str -> {
        if(res.statusCode() != HttpStatus.CREATED) {
          LOG.error("<" + res.statusCode().value() + "> " + str);
        } else {
          suite.setId(str);
        }
      }).doOnError(e -> {
        LOG.error("HTTP status code: {}, error: {}", res.statusCode(), e.getMessage(), e);
      }).doFinally(t -> {
        mutex.release();
      }).subscribe();
    }).doOnError(e -> {
      LOG.error(e.getMessage(), e);
    }).subscribe();
    mutexLock(mutex);
    // Insert test suite general informations -

    if(suite.getId() != null) {
      // Insert calls +
      LOG.info("Add calls to testsuite {}", suite.getId());
      mutex.release();
      mutexLock(mutex);
      statsClient.mutate().build().post().uri("/{id}/calls", suite.getId()).contentType(MediaType.APPLICATION_STREAM_JSON)
      .body(BodyInserters.fromPublisher(Flux.fromIterable(calls), ClientCall.class))
      .exchange().doOnError(e -> {
        LOG.error("Error while inserting call: " + e.getMessage(), e);
      }).doFinally(t -> {
        mutex.release();
      }).subscribe();
      mutexLock(mutex);
      // Insert calls -
    } else {
      LOG.error("Test suite Identifier is null");
    }
    // Delete calls cache +
    LOG.info("Purge statistics cache");
    mutex.release();
    mutexLock(mutex);
    statsClient.mutate().build().delete().exchange().doFinally(t -> {
      mutex.release();
    }).subscribe();
    mutexLock(mutex);
    // Delete calls cache -
  }
  // Methods -

}
