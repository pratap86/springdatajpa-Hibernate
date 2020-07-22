package com.pratap.springdata.mongodb.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.mongodb.entities.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	
}
