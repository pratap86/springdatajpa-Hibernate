package com.pratap.springdata.payment.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.payment.entities.Payment;
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>{

}
