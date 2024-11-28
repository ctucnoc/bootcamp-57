package com.bcp.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BcpRegisterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcpRegisterServerApplication.class, args);
	}

}
