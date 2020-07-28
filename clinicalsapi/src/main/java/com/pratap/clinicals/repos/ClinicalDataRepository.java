package com.pratap.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pratap.clinicals.entities.ClinicalData;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
	
}
