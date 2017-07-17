/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.Customer;
import com.github.vlachenal.webservice.bench.client.rest.api.bean.Phone;
import com.github.vlachenal.webservice.bench.thrift.api.Address;
import com.github.vlachenal.webservice.bench.thrift.api.PhoneType;


/**
 * Test suite data set
 *
 * @author Vincent Lachenal
 */
@Component
public class DataSet {

  // Attributes +
  @Value("${dataset.filename}")
  private Resource resource;

  /** Customers found in data set */
  private List<Customer> customers;
  // Attributes -


  // Methods +
  /**
   * Load data from file
   */
  public void loadData() {
    final ObjectMapper mapper = new ObjectMapper();
    try {
      customers = Arrays.asList(mapper.readValue(resource.getInputStream(), Customer[].class));
    } catch(final Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * Directly return the data as it is already in JSON format
   *
   * @return the REST data
   */
  public List<Customer> getRestData() {
    return customers;
  }

  /**
   * Convert the JSON data into Thrift data
   *
   * @return the Thrift data
   */
  public List<com.github.vlachenal.webservice.bench.thrift.api.Customer> getThriftData() {
    final ArrayList<com.github.vlachenal.webservice.bench.thrift.api.Customer> customers = new ArrayList<>();
    for(final Customer customer : this.customers) {
      final com.github.vlachenal.webservice.bench.thrift.api.Customer cust = new com.github.vlachenal.webservice.bench.thrift.api.Customer();
      cust.setFirstName(customer.getFirstName());
      cust.setLastName(customer.getLastName());
      cust.setBirthDate(customer.getBirthDate().getTime());
      cust.setEmail(customer.getEmail());
      final Address addr = new Address();
      for(final String line : customer.getAddress().getLines()) {
        addr.addToLines(line);
      }
      addr.setZipCode(customer.getAddress().getZipCode());
      addr.setCity(customer.getAddress().getCity());
      addr.setCountry(customer.getAddress().getCity());
      cust.setAddress(addr);
      for(final Phone phone : customer.getPhones()) {
        final com.github.vlachenal.webservice.bench.thrift.api.Phone phon = new com.github.vlachenal.webservice.bench.thrift.api.Phone();
        phon.setNumber(phone.getNumber());
        phon.setType(PhoneType.valueOf(phone.getType().toString()));
        cust.addToPhones(phon);
      }
      customers.add(cust);
    }
    return customers;
  }
  // Methods -

}
