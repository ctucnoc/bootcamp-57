package com.bcp.bootcamp.document;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document(collection = "customer_naturals")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNatural {
	
    @Id
    private String id;
    private Customer customer;
    private String apellido;
    private Date fechaNacimiento;
    private String genero;
    private String dni;

}
