package com.bcp.bootcamp.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bank_account")
public class BankAccount {
    @Id
    private String id;
    private String name;
    private double balance;
    private boolean comissionManager;
    private double limitMovement;
    private String documentNumber;
    private String titular;
    private Product product;
}
