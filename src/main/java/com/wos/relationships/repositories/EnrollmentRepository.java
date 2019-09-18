package com.wos.relationships.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wos.relationships.models.Enrollment;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long>{
	List<Enrollment> findAll();
	
	
	// NATIVE QUERIES //
	// method to find all enrollments:
	@Query(value="SELECT * FROM enrollments", nativeQuery=true)
	List<Enrollment> findAllByNative();
	
	
	// method to find all courses the student has:
	@Query(value="SELECT * FROM courses\n" + 
			"WHERE courses.name IN (\n" + 
			"SELECT courses.name FROM students\n" + 
			"JOIN enrollments ON students.id = enrollments.student_id\n" + 
			"JOIN courses ON enrollments.course_id = courses.id\n" + 
			"WHERE students.id = ?1)", 
			nativeQuery=true)
	List<Object> findStudentEnrollments(Long studentId);
	
	//method to find all courses the student DOES NOT have:
	@Query(value="SELECT * FROM courses\n" + 
			"WHERE courses.name NOT IN (\n" + 
			"SELECT courses.name FROM students\n" + 
			"JOIN enrollments ON students.id = enrollments.student_id\n" + 
			"JOIN courses ON enrollments.course_id = courses.id\n" + 
			"WHERE students.id = ?1)", 
			nativeQuery=true)
	List<Object> findStudentNoEnrollments(Long studentId);
	
	@Query(value="DELETE FROM enrollments\n"
			+ "WHERE student_id = ?1\n"
			+ "AND course_id = ?2", 
			nativeQuery=true)
	void deleteEnrollment(Long studentId, Long courseId);
	
	@Query(value="SELECT * FROM enrollments\n"
			+ "WHERE student_id = ?1\n"
			+ "AND course_id = ?2", 
			nativeQuery=true)
	Enrollment findSpecificEnrollment(Long studentId, Long courseId);
}
