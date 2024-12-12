package com.bcp.bootcamp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = { BcpClientApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { WireMockConfig.class })
@Slf4j
class BcpClientApplicationTests {
	
	  @LocalServerPort
	  private int randomServerPort;
	  private String SERVER_PATH;
	  @Autowired
	  private WireMockServer wireMockServer;
	  @Autowired
	  private TestRestTemplate restTemplate;
	  private ObjectMapper objectMapper = new ObjectMapper();

	  @BeforeAll
	  public void setup() {
	    SERVER_PATH = "http://localhost:" + randomServerPort + "/api/v1/";
	  }

	  @BeforeEach
	  public void init(){
	    wireMockServer.start();
	  }

	  @AfterEach
	  public void end(){
	    wireMockServer.stop();
	  }

}
