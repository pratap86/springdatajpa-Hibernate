package com.pratap.springdata.patientscheduling.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.patientscheduling.entities.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
