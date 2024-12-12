package com.bcp.bootcamp;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockConfig {

  @Value("${feign.pokemon.port}")
  private String wiremockPort;

  @Bean
  public WireMockServer wireMockServer(){
    return new WireMockServer(Integer.parseInt(wiremockPort));
  }

}
