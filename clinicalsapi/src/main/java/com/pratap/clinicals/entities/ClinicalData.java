package com.pratap.clinicals.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "clinicaldata")
public class ClinicalData extends RepresentationModel<ClinicalData> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "component_name")
	private String componentName;
	
	@Column(name = "component_value")
	private String componentValue;
	
	@Column(name = "measured_date_time")
	private LocalDateTime measuredDateTime;
	
	@JsonIgnore
	@Transient
	private Long patientId;
	
	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.PERSIST})
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

	public Long getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "ClinicalData [id=" + id + ", componentName=" + componentName + ", componentValue=" + componentValue
				+ ", measuredDateTime=" + measuredDateTime + ", patient=" + this.getPatient() + "]";
	}

}
