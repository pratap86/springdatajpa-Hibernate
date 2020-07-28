package com.pratap.clinicals.controllers;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pratap.clinicals.dtos.PatientDataRequest;
import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.entities.Patient;
import com.pratap.clinicals.services.PatientService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	private ModelMapper modelMapper;
	
	@ApiOperation(value = "Retrieves all the patients with their clinical data", 
			notes = "A list of Patients", 
			response = Patient.class,
			responseContainer = "List", 
			produces = "application/json")
	@GetMapping("/patients")
	public List<Patient> getAllPatientDetails(){
		return patientService.getPatients();
	}
	
	
	@ApiOperation(value = "Retrieves the specific patient detail with their clinical data", 
			notes = "A record of Patient", 
			response = Patient.class,
			responseContainer = "Object", 
			produces = "application/json")
	@GetMapping("/patients/{id}")
	public Patient getPatientById( @PathVariable("id") long id ) {
		return patientService.getPatient(id);
	}
	
	@ApiOperation(value = "create a new entry of Patient in to DB", 
			notes = "A new patient", 
			response = Patient.class,
			responseContainer = "Object", 
			produces = "application/json")
	@PostMapping("/patients")
	public ResponseEntity<Patient> savePatient(@RequestBody PatientDataRequest patient) {
		
		modelMapper = new ModelMapper();
		
		Patient patientEntity = modelMapper.map(patient, Patient.class);
		
		Patient savedPatient = patientService.savePatient(patientEntity);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(patientEntity.getId()).toUri();
		return ResponseEntity.created(location).body(savedPatient);
	}
	
	@GetMapping("/patients/analyze/{id}")
	public Patient analyze(@PathVariable("id") long id) {
		
		return patientService.analyzePatientData(id);
	}
	
	@ApiOperation(value = "Update specific patient details, only partialy", 
			notes = "A Patient record", 
			response = Patient.class,
			responseContainer = "Object", 
			produces = "application/json")
	@PatchMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody PatientDataRequest patient){
		modelMapper = new ModelMapper();
		Patient patientEntity = modelMapper.map(patient, Patient.class);
		Patient updatedPatient = patientService.updatePatientDetails(id, patientEntity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
				.buildAndExpand(updatedPatient.getId()).toUri();
		return ResponseEntity.created(location).body(updatedPatient);
	}
	
	/**
	 * get specific patient's clinicaldata
	 * endpoint :http://localhost:8080/clinicalservices/api/patients/{id}/clinicaldatas/
	 */
	
	@ApiOperation(value = "Retrieves all the clinical data associated with specific patient", 
			notes = "A list of Patients", 
			response = ClinicalData.class,
			responseContainer = "Set", 
			produces = "application/json")
	@GetMapping("/patients/{id}/clinicaldatas")
	public ResponseEntity<Set<ClinicalData>> getPatientClinicalDatas(@PathVariable("id") long id){
		
		Set<ClinicalData> patientClinicalDatas = patientService.getPatientClinicalDatas(id);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(patientClinicalDatas);
	}
	
	/**
	 * get specific patient's clinicaldata by clinicaldata id or ids
	 * endpoint :http://localhost:8080/clinicalservices/api/patients/{id}/clinicaldatas?id={id}
	 */
	
	@ApiOperation(value = "Retrieves the specific clinical data associated with specific patient", 
			notes = "A list of Patients", 
			response = ClinicalData.class,
			responseContainer = "Object", 
			produces = "application/json")
	@GetMapping("/patients/{patientId}/clinicaldatas/{clinicalId}")
	public ResponseEntity<ClinicalData> getPatientClinicalDatas(@PathVariable("patientId") long patientId, @PathVariable("clinicalId") long clinicalDataId){
		
		ClinicalData patientClinicalDatas = patientService.getPatientClinicalData(patientId, clinicalDataId);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(patientClinicalDatas);
	}
	
}
