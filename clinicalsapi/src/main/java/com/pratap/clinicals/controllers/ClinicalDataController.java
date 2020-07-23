package com.pratap.clinicals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratap.clinicals.dtos.ClinicalDataRequest;
import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.services.ClinicalDataService;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {
	
	@Autowired
	private ClinicalDataService clinicalDataService;
	
	@PostMapping("/clinicals")
	public ClinicalData saveClinicalData( @RequestBody ClinicalDataRequest request ) {
		return clinicalDataService.saveClinicalData(request);
	}

}
