package com.pratap.springdata.compositeprimarykeys.entities;

import java.io.Serializable;

public class DoctorId implements Serializable {

	private static final long serialVersionUID = -822334292665501776L;
	
	private long id;
	
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
