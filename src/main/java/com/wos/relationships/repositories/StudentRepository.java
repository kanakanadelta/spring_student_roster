package com.wos.relationships.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wos.relationships.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
	// method to retrieve all students
	List<Student> findAll();
	// method to save a student or update an existing student
	void save(Optional<Student> optionalStudent);
	// method to delete a student by their id
	void deleteById(Long id);
	// method to retrieve students with no studentinfo
	List<Student> findByStudentInfoIsNullAndFirstNameNotNull();
	//method to retrieve students with no dorm
	List<Student> findByDormIsNullAndFirstNameNotNull();
	
	// NATIVE QUERY
	@Query(value="SELECT * FROM students", nativeQuery = true)
	List<Student> findAllByNative();
	
	@Query(value="SELECT * FROM students WHERE id = ?1", nativeQuery = true)
	Optional<Student> findOneByNative(Long id);

}
