package com.pratap.clinicals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Patient savePatient(@RequestBody Patient patient) {
		return patientService.savePatient(patient);
	}
	
	@GetMapping("/patients/analyze/{id}")
	public Patient analyze(@PathVariable("id") long id) {
		
		return patientService.analyzePatientData(id);
	}
}
