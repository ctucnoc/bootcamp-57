package com.bcp.bootcamp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ServerWebExchange;
import com.bcp.bootcamp.dto.BusinessCustomerDTO;
import com.bcp.bootcamp.dto.NaturalCustomerDTO;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {
	
	final String ID_CUSTOMER_BUSINESES = "ghhrr654njjj98";

	private WebTestClient client;

	@Mock
	private ApiApiDelegate apiApiDelegate;
	
	@Mock
	ServerWebExchange exchange;
	
	@BeforeEach
	void setUp() {
		client = WebTestClient.bindToController(new ApiApiController(apiApiDelegate)).build();
	}

	@Test
	@DisplayName("test register ok")
	void register_customer_busineses_ok() {

		when(apiApiDelegate.saveBusineses(any(), any())).thenReturn(getMonoRequestBusinessCustomerOk());

		client.post().uri("/api/v1/customer-busineses")
				.body(getMonoBusinessCustomerOk(), BusinessCustomerDTO.class)
				.exchange()
				.expectStatus()
				.isOk();
	}
	
	@Test
	@DisplayName("test findById ok")
	void findById_busineses_ok() {
		
		when(apiApiDelegate.findByDniCustomerBusineses(any(), any())).thenReturn(getMonoRequestBusinessCustomerOk());
		
		client.get().uri("/api/v1/customer-busineses/search/"+ID_CUSTOMER_BUSINESES)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody(BusinessCustomerDTO.class)
		.value(bean ->{
			assertNotNull(bean);
		});
	}
	
	@Test
	@DisplayName("test register customer natural ok")
	void register_customer_natural_ok() {

		when(apiApiDelegate.saveNatural(any(), any())).thenReturn(getMonoRequestNaturalCustomerOk());

		client.post().uri("/api/v1/customer-naturals")
				.body(getMonoNaturalCustomerOk(), BusinessCustomerDTO.class)
				.exchange()
				.expectStatus()
				.isOk();
	}
	
	private Mono<ResponseEntity<BusinessCustomerDTO>> getMonoRequestBusinessCustomerOk() {
	    return Mono.just(ResponseEntity.ok(getBusinessCustomerOk()));
	}

	private Mono<BusinessCustomerDTO> getMonoBusinessCustomerOk() {
		return Mono.just(getBusinessCustomerOk());
	}

	private BusinessCustomerDTO getBusinessCustomerOk() {
		BusinessCustomerDTO dto = new BusinessCustomerDTO();
		dto.setId("ghhrr654njjj98");
		dto.setAddress("Asoc.los andes 123");
		dto.setBusinessName("demo sac");
		dto.setEmail("demo.unsch@gmail.com");
		dto.setPhoneNumber("987654321");
		dto.setRuc("202324252624");
		dto.setSector("educacion");
		return dto;
	}
	
	private Mono<ResponseEntity<NaturalCustomerDTO>> getMonoRequestNaturalCustomerOk() {
	    return Mono.just(ResponseEntity.ok(getNaturalCustomerOk()));
	}

	private Mono<NaturalCustomerDTO> getMonoNaturalCustomerOk() {
		return Mono.just(getNaturalCustomerOk());
	}
	
	private NaturalCustomerDTO getNaturalCustomerOk() {
		NaturalCustomerDTO dto = new NaturalCustomerDTO();
		dto.setId("ghhrr654njjj98");
		dto.setAddress("Asoc.los andes 123");
		dto.setName("ce");
		dto.setEmail("demo.unsch@gmail.com");
		dto.setPhoneNumber("987654321");
		dto.setFullName("yuve");
		return dto;
	}

}
