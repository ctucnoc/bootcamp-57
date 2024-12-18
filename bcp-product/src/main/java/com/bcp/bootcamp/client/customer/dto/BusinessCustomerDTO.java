package com.bcp.bootcamp.client.customer.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessCustomerDTO implements Serializable {

	private static final long serialVersionUID = 5398803233244144921L;

	private String id;

	private String email;

	private String address;

	private String phoneNumber;

	private String businessName;

	private String ruc;

	private String sector;

}
