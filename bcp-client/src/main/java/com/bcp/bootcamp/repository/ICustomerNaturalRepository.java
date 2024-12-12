package com.bcp.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bcp.bootcamp.document.CustomerNatural;

import reactor.core.publisher.Mono;

@Repository
public interface ICustomerNaturalRepository extends ReactiveMongoRepository<CustomerNatural, String> {
	
	Mono<CustomerNatural> findByDni(String dni);

}
