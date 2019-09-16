package com.wos.relationships.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wos.relationships.models.StudentInfo;

public interface StudentInfoRepository extends CrudRepository<StudentInfo, Long> {
	// method to retrieve all students' informations
	List<StudentInfo> findAll();
	
	// method to save a student information or update an existing student info
	void save(Optional<StudentInfo> optionalStudentInfo);
	// method to delete a student info by its id
	void deleteById(Long id);
	
	// NATIVE QUERIES:
	// method to retrieve all students' informations
	@Query(value="SELECT * FROM studentInfos", nativeQuery = true)
	List<StudentInfo> findAllByNative();
	
	// method to retrieve information by student_id foreign key
	@Query(value="SELECT * FROM studentInfos WHERE studentId = ?1", nativeQuery=true)
	StudentInfo findByStudentId(Long studentId);
	
}
