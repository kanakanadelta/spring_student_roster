package com.wos.relationships.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wos.relationships.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
	// method to find all courses;
	List<Course> findAll();
	
	
}
