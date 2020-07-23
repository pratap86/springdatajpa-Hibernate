package com.pratap.clinicals.services;

import com.pratap.clinicals.dtos.ClinicalDataRequest;
import com.pratap.clinicals.entities.ClinicalData;

public interface ClinicalDataService {

	ClinicalData saveClinicalData(ClinicalDataRequest request);
}
