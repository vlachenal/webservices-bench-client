/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.thrift.api;

import java.util.NoSuchElementException;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;


/**
 * Thrift client pool
 *
 * @param <T> the Thrift service client
 *
 * @author Vincent Lachenal
 */
public class TServiceClientPool<T extends TServiceClient> extends GenericObjectPool<T> {

  public TServiceClientPool(final PooledObjectFactory<T> factory) {
    super(factory);
    setTestOnBorrow(true);
    setTestOnReturn(true);
  }

  @Override
  public T borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void returnObject(final T obj) {
    // TODO Auto-generated method stub

  }

  @Override
  public void invalidateObject(final T obj) throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
    // TODO Auto-generated method stub

  }

  @Override
  public void clear() throws UnsupportedOperationException {
    // TODO Auto-generated method stub

  }

  @Override
  public void close() {
    // TODO Auto-generated method stub

  }

}
