package com.bcp.bootcamp.orquestacion.service;

import com.bcp.bootcamp.dto.BankAccountDTO;

import reactor.core.publisher.Mono;

public interface IBankAccountOrquestacionService {

	Mono<BankAccountDTO> save(BankAccountDTO bank);
	
}
