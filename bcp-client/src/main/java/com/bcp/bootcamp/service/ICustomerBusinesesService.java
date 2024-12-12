package com.bcp.bootcamp.service;

import com.bcp.bootcamp.dto.BusinessCustomerDTO;
import reactor.core.publisher.Mono;

public interface ICustomerBusinesesService {

	Mono<BusinessCustomerDTO> save(BusinessCustomerDTO customer);
	
	Mono<BusinessCustomerDTO> findById(String id);
	
	Mono<BusinessCustomerDTO> findByRuc(String dni);
	
}
