/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.thrift.api;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.TServiceClient;


/**
 * Thrift client pool
 *
 * @param <S> the Thrift service client
 *
 * @author Vincent Lachenal
 */
public class TServiceClientPool<S extends TServiceClient> extends GenericObjectPool<S> {

  // Constructors +
  /**
   * {@link TServiceClientPool} constructor
   *
   * @param factory the factory to use
   * @param poolConfig the pool configuration
   */
  public TServiceClientPool(final PooledObjectFactory<S> factory, final GenericObjectPoolConfig<S> poolConfig) {
    super(factory, poolConfig);
    setTestOnBorrow(true);
    setTestOnReturn(true);
  }
  // Constructors -


  // Methods +
  /**
   * Close coonnections.<br>
   * {@inheritDoc}
   *
   * @see org.apache.commons.pool2.impl.GenericObjectPool#invalidateObject(java.lang.Object)
   */
  @Override
  public void invalidateObject(final S obj) throws Exception {
    obj.getInputProtocol().getTransport().close();
    obj.getOutputProtocol().getTransport().close();
    super.invalidateObject(obj);
  }
  // Methods -

}
