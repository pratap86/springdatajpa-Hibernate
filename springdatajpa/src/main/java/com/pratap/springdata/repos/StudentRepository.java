package com.pratap.springdata.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pratap.springdata.entities.StudentEntity;
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	@Query("from StudentEntity")
	List<StudentEntity> findAllStudents(Pageable pageable);
	
	@Query("select st.firstName, st.lastName from StudentEntity st")
	List<Object[]> findAllStudentsPartialData();
	
	@Query("from StudentEntity st where st.firstName=:fname")
	List<StudentEntity> findAllStudentsByFirstName(@Param(value = "fname") String firstName);
	
	@Query("from StudentEntity st where st.score>:min and st.score<:max")
	List<StudentEntity> findStudentsForGivenScores(@Param("min") int min, @Param("max") int max);
	
	@Modifying
	@Query("delete from StudentEntity st where st.firstName = :fname")
	void deleteStudentsByFirstName(@Param("fname") String firstName);
	
	@Query(value = "select * from student where fname = :firstName", nativeQuery = true)
	List<StudentEntity> findByStudentFirstNameNQ(@Param("firstName") String firstName);
}
