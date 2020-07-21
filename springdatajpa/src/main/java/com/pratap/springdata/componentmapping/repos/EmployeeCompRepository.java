package com.pratap.springdata.componentmapping.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.componentmapping.entities.Employee;
@Repository
public interface EmployeeCompRepository extends CrudRepository<Employee, Integer>{

}
