package com.pratap.clinicals.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.clinicals.dtos.ClinicalDataRequest;
import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.entities.Patient;
import com.pratap.clinicals.repos.ClinicalDataRepository;
import com.pratap.clinicals.repos.PatientRepository;

@Service
public class ClinicalDataServiceImpl implements ClinicalDataService {

	@Autowired
	private ClinicalDataRepository clinicalDataRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public ClinicalData saveClinicalData(ClinicalDataRequest request) {
		
		Patient fetchedPatient = patientRepository.findById(request.getPatientId()).orElse(new Patient());
		
		modelMapper = new ModelMapper();
		
		ClinicalData clinicalData = modelMapper.map(request, ClinicalData.class);
		
		clinicalData.setPatient(fetchedPatient);
		clinicalData.setMeasuredDateTime(LocalDateTime.now());

		return clinicalDataRepository.save(clinicalData);
	}

}
