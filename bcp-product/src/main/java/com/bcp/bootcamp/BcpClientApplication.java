package com.bcp.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableReactiveFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class BcpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcpClientApplication.class, args);
	}

}
