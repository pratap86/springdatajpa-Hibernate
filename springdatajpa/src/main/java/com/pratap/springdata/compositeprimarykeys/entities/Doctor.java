package com.pratap.springdata.compositeprimarykeys.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "doctor")
public class Doctor {

	@EmbeddedId
	private DoctorId id;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DoctorId getId() {
		return id;
	}

	public void setId(DoctorId id) {
		this.id = id;
	}
	
}
