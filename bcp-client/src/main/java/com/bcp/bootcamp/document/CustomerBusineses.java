package com.bcp.bootcamp.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document(collection = "customer_busineses")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBusineses {
	
    @Id
    private String id;
    private Customer customer;
    private String ruc;
    private String sector;

}
