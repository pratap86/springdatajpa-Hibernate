package com.pratap.springdata.mongodb;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pratap.springdata.mongodb.entities.Product;
import com.pratap.springdata.mongodb.repos.ProductRepository;

@SpringBootTest
class MongodemoApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void testSaveProduct() {
		
		Product product = new Product();
		product.setName("macBook");
		product.setPrice(2000f);
		Product savedProduct = productRepository.save(product);
		
		assertNotNull(savedProduct);
	}

}
