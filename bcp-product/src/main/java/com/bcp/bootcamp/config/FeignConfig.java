package com.bcp.bootcamp.config;

import java.time.Clock;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.ReactiveOptions;
import reactivefeign.client.log.DefaultReactiveLogger;
import reactivefeign.client.log.ReactiveLoggerListener;
import reactivefeign.webclient.WebReactiveOptions;

@Configuration
@SuppressWarnings("java:S3740")
public class FeignConfig {

  @Bean
  public ReactiveOptions reactiveOptions() {
    return new WebReactiveOptions.Builder()
        .setReadTimeoutMillis(15000)
        .setWriteTimeoutMillis(15000)
        .setResponseTimeoutMillis(15000)
        .build();
  }

  @Bean
  public ReactiveLoggerListener loggerListener() {
    return new DefaultReactiveLogger(Clock.systemUTC(),
        LoggerFactory.getLogger(FeignConfig.class.getName()));
  }
}
