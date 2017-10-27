/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.protobuf.GeneratedMessageV3;


/**
 * Protobuf message converter
 *
 * @param <T> the Protocol Buffer message type
 *
 * @author Vincent Lachenal
 */
public abstract class ProtobufMessageConverter<T extends GeneratedMessageV3> implements HttpMessageConverter<T> {

  // Methods +
  /**
   * Check class
   *
   * @param clazz the class to check
   *
   * @return <code>true</code> if class extends Protobuf message, <code>false</code> otherwise
   */
  protected abstract boolean checkClass(final Class<?> clazz);

  /**
   * Only check MIME type.<br>
   * {@inheritDoc}
   *
   * @see org.springframework.http.converter.HttpMessageConverter#canRead(java.lang.Class, org.springframework.http.MediaType)
   */
  @Override
  public boolean canRead(final Class<?> clazz, final MediaType mediaType) {
    return checkClass(clazz) && (ProtobufType.PROTOBUF.includes(mediaType) || MediaType.APPLICATION_JSON_UTF8.equals(mediaType));
  }

  /**
   * Only check MIME type.<br>
   * {@inheritDoc}
   *
   * @see org.springframework.http.converter.HttpMessageConverter#canWrite(java.lang.Class, org.springframework.http.MediaType)
   */
  @Override
  public boolean canWrite(final Class<?> clazz, final MediaType mediaType) {
    return checkClass(clazz) && (ProtobufType.PROTOBUF.includes(mediaType) || MediaType.APPLICATION_JSON_UTF8.equals(mediaType));
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.http.converter.HttpMessageConverter#getSupportedMediaTypes()
   */
  @Override
  public List<MediaType> getSupportedMediaTypes() {
    return Arrays.asList(ProtobufType.PROTOBUF, ProtobufType.PROTOBUF_UTF8, MediaType.APPLICATION_JSON_UTF8);
  }

  /**
   * Convert input stream to message
   *
   * @param input the input stream
   *
   * @return the message
   *
   * @throws IOException I/O error
   */
  protected abstract T fromProtobuf(InputStream input) throws IOException;

  /**
   * Convert JSON input stream to message
   *
   * @param input the JSON input stream
   *
   * @return the message
   *
   * @throws IOException I/O error
   */
  protected abstract T fromJSON(InputStream input) throws IOException;

  /**
   * Write message on output stream in JSON format
   *
   * @param message the message
   * @param out the output stream
   *
   * @throws IOException I/O error
   */
  protected abstract void writeJSON(T message, OutputStream out) throws IOException;

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.http.converter.HttpMessageConverter#read(java.lang.Class, org.springframework.http.HttpInputMessage)
   */
  @Override
  public T read(final Class<? extends T> clazz, final HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    T message = null;
    if(MediaType.APPLICATION_JSON_UTF8.equals(inputMessage.getHeaders().getContentType())) {
      message = fromJSON(inputMessage.getBody());
    } else if(ProtobufType.PROTOBUF.includes(inputMessage.getHeaders().getContentType())) {
      message = fromProtobuf(inputMessage.getBody());
    } else {
      throw new HttpMessageNotReadableException("Invalid content type: " + inputMessage.getHeaders().getContentType().toString());
    }
    return message;
  }

  /**
   * {@inheritDoc}
   *
   * @see org.springframework.http.converter.HttpMessageConverter#write(java.lang.Object, org.springframework.http.MediaType, org.springframework.http.HttpOutputMessage)
   */
  @Override
  public void write(final T message, final MediaType contentType, final HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    if(MediaType.APPLICATION_JSON_UTF8.equals(contentType)) {
      writeJSON(message, outputMessage.getBody());
    } else if(ProtobufType.PROTOBUF.includes(contentType)) {
      message.writeTo(outputMessage.getBody());
    } else {
      throw new HttpMessageNotWritableException("Invalid content type: " + contentType.toString());
    }
  }
  // Methods -

}
