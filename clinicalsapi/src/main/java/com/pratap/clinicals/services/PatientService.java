package com.pratap.clinicals.services;

import java.util.List;
import java.util.Set;

import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.entities.Patient;

public interface PatientService {

	List<Patient> getPatients();
	
	Patient getPatient(long id);
	
	Patient savePatient(Patient patient);

	Patient analyzePatientData(long id);

	Patient updatePatientDetails(long id, Patient patient);

	Set<ClinicalData> getPatientClinicalDatas(long id);

	ClinicalData getPatientClinicalData(long patientId, long clinicalDataId);

	void deletePatientClinicalData(long patientId, long clinicalDataId);
}
