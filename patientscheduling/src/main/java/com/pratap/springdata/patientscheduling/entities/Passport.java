package com.pratap.springdata.patientscheduling.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "passport")
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String passportNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Patient patient;

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public long getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}
