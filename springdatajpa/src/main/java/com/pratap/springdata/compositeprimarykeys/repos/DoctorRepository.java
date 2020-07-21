package com.pratap.springdata.compositeprimarykeys.repos;

import org.springframework.data.repository.CrudRepository;

import com.pratap.springdata.compositeprimarykeys.entities.Doctor;
import com.pratap.springdata.compositeprimarykeys.entities.DoctorId;

public interface DoctorRepository extends CrudRepository<Doctor, DoctorId> {

}
