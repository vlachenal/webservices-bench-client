/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Abstract test suite
 *
 * @param <T> the customer class
 * @param <C> the call statistic class
 *
 * @author Vincent Lachenal
 */
public abstract class AbstractClientTestSuite<T,C> {

  // Attributes +
  /** Data set */
  @Autowired
  protected DataSet data;

  /** Test suite customers */
  protected List<T> customers;

  /** Calls */
  private List<C> calls;
  // Attributes -


  // Methods +
  /**
   * Delete all customers
   */
  public abstract void deleteAll();

  /**
   * Initialize test suite: fill customers list from data set
   */
  public abstract void initializeTestSuite();

  /**
   * Create customer
   *
   * @param customer the customer to create
   * @param requestSeq the request sequence
   *
   * @return the call statitics
   */
  public abstract C createCustomer(T customer, int requestSeq);

  /**
   * List all customers
   *
   * @param requestSeq the request sequance
   *
   * @return the call statitics
   */
  public abstract C listAll(int requestSeq);

  /**
   * List all customers
   *
   * @param requestSeq the request sequence
   * @param customerthe customer customer
   *
   * @return the call statitics
   */
  public abstract C getDetails(T customer, int requestSeq);

  /**
   * Run test suite
   */
  public void runTest(final int nbThread) {
    // Initialization +
    calls = new ArrayList<>();
    data.loadData();
    deleteAll();
    initializeTestSuite();
    // Initialization -

    // Customer creations +
    {
      final AtomicInteger seq = new AtomicInteger(0);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
      for(final T customer : customers) {
        tasks.add(new Runnable() {
          @Override
          public void run() {
            final C call = createCustomer(customer, seq.getAndIncrement());
            synchronized(calls) {
              calls.add(call);
            }
          }
        });
      }
      final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(nbThread, customers.size(), 10000, TimeUnit.SECONDS, tasks);
      threadPool.shutdown();
    }
    // Customer creations -

    // List all +
    {
      final AtomicInteger seq = new AtomicInteger(0);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
      for(int i = 0 ; i < customers.size() ; ++i) {
        tasks.add(new Runnable() {
          @Override
          public void run() {
            final C call = listAll(seq.getAndIncrement());
            synchronized(calls) {
              calls.add(call);
            }
          }
        });
      }
      final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(nbThread, customers.size(), 10000, TimeUnit.SECONDS, tasks);
      threadPool.shutdown();
    }
    // List all -

    // Get details +
    {
      final AtomicInteger seq = new AtomicInteger(0);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
      for(final T customer : customers) {
        tasks.add(new Runnable() {
          @Override
          public void run() {
            final C call = getDetails(customer, seq.getAndIncrement());
            synchronized(calls) {
              calls.add(call);
            }
          }
        });
      }
      final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(nbThread, customers.size(), 10000, TimeUnit.SECONDS, tasks);
      threadPool.shutdown();
    }
    // Get details -

    // TODO consolidate statistics

    deleteAll();

  }
  // Methods -

}
