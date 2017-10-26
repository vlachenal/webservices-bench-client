/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.github.vlachenal.webservice.bench.protobuf.api.Customer;
import com.github.vlachenal.webservice.bench.protobuf.api.ListAllResponse;
import com.google.protobuf.util.JsonFormat;


/**
 * Protobuf list all response message converter
 *
 * @author Vincent Lachenal
 */
public class ListAllResponseMessageConverter extends ProtobufMessageConverter<ListAllResponse> {

  // Methods +
  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#checkClass(java.lang.Class)
   */
  @Override
  protected boolean checkClass(final Class<?> clazz) {
    return clazz.isAssignableFrom(Customer.class);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#fromProtobuf(java.io.InputStream)
   */
  @Override
  protected ListAllResponse fromProtobuf(final InputStream input) throws IOException {
    return ListAllResponse.parseFrom(input);
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#fromJSON(java.io.InputStream)
   */
  @Override
  protected ListAllResponse fromJSON(final InputStream input) throws IOException {
    final ListAllResponse.Builder builder = ListAllResponse.newBuilder();
    JsonFormat.parser().merge(new InputStreamReader(input), builder);
    return builder.build();
  }

  /**
   * {@inheritDoc}
   *
   * @see com.github.vlachenal.webservice.bench.protobuf.ProtobufMessageConverter#writeJSON(com.google.protobuf.GeneratedMessageV3, java.io.OutputStream)
   */
  @Override
  protected void writeJSON(final ListAllResponse customer, final OutputStream out) throws IOException {
    JsonFormat.printer().appendTo(customer, new OutputStreamWriter(out));
  }
  // Methods -

}
