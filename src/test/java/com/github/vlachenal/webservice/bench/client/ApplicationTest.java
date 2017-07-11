/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * Test application entry point
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class ApplicationTest {

  // Methods +
  /**
   * Application entry point
   *
   * @param args command line arguments
   */
  public static void main(final String args[]) {
    SpringApplication.run(ApplicationTest.class, args);
  }

  /**
   * Build REST template
   *
   * @param builder the template builder
   *
   * @return the REST template
   */
  @Bean
  public RestTemplate restTemplate(final RestTemplateBuilder builder) {
    return builder.build();
  }
  // Methods -

}
