package com.pratap.springdata.transactionmanagement.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.springdata.transactionmanagement.entities.BankAccount;
import com.pratap.springdata.transactionmanagement.repos.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Override
	@Transactional
	public void transferMoney(Double amount) {

		BankAccount pratapAccount = bankAccountRepository.findById(1l).get();
		pratapAccount.setBalence(pratapAccount.getBalence() - amount);
		
		bankAccountRepository.save(pratapAccount);
		
		BankAccount sanjayAccount = bankAccountRepository.findById(2l).get();
		sanjayAccount.setBalence(sanjayAccount.getBalence() + amount);
		
		bankAccountRepository.save(sanjayAccount);
	}

}
