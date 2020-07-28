package com.pratap.clinicals.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratap.clinicals.entities.ClinicalData;
import com.pratap.clinicals.entities.Patient;
import com.pratap.clinicals.exceptions.ClinicalServiceException;
import com.pratap.clinicals.repos.ClinicalDataRepository;
import com.pratap.clinicals.repos.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private ClinicalDataRepository clinicalDataRepository;
	

	@Override
	public List<Patient> getPatients() {
		LOGGER.info("inside getPatients(), and executing patientRepository.findAll()");
		List<Patient> patients = patientRepository.findAll();
		LOGGER.info("Fetched patient detail", patients);
		if (patients.isEmpty()) {
			throw new ClinicalServiceException("No Patient data currently availble");
		}
		return patients;
	}

	@Override
	public Patient getPatient(long id) {
		if(Objects.isNull(id)) {
			throw new ClinicalServiceException("Pateint id is : "+id);
		}
		return patientRepository.findById(id).orElseThrow( () -> new ClinicalServiceException("Patient Id '"+id+"' does not exist") );
	}

	@Override
	public Patient savePatient(Patient patient) {
		
		LOGGER.info("inside savePatient() {}", patient);
		if(patient == null) {
			throw new ClinicalServiceException("Patient request data is not valid : Patient - "+patient);
		}
		LOGGER.info("Going to Perform savePatient() into db {}", patient);
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

	@Override
	public Patient updatePatientDetails(long id, Patient patient) {
		LOGGER.info("inside PATCH updatePatientDetails( ), id : {} ", id);
		LOGGER.info("and patient details {} ", patient);
		Patient fetchedPatient = patientRepository.findById(id).orElseThrow( () -> new ClinicalServiceException("Patient Id '"+id+"' does not exist") );
		if(patient.getFirstName() != null) {
			fetchedPatient.setFirstName(patient.getFirstName());
		}
		if(patient.getLastName() != null) {
			fetchedPatient.setLastName(patient.getLastName());
		}
		if(patient.getAge() != 0) {
			fetchedPatient.setAge(patient.getAge());
		}
		return patientRepository.save(fetchedPatient);
	}

	@Override
	public Set<ClinicalData> getPatientClinicalDatas(long id) {

		Patient fetchedPatient = patientRepository.findById(id).orElseThrow( () -> new ClinicalServiceException("Patient Id '"+id+"' does not exist") );
		
		Set<ClinicalData> clinicaldatas = fetchedPatient.getClinicaldatas();
		if (clinicaldatas.size() == 0) {
			throw new ClinicalServiceException("Now ClinicalData not available, {} "+clinicaldatas.size()); 
		}
		
		return clinicaldatas;
	}

	@Override
	public ClinicalData getPatientClinicalData(long patientId, long clinicalDataId) {
		
		Patient fetchedPatient = patientRepository.findById(patientId).orElseThrow( () -> new ClinicalServiceException("Patient Id '"+patientId+"' does not exist") );
		
		Optional<ClinicalData> filteredClinicalDataOptional = fetchedPatient.getClinicaldatas().stream().filter(clinicalData -> clinicalData.getId().equals(clinicalDataId)).findFirst();
		if (filteredClinicalDataOptional.isEmpty()) {
			throw new ClinicalServiceException("Now ClinicalData not available for clinical Id - "+clinicalDataId); 
		}
		
		return filteredClinicalDataOptional.get();
	}

	@Override
	@Transactional
	public void deletePatientClinicalData(long patientId, long clinicalDataId) {
		
		Patient fetchedPatient = patientRepository.findById(patientId).orElseThrow( () -> new ClinicalServiceException("Patient Id '"+patientId+"' does not exist") );
		
		Optional<ClinicalData> filteredClinicalDataOptional = fetchedPatient.getClinicaldatas().stream().filter(clinicalData -> clinicalData.getId().equals(clinicalDataId)).findFirst();
		if (filteredClinicalDataOptional.isEmpty()) {
			throw new ClinicalServiceException("Now ClinicalData not available for clinical Id - "+clinicalDataId); 
		}
		LOGGER.info("going to delete >>>>>>>>");
		clinicalDataRepository.delete(filteredClinicalDataOptional.get());
	}

}
