/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.thrift.api;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportFactory;


/**
 * Pooled Thrift service client factory
 *
 * @author Vincent Lachenal
 */
public class TServiceClientPooledFactory<T extends TServiceClient> extends BasePooledObjectFactory<T> {

  // Attributes +
  /** Thrift service client factory */
  private final TServiceClientFactory<T> clientFactory;

  /** Thrift protocol factory */
  private final TProtocolFactory protocolFactory;

  /** Thrift transport factory */
  private final TTransportFactory transportFactory;
  // Attributes -


  // Constructors +
  /**
   * {@link TServiceClientPooledFactory} constructor
   *
   * @param clientFactory the Thrift service client factory
   * @param protocolFactory the Thrift protocol factory
   * @param transportFactory the Thrift transport factory
   */
  public TServiceClientPooledFactory(final TServiceClientFactory<T> clientFactory,
                                     final TProtocolFactory protocolFactory,
                                     final TTransportFactory transportFactory) {
    super();
    this.clientFactory = clientFactory;
    this.protocolFactory = protocolFactory;
    this.transportFactory = transportFactory;
  }
  // Constructors -


  // Methods +
  /**
   * Create a new Thrift service client instance.<br>
   * {@inheritDoc}
   *
   * @see org.apache.commons.pool2.BasePooledObjectFactory#create()
   */
  @Override
  public T create() throws Exception {
    final TTransport transport = transportFactory.getTransport(null);
    transport.open();
    final TProtocol protocol = protocolFactory.getProtocol(transport);
    return clientFactory.getClient(protocol);
  }

  /**
   * {@inheritDoc}
   *
   * @see org.apache.commons.pool2.BasePooledObjectFactory#wrap(java.lang.Object)
   */
  @Override
  public PooledObject<T> wrap(final T obj) {
    return new DefaultPooledObject<>(obj);
  }

  /**
   * Close connections on destroy.<br>
   * {@inheritDoc}
   *
   * @see org.apache.commons.pool2.BasePooledObjectFactory#destroyObject(org.apache.commons.pool2.PooledObject)
   */
  @Override
  public void destroyObject(final PooledObject<T> p) throws Exception {
    final T client = p.getObject();
    client.getInputProtocol().getTransport().close();
    client.getOutputProtocol().getTransport().close();
    super.destroyObject(p);
  }

  /**
   * Check that client is not closed.<br>
   * {@inheritDoc}
   *
   * @see org.apache.commons.pool2.BasePooledObjectFactory#validateObject(org.apache.commons.pool2.PooledObject)
   */
  @Override
  public boolean validateObject(final PooledObject<T> p) {
    final T client = p.getObject();
    return client.getInputProtocol().getTransport().isOpen() && client.getOutputProtocol().getTransport().isOpen();
  }
  // Methods -

}
