package com.bcp.bootcamp.service;

import com.bcp.bootcamp.dto.NaturalCustomerDTO;

import reactor.core.publisher.Mono;

public interface ICustomerNaturalService {

	Mono<NaturalCustomerDTO> save(NaturalCustomerDTO customer);
	
	Mono<NaturalCustomerDTO> findById(String id);
	
	Mono<NaturalCustomerDTO> findByDni(String dni);
	
}
