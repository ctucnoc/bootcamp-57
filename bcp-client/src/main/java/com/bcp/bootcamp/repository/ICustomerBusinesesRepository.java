package com.bcp.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.bcp.bootcamp.document.CustomerBusineses;
import reactor.core.publisher.Mono;

@Repository
public interface ICustomerBusinesesRepository extends ReactiveMongoRepository<CustomerBusineses	, String> {

	Mono<CustomerBusineses> findByRuc(String ruc);
	
}
