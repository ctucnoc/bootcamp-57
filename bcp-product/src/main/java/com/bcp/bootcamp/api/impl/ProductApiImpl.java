package com.bcp.bootcamp.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.bcp.bootcamp.controller.ApiApiDelegate;
import com.bcp.bootcamp.dto.BankAccountDTO;
import com.bcp.bootcamp.dto.ProductDTO;
import com.bcp.bootcamp.orquestacion.service.IBankAccountOrquestacionService;
import com.bcp.bootcamp.service.IProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductApiImpl implements ApiApiDelegate{
	
	final
	IProductService productService;
	
	final
	IBankAccountOrquestacionService accountOrquestacionService;

	public ProductApiImpl(IProductService productService, IBankAccountOrquestacionService accountOrquestacionService) {
		this.productService = productService;
		this.accountOrquestacionService = accountOrquestacionService;
	}
	
	@Override
	public Mono<ResponseEntity<Flux<ProductDTO>>> findAllProduct(ServerWebExchange exchange){
		return Mono.just(ResponseEntity.ok().body(productService.findAll().log()));
	}
	
	@Override
	public Mono<ResponseEntity<ProductDTO>> findByIdProduct(String id,
	        ServerWebExchange exchange) {
		return productService.findById(id).map(bean -> ResponseEntity.ok().body(bean)).log();
	}
	
	@Override
	public Mono<ResponseEntity<ProductDTO>> saveProduct(Mono<ProductDTO> productDTO,
	        ServerWebExchange exchange){
		return productDTO.flatMap(dto -> productService.save(dto)
				.map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved)).log());
	}
	
	@Override
	public Mono<ResponseEntity<BankAccountDTO>> saveBankAccount(Mono<BankAccountDTO> bankAccountDTO,
	        ServerWebExchange exchange){
		return bankAccountDTO.flatMap(dto -> accountOrquestacionService.save(dto)
				.map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved)).log());
	}	
	
	@Override
	public Mono<ResponseEntity<Void>> deleteProduct(String id,
	        ServerWebExchange exchange){
		return productService.delete(id).then(Mono.just(ResponseEntity.noContent().build()));
	}
	
	@Override
	public Mono<ResponseEntity<ProductDTO>> updateProduct(String id,
	        Mono<ProductDTO> productDTO,
	        ServerWebExchange exchange){
		return productDTO.flatMap(bean -> productService.update(bean, id).map(x -> ResponseEntity.ok().body(x)));
	}

}
