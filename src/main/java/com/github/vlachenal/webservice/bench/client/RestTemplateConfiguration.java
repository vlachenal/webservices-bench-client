package com.github.vlachenal.webservice.bench.client;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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
   * REST HTTP client configuration
   *
   * @return the request factory
   */
  public ClientHttpRequestFactory clientHttpFactory() {
    return new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                                                      .setMaxConnPerRoute(20)
                                                      .setMaxConnTotal(200)
                                                      .build());
  }

  /**
   * Build REST template
   *
   * @param builder the template builder
   * @param protobufConverter Protocol Buffers message converter
   * @param clientHttpFactory the client factory
   *
   * @return the REST template
   */
  @Bean
  public RestTemplate restTemplate(final RestTemplateBuilder builder,
                                   final ProtobufHttpMessageConverter protobufConverter) {
    return builder.additionalMessageConverters(protobufConverter)
        .detectRequestFactory(false)
        .requestFactory(this::clientHttpFactory)
        .build();
  }

}
