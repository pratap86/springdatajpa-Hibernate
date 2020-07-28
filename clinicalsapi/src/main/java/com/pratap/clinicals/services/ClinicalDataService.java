package com.pratap.clinicals.services;

import com.pratap.clinicals.entities.ClinicalData;

public interface ClinicalDataService {

	ClinicalData saveClinicalData(ClinicalData request);
	
	void deleteClinicalData(Long id);
}
