package com.bcp.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.bcp.bootcamp.document.Customer;

@Repository
public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String>{

}
