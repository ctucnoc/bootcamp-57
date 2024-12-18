package com.bcp.bootcamp.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.bcp.bootcamp.document.Customer;
import com.bcp.bootcamp.document.CustomerNatural;
import com.bcp.bootcamp.dto.BusinessCustomerDTO;
import com.bcp.bootcamp.dto.NaturalCustomerDTO;
import com.bcp.bootcamp.repository.ICustomerNaturalRepository;
import com.bcp.bootcamp.repository.ICustomerRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CustomerNaturalServiceImplTest {

	@Mock
	ICustomerNaturalRepository customerNaturalRepository;

	@Mock
	ICustomerRepository customerRepository;

	@InjectMocks
	CustomerNaturalServiceImpl customerNaturalServiceImpl;

	@Test
	@DisplayName("test save customer natural")
	void testSaveOk() {

		when(customerRepository.save(any(Customer.class))).thenReturn(getCustomerBeanMonoOk());

		when(customerNaturalRepository.save(any(CustomerNatural.class))).thenReturn(getNaturalCustomerBeanMonoOk());

		StepVerifier.create(customerNaturalServiceImpl.save(getNaturalCustomerOk()))
				.expectNext(getNaturalCustomerIdOk())
				.verifyComplete();
	}

	private Mono<NaturalCustomerDTO> getMonoNaturalCustomerOk() {
		return Mono.just(getNaturalCustomerOk());
	}

	private NaturalCustomerDTO getNaturalCustomerIdOk() {
		var natural = getNaturalCustomerOk();
		natural.setId("ghhrr654njjj98");
		return natural;
	}

	private NaturalCustomerDTO getNaturalCustomerOk() {
		NaturalCustomerDTO dto = new NaturalCustomerDTO();
		dto.setName("ce");
		dto.setFullName("abc");
		dto.setDni("98765");
		dto.setPhoneNumber("987654321");
		dto.setGenero("mas");
		return dto;
	}

	private Mono<CustomerNatural> getNaturalCustomerBeanMonoOk() {
		return Mono.just(getNaturalCustomerBeanOk());
	}

	private Mono<Customer> getCustomerBeanMonoOk() {
		return Mono.just(getCustomerOk());
	}

	private CustomerNatural getNaturalCustomerBeanOk() {
		CustomerNatural dto = new CustomerNatural();
		dto.setId("ghhrr654njjj98");
		dto.setApellido("abc");
		dto.setGenero("mas");
		dto.setDni("98765");
		dto.setCustomer(getCustomerOk());
		return dto;
	}

	private Customer getCustomerOk() {
		Customer customer = new Customer();
		customer.setId("abcd");
		customer.setNombre("ce");
		customer.setTelefono("987654321");
		return customer;
	}

}
