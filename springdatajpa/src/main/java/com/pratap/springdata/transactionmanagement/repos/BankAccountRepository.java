package com.pratap.springdata.transactionmanagement.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.transactionmanagement.entities.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

}
