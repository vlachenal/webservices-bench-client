package com.github.vlachenal.webservice.bench.client.protobuf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Protocol buffer messages converter definition
 *
 * @author Vincent Lachenal
 */
@Configuration
public class ProtobufMessageConverterConfig {

  // Methods +
  /**
   * Protocol buffer customer message converter
   *
   * @return the message converter
   */
  @Bean
  public CustomerMessageConverter protobufCustomerConverter() {
    return new CustomerMessageConverter();
  }

  /**
   * Protocol buffer list all response message converter
   *
   * @return the message converter
   */
  @Bean
  public ListAllResponseMessageConverter protobufListAllResponseConverter() {
    return new ListAllResponseMessageConverter();
  }
  // Methods -

}
