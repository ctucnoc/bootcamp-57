package com.bcp.bootcamp.api.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.bcp.bootcamp.controller.ApiApiDelegate;
import com.bcp.bootcamp.dto.BusinessCustomerDTO;
import com.bcp.bootcamp.dto.NaturalCustomerDTO;
import com.bcp.bootcamp.service.ICustomerBusinesesService;
import com.bcp.bootcamp.service.ICustomerNaturalService;
import reactor.core.publisher.Mono;

@Component
public class CustomerNaturalApiDelegateImpl implements ApiApiDelegate {

	final ICustomerNaturalService customerNaturalService;
	
	final ICustomerBusinesesService customerBusinesesService;

	public CustomerNaturalApiDelegateImpl(ICustomerNaturalService customerNaturalService, ICustomerBusinesesService customerBusinesesService) {
		this.customerNaturalService = customerNaturalService;
		this.customerBusinesesService = customerBusinesesService;
	}

	@Override
	public Mono<ResponseEntity<NaturalCustomerDTO>> saveNatural(Mono<NaturalCustomerDTO> naturalCustomerDTO,
			ServerWebExchange exchange) {
		return naturalCustomerDTO.flatMap(dto -> customerNaturalService.save(dto)
				.map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved)).log());
	}
	
	@Override
	public Mono<ResponseEntity<NaturalCustomerDTO>> findByIdCustomerNatural(String id,
	        ServerWebExchange exchange){
		return customerNaturalService.findById(id).map(bean -> ResponseEntity.ok().body(bean)).log();
	}
	
	@Override
	public Mono<ResponseEntity<NaturalCustomerDTO>> findByDniCustomerNatural(String dni,
	        ServerWebExchange exchange) {
		return customerNaturalService.findById(dni).map(bean -> ResponseEntity.ok().body(bean)).log();
	}
	
	@Override
	public Mono<ResponseEntity<BusinessCustomerDTO>> saveBusineses(Mono<BusinessCustomerDTO> businessCustomerDTO,
	        ServerWebExchange exchange) {
		return businessCustomerDTO.flatMap(dto -> customerBusinesesService.save(dto)
				.map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved)).log());
	}
	
	@Override
	public Mono<ResponseEntity<BusinessCustomerDTO>> findByIdCustomerBusineses(String id,
	        ServerWebExchange exchange){
		return customerBusinesesService.findById(id).map(bean -> ResponseEntity.ok().body(bean)).log();
	}
	
	@Override
	public Mono<ResponseEntity<BusinessCustomerDTO>> findByDniCustomerBusineses(String dni,
	        ServerWebExchange exchange) {
		return customerBusinesesService.findByRuc(dni).map(bean -> ResponseEntity.ok().body(bean)).log();
	}

}
