/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.rest.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Client call bean
 *
 * @author Vincent Lachenal
 */
public class ClientCall {

  // Attributes +
  /** Request sequence identifier */
  @JsonProperty(value="request_seq",required=true)
  private Integer requestSeq;

  /** Protocol (always 'rest') */
  private final String protocol = "rest";

  /** The method which has been called */
  @JsonProperty(value="method",required=true)
  private String method;

  /** Client start timestamp */
  @JsonProperty(value="client_start",required=true)
  private Long clientStart;

  /** Client end timestamp */
  @JsonProperty(value="client_end",required=true)
  private Long clientEnd;
  // Attributes -


  // Accessors +
  /**
   * Request sequence getter
   *
   * @return the request sequence
   */
  public final Integer getRequestSeq() {
    return requestSeq;
  }

  /**
   * Request sequence setter
   *
   * @param requestSeq the request sequence to set
   */
  public final void setRequestSeq(final Integer requestSeq) {
    this.requestSeq = requestSeq;
  }

  /**
   * Protocol getter
   *
   * @return the protocol
   */
  public final String getProtocol() {
    return protocol;
  }

  /**
   * Method getter
   *
   * @return the method
   */
  public final String getMethod() {
    return method;
  }

  /**
   * Method setter
   *
   * @param method the method to set
   */
  public final void setMethod(final String method) {
    this.method = method;
  }

  /**
   * Client start time in ns getter
   *
   * @return the time
   */
  public final Long getClientStart() {
    return clientStart;
  }

  /**
   * Client start time in ns setter
   *
   * @param clientStart the time to set
   */
  public final void setClientStart(final Long clientStart) {
    this.clientStart = clientStart;
  }

  /**
   * Client end time in ns getter
   *
   * @return the time
   */
  public final Long getClientEnd() {
    return clientEnd;
  }

  /**
   * Client end time in ns setter
   *
   * @param clientEnd the time to set
   */
  public final void setClientEnd(final Long clientEnd) {
    this.clientEnd = clientEnd;
  }
  // Accessors -

}
