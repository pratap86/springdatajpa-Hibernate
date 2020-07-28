package com.pratap.clinicals.dtos;

import io.swagger.annotations.ApiModelProperty;

public class ClinicalDataRequest {

	@ApiModelProperty(
			  value = "component name of the ClinicalDataRequest",
			  name = "componentName",
			  dataType = "String",
			  example = "bmi")
	private String componentName;
	
	@ApiModelProperty(
			  value = "component value of the ClinicalDataRequest",
			  name = "componentValue",
			  dataType = "String",
			  example = "123.5")
	private String componentValue;
	
	@ApiModelProperty(
			  value = "patientId value of the ClinicalDataRequest",
			  name = "patientId",
			  dataType = "Long",
			  example = "8")
	private long patientId;

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

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
}
