package com.bcp.bootcamp.orquestacion.service.impl;

import org.springframework.stereotype.Service;
import com.bcp.bootcamp.client.customer.ICustomerBusinesesClient;
import com.bcp.bootcamp.client.customer.dto.BusinessCustomerDTO;
import com.bcp.bootcamp.document.BankAccount;
import com.bcp.bootcamp.document.Product;
import com.bcp.bootcamp.dto.BankAccountDTO;
import com.bcp.bootcamp.dto.ProductDTO;
import com.bcp.bootcamp.orquestacion.service.IBankAccountOrquestacionService;
import com.bcp.bootcamp.repository.IBankAccountRepository;
import com.bcp.bootcamp.service.IProductService;
import reactor.core.publisher.Mono;

@Service
public class BankAccountOrquestacionserviceImpl implements IBankAccountOrquestacionService{

	final 
	IBankAccountRepository accountRepository;
	
	final
	ICustomerBusinesesClient customerBusinesesClient;
	
	final
	IProductService productService;

	public BankAccountOrquestacionserviceImpl(IBankAccountRepository accountRepository,
			ICustomerBusinesesClient customerBusinesesClient, IProductService productService) {
		this.accountRepository = accountRepository;
		this.customerBusinesesClient = customerBusinesesClient;
		this.productService = productService;
	}

	@Override
	public Mono<BankAccountDTO> save(BankAccountDTO bank) {
		return Mono.zip(findByRuc(bank.getDocumentNumber()),productService.findById(bank.getProduct().getId()))
		.flatMap(y -> {
			var customer = y.getT1();
			var product = y.getT2();
			BankAccount bankBean = convertToBean(bank);
			bankBean.setProduct(convertToBeanProduct(product));
			bankBean.setTitular(customer.getBusinessName());
			 return accountRepository.save(bankBean)
					 .map(this::convertToDto);
		})
		.onErrorMap(RuntimeException.class, ex -> {
			return new RuntimeException(ex.getMessage());
		});
	}
	
	private Product convertToBeanProduct(ProductDTO product) {
		Product bean = new Product();
		if (product instanceof ProductDTO) {
			bean.setId(product.getId());
			bean.setName(product.getName());
			bean.setType(product.getType());
		}
		return bean;
	}
	
	private ProductDTO convertToDtoProduct(Product product) {
		ProductDTO dto = new ProductDTO();
		if (product instanceof Product) {
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setType(product.getType());
		}
		return dto;
	}
	
	private BankAccountDTO convertToDto(BankAccount bank) {
		BankAccountDTO dto = new BankAccountDTO();
		if(bank instanceof BankAccount) {
			dto.setId(bank.getId());
			dto.setBalance(bank.getBalance());
			dto.setComissionManager(bank.isComissionManager());
			dto.setDocumentNumber(bank.getDocumentNumber());
			dto.setLimitMovement(bank.getLimitMovement());
			dto.setName(bank.getName());
			dto.setProduct(convertToDtoProduct(bank.getProduct()));
		}
		return dto;
	}
	
	private BankAccount convertToBean(BankAccountDTO bank) {
		BankAccount bean = new BankAccount();
		if(bank instanceof BankAccountDTO) {
			bean.setId(bank.getId());
			bean.setBalance(bank.getBalance());
			bean.setComissionManager(bank.getComissionManager());
			bean.setDocumentNumber(bank.getDocumentNumber());
			bean.setLimitMovement(bank.getLimitMovement());
			bean.setName(bank.getName());
		}
		return bean;
	}
	
	private Mono<BusinessCustomerDTO> findByRuc(String ruc) {
		return customerBusinesesClient.findByDniCustomerBusineses(ruc);
	}
	
	
}
