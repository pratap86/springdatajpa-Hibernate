package com.pratap.springdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.pratap.springdata.associations.onetomany.entities.Customer;
import com.pratap.springdata.associations.onetomany.entities.PhoneNumber;
import com.pratap.springdata.associations.onetomany.repos.CustomerRepository;
import com.pratap.springdata.associations.onetoone.entities.License;
import com.pratap.springdata.associations.onetoone.entities.Person;
import com.pratap.springdata.associations.onetoone.repos.LicenseRepository;
import com.pratap.springdata.componentmapping.entities.Address;
import com.pratap.springdata.componentmapping.entities.Employee;
import com.pratap.springdata.componentmapping.repos.EmployeeCompRepository;
import com.pratap.springdata.compositeprimarykeys.entities.Doctor;
import com.pratap.springdata.compositeprimarykeys.entities.DoctorId;
import com.pratap.springdata.compositeprimarykeys.repos.DoctorRepository;
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
	
	@Autowired
	private EmployeeCompRepository employeeCompRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private EntityManager entityManager;
	
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
	
	@Test
	void testCreateEmployeeAddress() {
		Employee employee = new Employee();
		employee.setId(123);
		employee.setFirstName("test1");
		employee.setLastName("last1");
		
		Address address = new Address();
		address.setStreetaddress("New Test Road");
		address.setCity("Bangalore");
		address.setState("KA");
		address.setZipcode("560054");
		address.setCountry("IND");
		
		employee.setAddress(address);
		
		Employee savedEmp = employeeCompRepository.save(employee);
		assertThat(savedEmp.getAddress().getCity(), equalTo("Bangalore"));
	}
	
	
	@Test
	void testCreateCustomer() {
		
		Customer customer1 = new Customer();
		customer1.setFirstName("testx");
		customer1.setLastName("lastx");
		
		
		PhoneNumber ph1 = new PhoneNumber();
		ph1.setNumber("9898989898");
		ph1.setType("Corporate");

		customer1.addPhoneNumber(ph1);
		
		PhoneNumber ph2 = new PhoneNumber();
		ph2.setNumber("9898989899");
		ph2.setType("Group User");
		
		customer1.addPhoneNumber(ph2);
		
		Customer savedCustomer = customerRepository.save(customer1);
		assertThat(savedCustomer.getNumbers().stream().map(customer -> customer.getNumber()).findFirst().get(), equalTo("9898989898"));
	}
	
//	@Test
	@Transactional
	void testLoadCustomerById() {
		Optional<Customer> optionalCust = customerRepository.findById(101l);
		if(optionalCust.isPresent()) {
			optionalCust.get().getNumbers();
			assertThat(optionalCust.get().getFirstName(), equalTo("test1"));
		}
		
		assertThat(optionalCust, equalTo("is empty"));
	}
	
	@Test
	void testCreateLicence() {
		License license = new License();
		license.setType("Two wheeler");
		license.setValidFrom(new Date());
		license.setValidTo(new Date());
		
		Person person1 = new Person();
		
		person1.setFirstName("test1");
		person1.setLastName("last1");
		person1.setAge(32);
		
		license.setPerson(person1);
		
		License savedLicense = licenseRepository.save(license);
		
		assertThat(savedLicense.getPerson().getFirstName(), equalTo("test1"));
		
	}
	
	@Test
	void testSaveDoctor() {
		
		Doctor doctor = new Doctor();
		doctor.setName("test");
		
		DoctorId id = new DoctorId();
		id.setId(123);
		id.setEmail("test@test.com");
		
		doctor.setId(id);
		
		Doctor savedDoctor = doctorRepository.save(doctor);
		
		assertThat(savedDoctor.getId().getEmail(), equalTo("test@test.com"));
	}
	
	@Test
	void testProductFirstLevelCache() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Optional<ProductEntity> optionalProduct = productRepository.findById(101);
		
		productRepository.findById(101);
		
		session.evict(optionalProduct);
		
		productRepository.findById(101);
	}
	

}
