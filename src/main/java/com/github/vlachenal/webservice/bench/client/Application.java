/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.github.vlachenal.webservice.bench.client.protobuf.api.ProtobufRESTClient;
import com.github.vlachenal.webservice.bench.client.rest.api.RESTfulClient;
import com.github.vlachenal.webservice.bench.client.soap.api.SOAPClientTestSuite;
import com.github.vlachenal.webservice.bench.client.thrift.api.ThriftClientTestSuite;
import com.github.vlachenal.webservice.bench.client.utils.ApplicationProfiles;
import com.github.vlachenal.webservice.bench.client.webflux.api.RESTWebfluxClient;


/**
 * Application entry point
 *
 * @author Vincent Lachenal
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@PropertySource({"classpath:application.properties","classpath:hardware.properties"})
@Profile(ApplicationProfiles.NOT_TEST)
public class Application {

  // Attributes +
  /** {@link Application} logger instance */
  private static final Logger LOG = LoggerFactory.getLogger(Application.class);
  // Attributes -


  // Methods +
  /**
   * Application entry point
   *
   * @param args command line arguments
   */
  public static void main(final String args[]) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Print help
   */
  private void printHelp() {
    LOG.info("string: protocol to use ('rest','thrift','soap', 'protobuf' or 'webflux' for now)");
    LOG.info("int: number of simultanous request to proccess");
    LOG.info("string (optional): HTTP compression (use null or none for no compression)");
    LOG.info("string (optional): test suite comment");
    LOG.info("string (optional): Mapper (manual, dozer or mapstruct)");
  }

  /**
   * Run client
   *
   * @param restClient the RESTful client to use
   * @param thriftClient the Thrift test suite to use
   * @param soapClient the SOAP test suite to use
   * @param protobufClient the Protocol Buffer test suite to use
   * @param webfluxClient the REST webflux client to use
   *
   * @return the command line runner
   *
   * @throws Exception any error
   */
  @Bean
  public CommandLineRunner run(final RESTfulClient restClient,
                               final ThriftClientTestSuite thriftClient,
                               final SOAPClientTestSuite soapClient,
                               final ProtobufRESTClient protobufClient,
                               final RESTWebfluxClient webfluxClient) throws Exception {
    return args -> {
      // Check arguments +
      if(args.length < 2) {
        LOG.error("Invalid number of arguments");
        printHelp();
        throw new Exception("Invalid number of arguments");
      }
      int nbThread = -1;
      try {
        nbThread = Integer.parseInt(args[1]);
      } catch(final Exception e) {
        LOG.error("Number of thread is not an integer: " + args[1], e);
        throw new Exception("Number of thread is not an integer: " + args[1], e);
      }
      if(nbThread < 1) {
        LOG.error("Number of thread must be superior to 1: " + nbThread);
        throw new Exception("Number of thread must be superior to 1: " + nbThread);
      }
      String compression = null;
      if(args.length > 2) {
        compression = args[2];
        if("none".equals(compression) || "null".equals(compression)) {
          compression = null;
        }
      }
      String comment = null;
      if(args.length > 3) {
        comment = args[3];
      }
      String mapper = "manual";
      if(args.length > 4) {
        mapper = args[4];
      }
      // Check arguments -
      if("rest".equals(args[0])) {
        restClient.runTest(nbThread, mapper, compression, comment);
      } else if("thrift".equals(args[0])) {
        thriftClient.runTest(nbThread, mapper, compression, comment);
      } else if("soap".equals(args[0])) {
        soapClient.runTest(nbThread, mapper, compression, comment);
      } else if("protobuf".equals(args[0])) {
        protobufClient.runTest(nbThread, mapper, compression, comment);
      } else if("webflux".equals(args[0])) {
        webfluxClient.runTest(nbThread, mapper, compression, comment);
      } else {
        LOG.error("Invalid protocol: " + args[0]);
        printHelp();
        throw new Exception("Invalid protocol: " + args[0]);
      }
    };
  }
  // Methods -

}
