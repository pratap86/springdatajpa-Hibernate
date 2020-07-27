package com.pratap.clinicals.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.clinicals.dtos.ClinicalDataRequest;
import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.entities.Patient;
import com.pratap.clinicals.repos.ClinicalDataRepository;

@Service
public class ClinicalDataServiceImpl implements ClinicalDataService {

	@Autowired
	private ClinicalDataRepository clinicalDataRepository;

	@Autowired
	private PatientService patientService;
	
	private ClinicalData clinicalData;
	
	@Override
	public ClinicalData saveClinicalData(ClinicalDataRequest request) {
		
		Patient patient = patientService.getPatient(request.getPatientId());
		
		clinicalData = new ClinicalData();
		clinicalData.setComponentName(request.getComponentName());
		clinicalData.setComponentValue(request.getComponentValue());
		clinicalData.setMeasuredDateTime(LocalDateTime.now());
		clinicalData.setPatient(patient);

		return clinicalDataRepository.save(clinicalData);
	}

}
