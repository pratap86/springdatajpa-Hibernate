package com.pratap.clinicals.services;

import java.util.List;

import com.pratap.clinicals.entities.Patient;

public interface PatientService {

	List<Patient> getPatients();
	
	Patient getPatient(long id);
	
	Patient savePatient(Patient patient);

	Patient analyzePatientData(long id);

	Patient updatePatientDetails(long id, Patient patient);
}
