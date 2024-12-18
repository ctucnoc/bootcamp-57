package com.bcp.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bcp.bootcamp.document.Product;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String>{

}
