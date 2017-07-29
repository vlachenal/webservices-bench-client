/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.soap.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * SOAP customer configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
public class CustomerConfiguration {

  // Attributes +
  /** Server hostname or IP address */
  @Value("${server.host}")
  private String host;

  /** Server port */
  @Value("${server.port}")
  private Integer port;

  /** Server port */
  @Value("${server.base.url}")
  private String baseUrl;
  // Attributes -


  // Methods +
  /**
   * Customer marshaller
   *
   * @return the marshaller
   */
  @Bean
  public Jaxb2Marshaller customerMarshaller() {
    final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("com.github.vlachenal.webservice.bench.client.soap.api");
    return marshaller;
  }

  /**
   * Customer client
   *
   * @param customerMarshaller the marshaller to use
   *
   * @return the customer client
   */
  @Bean
  public CustomerClient customerClient(final Jaxb2Marshaller customerMarshaller) {
    final CustomerClient client = new CustomerClient();
    client.setDefaultUri("http://" + host + ':' + port + baseUrl + "/soap/customer");
    client.setMarshaller(customerMarshaller);
    client.setUnmarshaller(customerMarshaller);
    return client;
  }
  // Methods -

}
