package com.pratap.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratap.clinicals.entities.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {

}
