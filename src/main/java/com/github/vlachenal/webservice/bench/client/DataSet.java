/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

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

  /**
   * Convert the JSON data into SOAP data
   *
   * @return the SOAP data
   *
   * @throws DatatypeConfigurationException date format error
   */
  public List<com.github.vlachenal.webservice.bench.client.soap.api.Customer> getSoapData() throws DatatypeConfigurationException {
    final ArrayList<com.github.vlachenal.webservice.bench.client.soap.api.Customer> customers = new ArrayList<>();
    for(final Customer customer : this.customers) {
      final com.github.vlachenal.webservice.bench.client.soap.api.Customer cust = new com.github.vlachenal.webservice.bench.client.soap.api.Customer();
      cust.setFirstName(customer.getFirstName());
      cust.setLastName(customer.getLastName());
      final GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(customer.getBirthDate());
      cust.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
      cust.setEmail(customer.getEmail());
      final com.github.vlachenal.webservice.bench.client.soap.api.Address addr = new com.github.vlachenal.webservice.bench.client.soap.api.Address();
      for(final String line : customer.getAddress().getLines()) {
        addr.getLines().add(line);
      }
      addr.setZipCode(customer.getAddress().getZipCode());
      addr.setCity(customer.getAddress().getCity());
      addr.setCountry(customer.getAddress().getCity());
      cust.setAddress(addr);
      for(final Phone phone : customer.getPhones()) {
        final com.github.vlachenal.webservice.bench.client.soap.api.Phone phon = new com.github.vlachenal.webservice.bench.client.soap.api.Phone();
        phon.setNumber(phone.getNumber());
        phon.setPhoneType(com.github.vlachenal.webservice.bench.client.soap.api.PhoneType.valueOf(phone.getType().toString()));
        cust.getPhones().add(phon);
      }
      customers.add(cust);
    }
    return customers;
  }

  /**
   * Convert the JSON data into Protobuf data
   *
   * @return the Protobuf data
   */
  public List<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder> getProtobufData() {
    final ArrayList<com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder> customers = new ArrayList<>();
    for(final Customer customer : this.customers) {
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Builder cust = com.github.vlachenal.webservice.bench.protobuf.api.Customer.newBuilder();
      cust.setFirstName(customer.getFirstName());
      cust.setLastName(customer.getLastName());
      cust.setBirthDate(customer.getBirthDate().getTime());
      cust.setEmail(customer.getEmail());
      final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.Builder addr = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Address.newBuilder();
      addr.addAllLines(customer.getAddress().getLines());
      addr.setZipCode(customer.getAddress().getZipCode());
      addr.setCity(customer.getAddress().getCity());
      addr.setCountry(customer.getAddress().getCity());
      cust.setAddress(addr);
      for(final Phone phone : customer.getPhones()) {
        final com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.Builder phon = com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.newBuilder();
        phon.setNumber(phone.getNumber());
        phon.setType(com.github.vlachenal.webservice.bench.protobuf.api.Customer.Phone.PhoneType.valueOf(phone.getType().toString()));
        cust.addPhones(phon);
      }
      customers.add(cust);
    }
    return customers;
  }
  // Methods -


  // Accessors +
  /**
   * Customer list getter
   *
   * @return the customers
   */
  public final List<Customer> getCustomers() {
    return customers;
  }

  /**
   * Customer list setter
   *
   * @param customers the customers to set
   */
  public final void setCustomers(final List<Customer> customers) {
    this.customers = customers;
  }
  // Accessors -

}
