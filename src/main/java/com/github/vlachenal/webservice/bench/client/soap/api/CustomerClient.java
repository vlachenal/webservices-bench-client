/*
 * Copyright © 2017 Vincent Lachenal
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package com.github.vlachenal.webservice.bench.client.soap.api;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;


/**
 * SOAP customer client
 *
 * @author Vincent Lachenal
 */
public class CustomerClient extends WebServiceGatewaySupport {

  // Methods +
  /**
   * Create new customer
   *
   * @param customer the customer to create
   *
   * @return the new customer identifier
   */
  public String create(final Customer customer, final int requestSeq) {
    final CreateRequest req = new CreateRequest();
    req.setCustomer(customer);
    final CreateResponse res = (CreateResponse)getWebServiceTemplate().marshalSendAndReceive(req, new WSRequestSequenceCB(requestSeq));
    return res.getId();
  }

  /**
   * List all customers
   *
   * @return the customers
   */
  public List<Customer> listCustomers(final int requestSeq) {
    final ListCustomersResponse res = (ListCustomersResponse)getWebServiceTemplate().marshalSendAndReceive(new ListCustomersRequest(), new WSRequestSequenceCB(requestSeq));
    return res.getCustomer();
  }

  /**
   * Get customer details
   *
   * @param id the customer identifier
   *
   * @return the customer
   */
  public Customer getDetails(final String id, final int requestSeq) {
    final GetDetailsRequest req = new GetDetailsRequest();
    req.setId(id);
    final GetDetailsResponse res = (GetDetailsResponse)getWebServiceTemplate().marshalSendAndReceive(req, new WSRequestSequenceCB(requestSeq));
    return res.getCustomer();
  }

  /**
   * Delete all customer
   */
  public void deleteAll() {
    getWebServiceTemplate().marshalSendAndReceive(new DeleteAllRequest());
  }
  // Methods -


  // Classes +
  /**
   * Web service message callback to add request sequence to SOAP header
   *
   * @author Vincent Lachenal
   */
  public class WSRequestSequenceCB implements WebServiceMessageCallback {

    // Attributes +
    /** Request sequence */
    private final int requestSeq;
    // Attributes -

    // Constructors +
    /**
     * {@link WSRequestSequenceCB} constructor
     *
     * @param requestSeq the request sequence
     */
    public WSRequestSequenceCB(final int requestSeq) {
      this.requestSeq = requestSeq;
    }
    // Constructors -

    // Methods +
    /**
     * Add request sequence to SOAP header.<br>
     * {@inheritDoc}
     *
     * @see org.springframework.ws.client.core.WebServiceMessageCallback#doWithMessage(org.springframework.ws.WebServiceMessage)
     */
    @Override
    public void doWithMessage(final WebServiceMessage message) throws IOException, TransformerException {
      final SoapMessage soapMessage = (SoapMessage)message;
      final RequestHeader reqHeader = new RequestHeader();
      reqHeader.setRequestSeq(requestSeq);
      try {
        final JAXBContext context = JAXBContext.newInstance(RequestHeader.class);
        final Marshaller marshall = context.createMarshaller();
        marshall.marshal(reqHeader, soapMessage.getSoapHeader().getResult());
      } catch(final JAXBException e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
    // Methods -

  }
  // Classes -

}
