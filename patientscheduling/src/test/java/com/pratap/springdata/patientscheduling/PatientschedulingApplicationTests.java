package com.pratap.springdata.patientscheduling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pratap.springdata.patientscheduling.entities.Doctor;
import com.pratap.springdata.patientscheduling.repos.DoctorRepository;

@SpringBootTest
class PatientschedulingApplicationTests {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Test
	void testCreateDoctor() {
		
		Doctor doctor = new Doctor();
		doctor.setFirstName("test1");
		doctor.setLastName("last1");
		doctor.setSpeciality("All");
		
		doctorRepository.save(doctor);
	}
	

}
