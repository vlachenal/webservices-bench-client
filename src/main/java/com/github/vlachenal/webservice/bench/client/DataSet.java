/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer;


/**
 * Test suite data set
 *
 * @author Vincent Lachenal
 */
@Component
public class DataSet {

  @Value("${dataset.filename}")
  private Resource resource;

  private List<Customer> customers;

  public void loadData() {
    final ObjectMapper mapper = new ObjectMapper();
    try {
      customers = Arrays.asList(mapper.readValue(resource.getInputStream(), Customer[].class));
    } catch(final Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public List<Customer> getRestData() {
    return customers;
  }

}
