package com.bcp.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BcpConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcpConfigServerApplication.class, args);
	}

}
