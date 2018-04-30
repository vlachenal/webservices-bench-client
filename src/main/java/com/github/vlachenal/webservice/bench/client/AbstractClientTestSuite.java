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
import org.springframework.beans.factory.annotation.Value;


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
  /** {@link AbstractClientTestSuite} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(AbstractClientTestSuite.class);

  /** Data set */
  @Autowired
  protected DataSet data;

  /** CPU */
  @Value("${cpu}")
  protected String cpu;

  /** Memory */
  @Value("${memory}")
  protected String memory;

  /** Test suite customers */
  protected List<T> customers;

  /** Calls */
  protected List<C> calls;

  /** Number of simultaneous calls */
  protected int nbThread;

  /** HTTP compression */
  protected String compression;

  /** Test suite comment */
  protected String comment;

  /** Test suite mapper */
  protected String mapper;
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
   * @param requestSeq the request sequence
   *
   * @return the call statitics
   */
  public abstract C listAll(int requestSeq);

  /**
   * Get customer details
   *
   * @param requestSeq the request sequence
   * @param customerthe customer customer
   *
   * @return the call statitics
   */
  public abstract C getDetails(T customer, int requestSeq);

  /**
   * Consolidate statistics
   */
  public abstract void consolidateStats();

  /**
   * Run test suite
   *
   * @param nbThread number of simultaneous calls
   * @param mapper the mapper to use
   * @param compression HTTP compression (can be null)
   * @param comment the test suite comment (can be null)
   */
  public void runTest(final int nbThread, final String mapper, final String compression, final String comment) {
    // Initialization +
    LOG.info("Initialization");
    this.compression = compression;
    this.comment = comment;
    this.nbThread = nbThread;
    this.mapper = mapper;
    calls = new ArrayList<>();
    data.loadData();
    initializeTestSuite();
    deleteAll();
    LOG.info("Found {} customers in data set", customers.size());
    // Initialization -

    // Customer creations +
    {
      LOG.info("Customers creation");
      final AtomicInteger seq = new AtomicInteger(1);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
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
      try {
        threadPool.awaitTermination(10, TimeUnit.MINUTES);
      } catch(final InterruptedException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
    // Customer creations -

    // List all +
    {
      LOG.info("List all");
      final AtomicInteger seq = new AtomicInteger(1);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
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
      try {
        threadPool.awaitTermination(10, TimeUnit.MINUTES);
      } catch(final InterruptedException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
    // List all -

    // Get details +
    {
      LOG.info("Get details");
      final AtomicInteger seq = new AtomicInteger(1);
      final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
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
      try {
        threadPool.awaitTermination(10, TimeUnit.MINUTES);
      } catch(final InterruptedException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
    // Get details -

    consolidateStats();
    deleteAll();
    try {
      Thread.sleep(1000);
    } catch(final InterruptedException e) {
      // Nothing to do
    }
    System.exit(0);
  }
  // Methods -


  // Accessors +
  /**
   * Data set getter
   *
   * @return the data
   */
  public final DataSet getData() {
    return data;
  }

  /**
   * Data set setter
   *
   * @param data the data to set
   */
  public final void setData(final DataSet data) {
    this.data = data;
  }

  /**
   * CPU information getter
   *
   * @return the cpu
   */
  public final String getCpu() {
    return cpu;
  }

  /**
   * CPU information setter
   *
   * @param cpu the cpu to set
   */
  public final void setCpu(final String cpu) {
    this.cpu = cpu;
  }

  /**
   * RAM informations getter
   *
   * @return the memory
   */
  public final String getMemory() {
    return memory;
  }

  /**
   * RAM informations setter
   *
   * @param memory the memory to set
   */
  public final void setMemory(final String memory) {
    this.memory = memory;
  }

  /**
   * Customers getter
   *
   * @return the customers
   */
  public final List<T> getCustomers() {
    return customers;
  }

  /**
   * Customers setter
   *
   * @param customers the customers to set
   */
  public final void setCustomers(final List<T> customers) {
    this.customers = customers;
  }

  /**
   * Calls getter
   *
   * @return the calls
   */
  public final List<C> getCalls() {
    return calls;
  }

  /**
   * Calls setter
   *
   * @param calls the calls to set
   */
  public final void setCalls(final List<C> calls) {
    this.calls = calls;
  }

  /**
   * Number of simultaneous calls getter
   *
   * @return the number of simultaneous calls
   */
  public final int getNbThread() {
    return nbThread;
  }

  /**
   * Number of simultaneous calls setter
   *
   * @param nbThread the number of simultaneous calls to set
   */
  public final void setNbThread(final int nbThread) {
    this.nbThread = nbThread;
  }

  /**
   * HTTP compression getter
   *
   * @return the compression
   */
  public final String getCompression() {
    return compression;
  }

  /**
   * HTTP compression setter
   *
   * @param compression the compression to set
   */
  public final void setCompression(final String compression) {
    this.compression = compression;
  }

  /**
   * Test suite comment getter
   *
   * @return the comment
   */
  public final String getComment() {
    return comment;
  }

  /**
   * Test suite comment setter
   *
   * @param comment the comment to set
   */
  public final void setComment(final String comment) {
    this.comment = comment;
  }

  /**
   * Mapper getter
   *
   * @return the mapper
   */
  public final String getMapper() {
    return mapper;
  }

  /**
   * Mapper setter
   *
   * @param mapper the mapper to set
   */
  public final void setMapper(final String mapper) {
    this.mapper = mapper;
  }
  // Accessors -


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
