package com.wos.relationships.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wos.relationships.models.StudentInfo;

@Repository
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
	
//	@Query(value="SELECT * FROM studentInfos JOIN students ON students.id = studentInfos.studentId", 
//			nativeQuery=true)
//	List<Object[]> findAllJoined();
	
	@Query(value="SELECT i, s FROM StudentInfo i JOIN i.student s")
	List<Object[]> findAllJoined();
	
}
