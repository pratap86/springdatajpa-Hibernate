package com.pratap.springdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.pratap.springdata.entities.EmployeeEntity;
import com.pratap.springdata.entities.ProductEntity;
import com.pratap.springdata.entities.StudentEntity;
import com.pratap.springdata.payment.entities.Check;
import com.pratap.springdata.payment.entities.CreditCard;
import com.pratap.springdata.payment.repos.PaymentRepository;
import com.pratap.springdata.repos.EmployeeRepository;
import com.pratap.springdata.repos.ProductRepository;
import com.pratap.springdata.repos.StudentRepository;

@SpringBootTest
class SpringDataJpaApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
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
		ProductEntity productEntity = productRepository.findById(101).orElse(new ProductEntity());
		assertThat(productEntity.getDescription(), equalTo("Awesome"));
	}
	
	@Test
	void testProductUpdate() {
		ProductEntity productEntity = productRepository.findById(101).orElse(new ProductEntity());
		productEntity.setDescription("Very awesome product");
		ProductEntity updatedEntity = productRepository.save(productEntity);
		assertThat(updatedEntity.getDescription(), equalTo("Very awesome product"));
	}
	
	@Test
	void testDeleteProduct() {
		if(productRepository.existsById(101)) {
			productRepository.deleteById(101);
			ProductEntity productEntity = productRepository.findById(101).orElse(null);
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
	
	@Test
	void testProductFindByName() {
		List<ProductEntity> products = productRepository.findByName("product1");
		assertThat(products, hasSize(2));
	}
	
	@Test
	void testProductFindByNameAndDescription() {
		List<ProductEntity> products = productRepository.findByNameAndDescription("product4", "Awesome product");
		assertThat(products.get(0).getPrice(), equalTo(2600.0));
	}
	
	@Test
	void testProductFindByPriceGreaterThan() {
		List<ProductEntity> products = productRepository.findByPriceGreaterThan(2300.0);
		assertThat(products, hasSize(3));
	}
	
	@Test
	void testProductFindByDescriptionContains() {
		List<ProductEntity> products = productRepository.findByDescriptionContains("product");
		assertThat(products, hasSize(3));
	}

	@Test
	void testProductFindByPriceBetween() {
		List<ProductEntity> products = productRepository.findByPriceBetween(2351.0, 2600.0);
		assertThat(products, hasSize(2));
	}
	@Test
	void testProductFindByDescriptionLike() {
		List<ProductEntity> products = productRepository.findByDescriptionLike("Very%");
		assertThat(products, hasSize(1));
	}
	@Test
	void testProductFindByIdIn() {
		List<ProductEntity> products = productRepository.findByIdIn(Arrays.asList(101, 103, 104));
		assertThat(products, hasSize(3));
	}
	
	@Test
	void testProductFindAllPaging() {
		
		Pageable pageable = PageRequest.of(0, 2);
		Page<ProductEntity> results = productRepository.findAll(pageable);
		assertThat(results.getContent(), hasSize(2));
	}
	
	@Test
	void testProductFindAllSorting() {
		Iterable<ProductEntity> sortedResults = productRepository.findAll(Sort.by(Direction.DESC, "name", "price"));
		sortedResults.forEach(product -> System.out.println(product.getName()));
	}
	
	@Test
	void testFindAllStudents() {
		Pageable pageable = PageRequest.of(0, 2, Direction.DESC, "firstName");
		List<StudentEntity> students = studentRepository.findAllStudents(pageable);
		assertThat(students, hasSize(2));
	}
	
	@Test
	void testFindAllStudentsPartialData() {
		List<Object[]> studentsPartialData = studentRepository.findAllStudentsPartialData();
		for(Object[] objects : studentsPartialData ) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
		}
	}
	
	@Test
	void testFindAllStudentsByFirstName() {
		List<StudentEntity> students = studentRepository.findAllStudentsByFirstName("test1");
		assertThat(students, hasSize(1));
	}
	@Test
	void testFindStudentsForGivenScores() {
		List<StudentEntity> students = studentRepository.findStudentsForGivenScores(201, 204);
		assertThat(students, hasSize(2));
	}
	
	@Test
	@Transactional
//	@Rollback(false)
	void testDeleteStudentsByFirstName() {
		studentRepository.deleteStudentsByFirstName("test1");
		assertThat(studentRepository.findAll(), hasSize(3));
	}

	@Test
	void testFindAllStudentsByFirstNameNQ() {
		List<StudentEntity> students = studentRepository.findByStudentFirstNameNQ("test1");
		assertThat(students, hasSize(1));
	}
	
	@Test
	void testcreatePaymentByCard() {
		CreditCard cc = new CreditCard();
		cc.setId(123);
		cc.setAmount(1200.0);
		cc.setCardNumber("1234567890");
		CreditCard savedCC = paymentRepository.save(cc);
		assertThat(savedCC.getCardNumber(), equalTo("1234567890"));
	}
	
	@Test
	void testcreatePaymentByCheck() {
		Check check = new Check();
		check.setId(124);
		check.setAmount(1340);
		check.setCheckNumber("12345");
		Check savedCheck = paymentRepository.save(check);
		assertThat(savedCheck.getCheckNumber(), equalTo("12345"));
	}

}
