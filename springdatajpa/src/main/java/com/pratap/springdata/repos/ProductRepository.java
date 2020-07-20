package com.pratap.springdata.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pratap.springdata.entities.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer>{
	
	List<ProductEntity> findByName(String name);
	
	List<ProductEntity> findByNameAndDescription(String name, String description);
	
	List<ProductEntity> findByPriceGreaterThan(Double price);
	
	List<ProductEntity> findByDescriptionContains(String description);
	
	List<ProductEntity> findByPriceBetween(Double price1, Double price2);
	
	List<ProductEntity> findByDescriptionLike(String partialDescription);
	
	List<ProductEntity> findByIdIn(List<Integer> ids);

}
