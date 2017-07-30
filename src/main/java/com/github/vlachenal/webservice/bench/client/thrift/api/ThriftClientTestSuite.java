/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.thrift.api;

import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.THttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite;
import com.github.vlachenal.webservice.bench.thrift.api.ClientCall;
import com.github.vlachenal.webservice.bench.thrift.api.CreateRequest;
import com.github.vlachenal.webservice.bench.thrift.api.Customer;
import com.github.vlachenal.webservice.bench.thrift.api.CustomerException;
import com.github.vlachenal.webservice.bench.thrift.api.CustomerService;
import com.github.vlachenal.webservice.bench.thrift.api.GetRequest;
import com.github.vlachenal.webservice.bench.thrift.api.Header;
import com.github.vlachenal.webservice.bench.thrift.api.ListAllRequest;
import com.github.vlachenal.webservice.bench.thrift.api.StatsService;
import com.github.vlachenal.webservice.bench.thrift.api.TestSuite;


/**
 * Thrift client test
 *
 * @author Vincent Lachenal
 */
@Component
public class ThriftClientTestSuite extends AbstractClientTestSuite<Customer, ClientCall> {

  // Attributes +
  /** {@link ThriftClientTestSuite logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(ThriftClientTestSuite.class);

  /** Thrift customer endpoint */
  private static final String CUST_ENDPOINT = "/thrift/customer";

  /** Customer service client pool */
  private TServiceClientPool<CustomerService.Client> customerClientPool;

  /** Server hostname or IP address */
  @Value("${server.host}")
  private String host;

  /** Server port */
  @Value("${server.port}")
  private Integer port;

  /** Server port */
  @Value("${server.base.url}")
  private String baseUrl;
  // Attributes -


  // Methods +
  /**
   * Realease client
   *
   * @param client the client to release
   * @param ok the call state
   */
  private void releaseClient(final CustomerService.Client client, final boolean ok) {
    if(client != null) {
      if(ok) {
        customerClientPool.returnObject(client);
      } else {
        try {
          customerClientPool.invalidateObject(client);
        } catch(final Exception e) {
          LOG.warn(e.getMessage(), e);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#initializeTestSuite()
   */
  @Override
  public void initializeTestSuite() {
    customers = data.getThriftData();
    final String url = "http://" + host + ':' + port + baseUrl + CUST_ENDPOINT;
    final TServiceClientPooledFactory<CustomerService.Client> clientFactory = new TServiceClientPooledFactory<>(new CustomerService.Client.Factory(), new TCompactProtocol.Factory(), new THttpClient.Factory(url));
    final GenericObjectPoolConfig config = new GenericObjectPoolConfig();
    config.setMaxTotal(nbThread);
    config.setMaxIdle(nbThread);
    customerClientPool = new TServiceClientPool<>(clientFactory, config);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#deleteAll()
   */
  @Override
  public void deleteAll() {
    boolean ok = true;
    CustomerService.Client client = null;
    try {
      client = customerClientPool.borrowObject();
      client.deleteAll();
    } catch(final CustomerException e) {
      LOG.error("Unable to remove all entries from database: " + e.getMessage(), e);
    } catch(final Exception e) {
      ok = false;
    } finally {
      releaseClient(client, ok);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#createCustomer(java.lang.Object, int)
   */
  @Override
  public ClientCall createCustomer(final Customer customer, final int requestSeq) {
    final CreateRequest req = new CreateRequest();
    req.setCustomer(customer);
    final Header header = new Header();
    header.setRequestSeq(requestSeq);
    req.setHeader(header);
    final ClientCall call = new ClientCall();
    call.setProtocol("thrift");
    call.setMethod("create");
    call.setRequestSeq(requestSeq);
    call.setOk(false);
    boolean ok = true;
    CustomerService.Client client = null;
    String id = null;
    call.setClientStart(System.nanoTime());
    try {
      client = customerClientPool.borrowObject();
      id = client.create(req);
      call.setOk(true);
    } catch(final CustomerException e) {
      call.setErrMsg(e.getMessage());
    } catch(final Exception e) {
      call.setErrMsg(e.getMessage());
      ok = false;
    } finally {
      releaseClient(client, ok);
      call.setClientEnd(System.nanoTime());
    }
    customer.setId(id);
    return call;
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.client.AbstractClientTestSuite#listAll(int)
   */
  @Override
  public ClientCall listAll(final int requestSeq) {
    final ListAllRequest req = new ListAllRequest();
    final Header header = new Header();
    header.setRequestSeq(requestSeq);
    req.setHeader(header);
    final ClientCall call = new ClientCall();
    call.setMethod("list");
    call.setProtocol("thrift");
    call.setRequestSeq(requestSeq);
    call.setOk(false);
    boolean ok = true;
    CustomerService.Client client = null;
    List<Customer> customers = null;
    call.setClientStart(System.nanoTime());
    try {
      client = customerClientPool.borrowObject();
      customers = client.listCustomers(req);
      call.setOk(true);
    } catch(final CustomerException e) {
      call.setErrMsg(e.getMessage());
    } catch(final Exception e) {
      call.setErrMsg(e.getMessage());
      ok = false;
    } finally {
      releaseClient(client, ok);
      call.setClientEnd(System.nanoTime());
    }
    if(customers == null) {
      call.setOk(false);
      call.setErrMsg("Response is null");
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
    final GetRequest req = new GetRequest();
    final Header header = new Header();
    header.setRequestSeq(requestSeq);
    req.setHeader(header);
    req.setId(customer.getId());
    final ClientCall call = new ClientCall();
    call.setProtocol("thrift");
    call.setMethod("get");
    call.setRequestSeq(requestSeq);
    call.setOk(false);
    boolean ok = true;
    CustomerService.Client client = null;
    Customer cust = null;
    call.setClientStart(System.nanoTime());
    try {
      client = customerClientPool.borrowObject();
      cust = client.get(req);
      call.setOk(true);
    } catch(final CustomerException e) {
      call.setErrMsg(e.getMessage());
    } catch(final Exception e) {
      call.setErrMsg(e.getMessage());
      ok = false;
    } finally {
      releaseClient(client, ok);
      call.setClientEnd(System.nanoTime());
    }
    if(cust == null) {
      call.setOk(false);
      call.setErrMsg("Response is null for customer " + customer.getId());
    } else if(!cust.getId().equals(customer.getId())) {
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
    suite.setProtocol("thrift");
    suite.setCompression(compression);
    suite.setComment(comment);
    suite.setCalls(calls);
    // Gather test suite informations -
    final String url = "http://" + host + ':' + port + baseUrl + "/thrift/statistics";
    final TServiceClientPooledFactory<StatsService.Client> clientFactory = new TServiceClientPooledFactory<>(new StatsService.Client.Factory(), new TCompactProtocol.Factory(), new THttpClient.Factory(url));
    final GenericObjectPoolConfig config = new GenericObjectPoolConfig();
    config.setMaxTotal(1);
    config.setMaxIdle(1);
    final TServiceClientPool<StatsService.Client> pool = new TServiceClientPool<>(clientFactory, config);
    StatsService.Client client = null;
    try {
      client = pool.borrowObject();
      client.consolidate(suite);
      client.purge();
    } catch(final Exception e) {
      LOG.error("Unable to consolidate statistics: " + e.getMessage(), e);
    } finally {
      if(client != null) {
        pool.returnObject(client);
      }
    }
  }
  // Methods -

}
