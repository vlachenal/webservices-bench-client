/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.github.vlachenal.webservice.bench.client.utils.ApplicationProfiles;


/**
 * Application test entry point
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@PropertySource(value={"classpath:application.properties","classpath:hardware.properties"})
@Profile(ApplicationProfiles.TEST)
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
    builder.errorHandler(new ResponseErrorHandler() {

      @Override
      public boolean hasError(final ClientHttpResponse response) throws IOException {
        // TODO Auto-generated method stub
        return false;
      }

      @Override
      public void handleError(final ClientHttpResponse response) throws IOException {
        // TODO Auto-generated method stub
      }

    });
    return builder.build();
  }
  // Methods -

}
