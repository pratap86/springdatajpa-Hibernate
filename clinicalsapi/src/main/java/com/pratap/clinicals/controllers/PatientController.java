package com.pratap.clinicals.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pratap.clinicals.entities.Patient;
import com.pratap.clinicals.services.PatientService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/patients")
	public List<Patient> getAllPatientDetails(){
		return patientService.getPatients();
	}
	
	@GetMapping("/patients/{id}")
	public Patient getPatientById( @PathVariable("id") long id ) {
		return patientService.getPatient(id);
	}
	
	@PostMapping("/patients")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
		
		Patient savedPatient = patientService.savePatient(patient);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(patient.getId()).toUri();
		return ResponseEntity.created(location).body(savedPatient);
	}
	
	@GetMapping("/patients/analyze/{id}")
	public Patient analyze(@PathVariable("id") long id) {
		
		return patientService.analyzePatientData(id);
	}
	
	@PatchMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient){
		Patient updatedPatient = patientService.updatePatientDetails(id, patient);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/")
				.buildAndExpand(patient.getId()).toUri();
		return ResponseEntity.created(location).body(updatedPatient);
	}

}
