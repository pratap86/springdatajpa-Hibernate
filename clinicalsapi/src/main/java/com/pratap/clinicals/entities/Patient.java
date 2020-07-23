package com.pratap.clinicals.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity(name = "patient")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Patient {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;

	private int age;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
	private Set<ClinicalData> clinicaldatas;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public Set<ClinicalData> getClinicaldatas() {
		return clinicaldatas;
	}

	public void addClinicaldata(ClinicalData clinicaldata) {
		if( clinicaldata != null ) {
			if( clinicaldatas == null ) {
				clinicaldatas = new HashSet<>();
			}
			this.clinicaldatas.add(clinicaldata);
		}
	}
	
	public void removeClinicaldata(ClinicalData clinicaldata) {
		if( clinicaldata != null ) {
			
			this.clinicaldatas.remove(clinicaldata);
		}
	}
	
}
