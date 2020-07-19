package com.pratap.springdata.repos;

import org.springframework.data.repository.CrudRepository;

import com.pratap.springdata.entities.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{

}
