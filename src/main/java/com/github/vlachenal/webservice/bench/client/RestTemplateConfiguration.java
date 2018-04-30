package com.github.vlachenal.webservice.bench.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * REST template configuration
 *
 * @author Vincent Lachenal
 */
@Configuration
public class RestTemplateConfiguration {

  /**
   * HTTP Protocol Buffers message converter provider
   *
   * @return the HTTP Protocol Buffers message converter
   */
  @Bean
  public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    return new ProtobufHttpMessageConverter();
  }

  /**
   * Build REST template
   *
   * @param builder the template builder
   * @param protobufConverter Protocol Buffers message converter
   *
   * @return the REST template
   */
  @Bean
  public RestTemplate restTemplate(final RestTemplateBuilder builder, final ProtobufHttpMessageConverter protobufConverter) {
    builder.additionalMessageConverters(protobufConverter);
    return builder.build();
  }

}
