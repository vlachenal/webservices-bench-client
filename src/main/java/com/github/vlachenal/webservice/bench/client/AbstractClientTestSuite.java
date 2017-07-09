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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  /** {@link AbstractClientTestSuite logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(AbstractClientTestSuite.class);

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
    LOG.info("Initialization");
    // Initialization +
    calls = new ArrayList<>();
    data.loadData();
    deleteAll();
    initializeTestSuite();
    LOG.info("Found {} customers in data set", customers.size());
    // Initialization -

    // Customer creations +
    {
      LOG.info("Customers creation");
      final AtomicInteger seq = new AtomicInteger(0);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
      final PausableThreadPoolExecutor threadPool = new PausableThreadPoolExecutor(nbThread, customers.size(), 10000, TimeUnit.SECONDS, tasks);
      threadPool.prestartAllCoreThreads();
      threadPool.pause();
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
      threadPool.resume();
      threadPool.shutdown();
    }
    // Customer creations -

    // List all +
    {
      LOG.info("List all");
      final AtomicInteger seq = new AtomicInteger(0);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
      final PausableThreadPoolExecutor threadPool = new PausableThreadPoolExecutor(nbThread, customers.size(), 10000, TimeUnit.SECONDS, tasks);
      threadPool.prestartAllCoreThreads();
      threadPool.pause();
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
      threadPool.resume();
      threadPool.shutdown();
    }
    // List all -

    // Get details +
    {
      LOG.info("Get details");
      final AtomicInteger seq = new AtomicInteger(0);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
      final PausableThreadPoolExecutor threadPool = new PausableThreadPoolExecutor(nbThread, customers.size(), 10000, TimeUnit.SECONDS, tasks);
      threadPool.prestartAllCoreThreads();
      threadPool.pause();
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
      threadPool.resume();
      threadPool.shutdown();
    }
    // Get details -

    // TODO consolidate statistics

    deleteAll();

  }
  // Methods -

  // Classes +
  /**
   * Pausable thread pool executor from Oracle javadoc
   *
   * @author ???
   */
  private static class PausableThreadPoolExecutor extends ThreadPoolExecutor {

    /** Is paused */
    private boolean isPaused;

    /** Mutex */
    private final ReentrantLock pauseLock = new ReentrantLock();

    /** Condition variable */
    private final Condition unpaused = pauseLock.newCondition();

    /**
     * {@link PausableThreadPoolExecutor} constructor
     *
     * @param corePoolSize core pool size
     * @param maximumPoolSize maximum pool size
     * @param keepAliveTime keep alive
     * @param unit unit
     * @param workQueue queue
     */
    public PausableThreadPoolExecutor(final int corePoolSize,
                                      final int maximumPoolSize,
                                      final long keepAliveTime,
                                      final TimeUnit unit,
                                      final BlockingQueue<Runnable> workQueue) {
      super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.concurrent.ThreadPoolExecutor#beforeExecute(java.lang.Thread, java.lang.Runnable)
     */
    @Override
    protected void beforeExecute(final Thread t, final Runnable r) {
      super.beforeExecute(t, r);
      pauseLock.lock();
      try {
        while (isPaused) {
          unpaused.await();
        }
      } catch (final InterruptedException ie) {
        t.interrupt();
      } finally {
        pauseLock.unlock();
      }
    }

    /**
     * Pause executor
     */
    public void pause() {
      pauseLock.lock();
      try {
        isPaused = true;
      } finally {
        pauseLock.unlock();
      }
    }

    /**
     * Resume executor
     */
    public void resume() {
      pauseLock.lock();
      try {
        isPaused = false;
        unpaused.signalAll();
      } finally {
        pauseLock.unlock();
      }
    }
  }
  // Classes -

}
