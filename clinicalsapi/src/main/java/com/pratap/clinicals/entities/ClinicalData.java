package com.pratap.clinicals.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity(name = "clinicaldata")
public class ClinicalData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "component_name")
	private String componentName;
	
	@Column(name = "component_value")
	private String componentValue;
	
	@Column(name = "measured_date_time")
	private LocalDateTime measuredDateTime;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	public LocalDateTime getMeasuredDateTime() {
		return measuredDateTime;
	}

	public void setMeasuredDateTime(LocalDateTime measuredDateTime) {
		this.measuredDateTime = measuredDateTime;
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

	@Override
	public String toString() {
		return "ClinicalData [id=" + id + ", componentName=" + componentName + ", componentValue=" + componentValue
				+ ", measuredDateTime=" + measuredDateTime + ", patient=" + patient.toString() + "]";
	}

}
