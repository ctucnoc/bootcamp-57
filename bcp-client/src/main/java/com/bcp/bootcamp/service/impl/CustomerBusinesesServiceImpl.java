package com.bcp.bootcamp.service.impl;

import org.springframework.stereotype.Service;
import com.bcp.bootcamp.document.Customer;
import com.bcp.bootcamp.document.CustomerBusineses;
import com.bcp.bootcamp.dto.BusinessCustomerDTO;
import com.bcp.bootcamp.repository.ICustomerBusinesesRepository;
import com.bcp.bootcamp.repository.ICustomerRepository;
import com.bcp.bootcamp.service.ICustomerBusinesesService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CustomerBusinesesServiceImpl implements ICustomerBusinesesService{
	
	final
	ICustomerBusinesesRepository customerBusinesesRepository;
	
	final
	ICustomerRepository customerRepository;

	public CustomerBusinesesServiceImpl(ICustomerBusinesesRepository customerBusinesesRepository, ICustomerRepository customerRepository) {
		this.customerBusinesesRepository = customerBusinesesRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Mono<BusinessCustomerDTO> save(BusinessCustomerDTO customer) {
		return customerRepository.save(convertToBeanCustomer(customer))
				.doOnNext(bean -> log.info("save busineses -> {} "+bean.toString()))
		.flatMap(bean -> {
			CustomerBusineses customerbus = new CustomerBusineses();
			customerbus.setCustomer(bean);
			customerbus.setRuc(customer.getRuc());
			customerbus.setSector(customer.getSector());
		
			return customerBusinesesRepository.save(customerbus)
					.map(bus -> convertToBean(bus));
		});
	}

	@Override
	public Mono<BusinessCustomerDTO> findById(String id) {
		return customerBusinesesRepository.findById(id).map(bean -> convertToBean(bean)).log();
	}

	@Override
	public Mono<BusinessCustomerDTO> findByRuc(String ruc) {
		return customerBusinesesRepository.findByRuc(ruc)
				.doOnNext(bean -> log.info("findbyRuc busineses -> {} "+bean.toString()))
				.map(bean -> convertToBean(bean)).log();
	}
	
	private BusinessCustomerDTO convertToBean(CustomerBusineses customerBus) {
		BusinessCustomerDTO bean = new BusinessCustomerDTO();
		if(customerBus instanceof CustomerBusineses) {
			bean.setId(customerBus.getId());
			bean.setBusinessName(customerBus.getCustomer().getNombre());
			bean.setRuc(customerBus.getRuc());
			bean.setSector(customerBus.getSector());
			bean.setEmail(customerBus.getCustomer().getEmail());
		}
		return bean;
	}
	
	private Customer convertToBeanCustomer(BusinessCustomerDTO customer) {
		Customer bean = new Customer();
		if(customer instanceof BusinessCustomerDTO) {
			bean.setDireccion(customer.getAddress());
			bean.setEmail(customer.getEmail());
			bean.setNombre(customer.getBusinessName());
			bean.setTelefono(customer.getPhoneNumber());
		}
		return bean;
	}
	
	

}
