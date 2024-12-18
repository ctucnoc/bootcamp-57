package com.bcp.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.bcp.bootcamp.document.BankAccount;

@Repository
public interface IBankAccountRepository extends ReactiveMongoRepository<BankAccount, String>{

}
