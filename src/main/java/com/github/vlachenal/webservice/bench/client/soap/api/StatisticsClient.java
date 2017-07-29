/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.soap.api;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


/**
 * SOAP statistics client
 *
 * @author Vincent Lachenal
 */
public class StatisticsClient extends WebServiceGatewaySupport {

  // Methods +
  /**
   * Consolidate statistics
   *
   * @param test the test suite
   */
  public void consolidate(final TestSuite test) {
    final ConsolidateRequest req = new ConsolidateRequest();
    req.setTest(test);
    getWebServiceTemplate().marshalSendAndReceive(req);
  }

  /**
   * Purge statistics
   */
  public void purge() {
    getWebServiceTemplate().marshalSendAndReceive(new PurgeRequest());
  }
  // Methods -

}
