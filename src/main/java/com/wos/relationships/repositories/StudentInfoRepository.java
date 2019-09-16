package com.wos.relationships.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.wos.relationships.models.StudentInfo;

public interface StudentInfoRepository extends CrudRepository<StudentInfo, Long> {
	// method to retrieve all students' infos
	List<StudentInfo> findAll();
	// method to save a student information or update an existing student info
	void save(Optional<StudentInfo> optionalStudentInfo);
	// method to delete a student info by its id
	void deleteById(Long id);
}
