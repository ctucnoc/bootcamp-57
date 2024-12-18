package com.bcp.bootcamp.client.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bcp.bootcamp.client.customer.dto.BusinessCustomerDTO;
import com.bcp.bootcamp.config.FeignConfig;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "${feign.bcp-client.name}", configuration = FeignConfig.class)
public interface ICustomerBusinesesClient {

	@GetMapping("/api/v1/customer-busineses/search/{dni}")
	public Mono<BusinessCustomerDTO> findByDniCustomerBusineses(@PathVariable(value = "dni") String dni);
	
}
