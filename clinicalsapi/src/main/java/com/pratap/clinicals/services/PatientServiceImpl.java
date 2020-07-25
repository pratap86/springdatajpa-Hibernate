package com.pratap.clinicals.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.entities.Patient;
import com.pratap.clinicals.exceptions.ClinicalServiceException;
import com.pratap.clinicals.repos.ClinicalDataRepository;
import com.pratap.clinicals.repos.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private ClinicalDataRepository clinicalDataRepository;

	@Override
	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatient(long id) {
		return patientRepository.findById(id).orElseThrow( () -> new ClinicalServiceException("Patient Id '"+id+"' does not exist") );
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Patient analyzePatientData(long id) {
		
		Map<String, String> filters = new HashMap<>();
		
		Patient patient = patientRepository.findById(id).orElse(new Patient());
		
		Set<ClinicalData> clinicaldatas = patient.getClinicaldatas();
		
		Set<ClinicalData> dupClinicaldatas = new HashSet<>(clinicaldatas);
		
		for(ClinicalData eachEntry : dupClinicaldatas) {
			
			if(filters.containsKey(eachEntry.getComponentName())) {
				clinicaldatas.remove(eachEntry);
				continue;
			} else {
				filters.put(eachEntry.getComponentName(), null);
			}
			
			if(eachEntry.getComponentName().equalsIgnoreCase("HW")) {
				
				String[] hieghtAndWeight = eachEntry.getComponentValue().split("/");
				
				if(hieghtAndWeight != null && hieghtAndWeight.length > 1) {
					
					float hieghtInMeter = Float.parseFloat(hieghtAndWeight[0]) * 0.4536F;
					float bmi = Float.parseFloat(hieghtAndWeight[1]) / ( hieghtInMeter * hieghtInMeter );
					
					ClinicalData clinicalDataCal = new ClinicalData();
					clinicalDataCal.setComponentName("bmi");
					clinicalDataCal.setComponentValue(Float.toString(bmi));
					clinicalDataCal.setMeasuredDateTime(LocalDateTime.now());
					clinicalDataCal.setPatient(patient);
					clinicalDataRepository.save(clinicalDataCal);
				}
			}
			
		}
		
		return patient;
	}

}
