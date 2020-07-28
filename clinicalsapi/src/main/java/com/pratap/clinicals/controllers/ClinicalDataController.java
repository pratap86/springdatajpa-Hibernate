package com.pratap.clinicals.controllers;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {
	
	@Autowired
	private ClinicalDataService clinicalDataService;
	
	private ModelMapper modelMapper;
	
	@ApiResponse(code = 201, message = "created")
	@ApiOperation(value = "create a new entry of Clinical data for specific Patient in to DB", 
			notes = "A new clinical data record for specific Patient", 
			response = ClinicalData.class,
			responseContainer = "Object", 
			produces = "application/json")
	@PostMapping("/clinicals")
	public ResponseEntity<ClinicalData> saveClinicalData( 
			@ApiParam(
					name = "ClinicalDataRequest",
					type = "ClinicalDataRequest",
					value = "componentName, componentValue, patientId",
					required = true
					)
			@RequestBody ClinicalDataRequest request ) {
		
		modelMapper = new ModelMapper();
		ClinicalData clinicalData = modelMapper.map(request, ClinicalData.class);
		
		ClinicalData savedClinicalData = clinicalDataService.saveClinicalData(clinicalData);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedClinicalData.getId()).toUri();
		return ResponseEntity.created(location).body(savedClinicalData);
	}
	
	@CacheEvict("clinicalsapi-cache")
	@ApiOperation(value = "delete a specific clinical data associated with Patient", 
			notes = "delete only clinical record, not their Patient associated with", 
			response = ClinicalData.class,
			responseContainer = "Void")
	@DeleteMapping("/clinicals/{id}")
	public ResponseEntity<Void> deleteClinicalData(
			@ApiParam(
						name = "clinicalDataId",
						type = "Long",
						value = "id",
						required = true
					)
			@PathVariable("id") long id){
		
		clinicalDataService.deleteClinicalData(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
