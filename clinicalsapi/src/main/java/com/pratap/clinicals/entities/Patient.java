package com.pratap.clinicals.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name = "patient")
public class Patient extends RepresentationModel<Patient> implements Serializable {

	private static final long serialVersionUID = 7248845216031486492L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;

	private int age;
	
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.PERSIST}, fetch =  FetchType.EAGER, mappedBy = "patient", orphanRemoval = false)
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
		clinicaldata.setPatient(this);
		this.getClinicaldatas().add(clinicaldata);
		
	}

	public void removeClinicaldata(ClinicalData clinicaldata) {
		if( clinicaldata != null ) {
			
			this.clinicaldatas.remove(clinicaldata);
		}
	}

	@Override
	public String toString() {
		return String.format("Patient [lastName=%s, firstName=%s, age=%s, clinicaldatas=%s]", lastName, firstName, age,
				this.getClinicaldatas().size());
	}

	

}
