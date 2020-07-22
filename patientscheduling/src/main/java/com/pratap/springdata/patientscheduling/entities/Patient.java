package com.pratap.springdata.patientscheduling.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String phone;
	
	@Embedded
	private Insurance insurance;
	
	@ManyToMany
	@JoinTable(name = "patients_doctors", 
	joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
	private List<Doctor> doctors;
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public Long getId() {
		return id;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void addAppointment(Appointment appointment) {
		if( appointments == null ) {
			appointments = new ArrayList<>();
		}
		this.appointments.add(appointment);
	}
	
	public void removeAppointment(Appointment appointment) {
		if( !appointments.isEmpty() ) {
			this.appointments.remove(appointment);
		}
	}

	@Override
	public String toString() {
		return String.format("Patient [id=%s, firstName=%s, lastName=%s, phone=%s, insurance=%s]", id, firstName,
				lastName, phone, insurance);
	}

}
