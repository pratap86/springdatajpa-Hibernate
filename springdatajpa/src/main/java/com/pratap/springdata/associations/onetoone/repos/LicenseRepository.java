package com.pratap.springdata.associations.onetoone.repos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.associations.onetoone.entities.License;

@Transactional
@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {

}
