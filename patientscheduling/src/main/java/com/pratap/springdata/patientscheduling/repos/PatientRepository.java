package com.pratap.springdata.patientscheduling.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.patientscheduling.entities.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

}
