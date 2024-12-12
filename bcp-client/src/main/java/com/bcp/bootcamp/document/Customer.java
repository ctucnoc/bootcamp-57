package com.bcp.bootcamp.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document(collection = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
    @Id
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}
