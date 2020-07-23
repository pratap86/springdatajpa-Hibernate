package com.pratap.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pratap.clinicals.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
