package com.pratap.clinicals.controllers;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pratap.clinicals.dtos.ClinicalDataRequest;
import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.services.ClinicalDataService;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {
	
	@Autowired
	private ClinicalDataService clinicalDataService;
	
	private ModelMapper modelMapper;
	
	@PostMapping("/clinicals")
	public ResponseEntity<ClinicalData> saveClinicalData( @RequestBody ClinicalDataRequest request ) {
		
		modelMapper = new ModelMapper();
		ClinicalData clinicalData = modelMapper.map(request, ClinicalData.class);
		
		ClinicalData savedClinicalData = clinicalDataService.saveClinicalData(clinicalData);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedClinicalData.getId()).toUri();
		return ResponseEntity.created(location).body(savedClinicalData);
	}
	
	@DeleteMapping("/clinicals/{id}")
	public ResponseEntity<Void> deleteClinicalData(@PathVariable("id") long id){
		
		clinicalDataService.deleteClinicalData(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
