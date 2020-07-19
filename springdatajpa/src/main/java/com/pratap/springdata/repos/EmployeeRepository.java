package com.pratap.springdata.repos;

import org.springframework.data.repository.CrudRepository;

import com.pratap.springdata.entities.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

}
