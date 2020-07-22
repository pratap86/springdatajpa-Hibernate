package com.pratap.springdata.patientscheduling.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Insurance {

	private String providerName;
	
	private double copay;

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public double getCopay() {
		return copay;
	}

	public void setCopay(double copay) {
		this.copay = copay;
	}

	@Override
	public String toString() {
		return String.format("Insurance [providerName=%s, copay=%s]", providerName, copay);
	}

}
