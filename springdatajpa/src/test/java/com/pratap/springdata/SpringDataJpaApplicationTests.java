package com.pratap.springdata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pratap.springdata.entities.EmployeeEntity;
import com.pratap.springdata.entities.ProductEntity;
import com.pratap.springdata.repos.EmployeeRepository;
import com.pratap.springdata.repos.ProductRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class SpringDataJpaApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private ProductEntity product;
	
	private EmployeeEntity employee;
	
	@BeforeEach
	void setup() {
		product = new ProductEntity();
		product.setName("iPhone");
		product.setDescription("Awesome");
		product.setPrice(2340.80);
		
		employee = new EmployeeEntity();
		employee.setFirstName("Pratap");
		employee.setLastName("Narayan");
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testCreateProduct() {
		ProductEntity savedEntity = productRepository.save(product);
		assertThat(savedEntity.getName(), equalTo(product.getName()));
	}
	
	@Test
	void testReadProduct() {
		ProductEntity productEntity = productRepository.findById(1).orElse(new ProductEntity());
		assertThat(productEntity.getDescription(), equalTo(product.getDescription()));
	}
	
	@Test
	void testProductUpdate() {
		ProductEntity productEntity = productRepository.findById(1).orElse(new ProductEntity());
		productEntity.setDescription("Very awesome product");
		ProductEntity updatedEntity = productRepository.save(productEntity);
		assertThat(updatedEntity.getDescription(), equalTo("Very awesome product"));
	}
	
	@Test
	void testDeleteProduct() {
		if(productRepository.existsById(1)) {
			productRepository.deleteById(1);
			ProductEntity productEntity = productRepository.findById(1).orElse(null);
			assertThat(productEntity, nullValue());
		} else {
			assertEquals(1, " : ID not present..");
		}
		
	}
	
	@Test
	void testCreateEmployee() {
		EmployeeEntity savedEmployee = employeeRepository.save(employee);
		assertThat(savedEmployee.getFirstName(), equalTo(employee.getFirstName()));
	}

}
