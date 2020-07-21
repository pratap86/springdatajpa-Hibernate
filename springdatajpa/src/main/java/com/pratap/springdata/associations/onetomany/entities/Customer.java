package com.pratap.springdata.associations.onetomany.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "fname")
	private String firstName;
	
	@Column(name = "lname")
	private String lastName;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<PhoneNumber> numbers;

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<PhoneNumber> getNumbers() {
		return numbers;
	}

	public void setNumbers(Set<PhoneNumber> numbers) {
		this.numbers = numbers;
	}
	
	// A better way to handle foreign key
	public void addPhoneNumber(PhoneNumber number) {
		if( number != null ) {
			if( numbers == null ) {
				numbers = new HashSet<>();
			}
			number.setCustomer(this);// foreign key
			numbers.add(number);
		}
	}
	
}
