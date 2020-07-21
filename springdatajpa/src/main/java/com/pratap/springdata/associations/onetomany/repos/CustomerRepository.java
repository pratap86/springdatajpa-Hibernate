package com.pratap.springdata.associations.onetomany.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.associations.onetomany.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
