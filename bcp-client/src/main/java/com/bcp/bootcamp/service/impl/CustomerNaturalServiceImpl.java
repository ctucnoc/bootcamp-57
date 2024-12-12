package com.bcp.bootcamp.service.impl;

import org.springframework.stereotype.Service;
import com.bcp.bootcamp.document.Customer;
import com.bcp.bootcamp.document.CustomerNatural;
import com.bcp.bootcamp.dto.NaturalCustomerDTO;
import com.bcp.bootcamp.repository.ICustomerNaturalRepository;
import com.bcp.bootcamp.repository.ICustomerRepository;
import com.bcp.bootcamp.service.ICustomerNaturalService;
import reactor.core.publisher.Mono;

@Service
public class CustomerNaturalServiceImpl implements ICustomerNaturalService {
	
	final
	ICustomerRepository customerRepository;
	
	final
	ICustomerNaturalRepository customerNaturalRepository;

	public CustomerNaturalServiceImpl(ICustomerRepository customerRepository,
			ICustomerNaturalRepository customerNaturalRepository) {
		this.customerRepository = customerRepository;
		this.customerNaturalRepository = customerNaturalRepository;
	}

	@Override
	public Mono<NaturalCustomerDTO> save(NaturalCustomerDTO customer) {
		return customerRepository.save(convertToBeanCustomer(customer))
		.flatMap(bean -> {
			CustomerNatural customerNatural = new CustomerNatural();
			customerNatural.setCustomer(bean);
			customerNatural.setApellido(customer.getFullName());
			customerNatural.setGenero(customer.getGenero());
			return customerNaturalRepository.save(customerNatural)
					.map(natural -> convertToBean(natural));
		});
	}
	
	private NaturalCustomerDTO convertToBean(CustomerNatural customerNatural) {
		NaturalCustomerDTO natural = new NaturalCustomerDTO();
		if(customerNatural instanceof CustomerNatural) {
			natural.setId(customerNatural.getId());
			natural.setName(customerNatural.getCustomer().getNombre());
			natural.setFullName(customerNatural.getApellido());
			natural.setGenero(customerNatural.getGenero());
			natural.setPhoneNumber(customerNatural.getCustomer().getTelefono());
			natural.setAddress(customerNatural.getCustomer().getDireccion());
			natural.setDni(customerNatural.getDni());
		}
		return natural;
	}
	
	private Customer convertToBeanCustomer(NaturalCustomerDTO customer) {
		Customer bean = new Customer();
		if(customer instanceof NaturalCustomerDTO) {
			bean.setDireccion(customer.getAddress());
			bean.setEmail(customer.getEmail());
			bean.setNombre(customer.getName());
			bean.setTelefono(customer.getPhoneNumber());
		}
		return bean;
	}

	@Override
	public Mono<NaturalCustomerDTO> findById(String id) {
		return customerNaturalRepository.findById(id).map(bean -> convertToBean(bean)).log();
	}

	@Override
	public Mono<NaturalCustomerDTO> findByDni(String dni) {
		return customerNaturalRepository.findByDni(dni).map(bean -> convertToBean(bean)).log();
	}

}
