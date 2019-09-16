package com.wos.relationships.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wos.relationships.models.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	// method to retrieve all students
	List<Student> findAll();
	// method to save a student or update an existing student
	void save(Optional<Student> optionalStudent);
	// method to delete a student by their id
	void deleteById(Long id);
}
