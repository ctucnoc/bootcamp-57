package com.bcp.bootcamp.service;

import com.bcp.bootcamp.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

	Mono<ProductDTO> save(ProductDTO customer);
	
	Mono<ProductDTO> findById(String id);
	
	Flux<ProductDTO> findAll();
	
	Mono<ProductDTO> update(ProductDTO customer, String id);
	
	Mono<Void> delete(String id);
	
}
