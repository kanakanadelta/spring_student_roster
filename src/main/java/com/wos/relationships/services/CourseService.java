package com.wos.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wos.relationships.models.Course;
import com.wos.relationships.repositories.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepo;
	
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
	
	// GET all courses
	public List<Course> allCourses() {
		return courseRepo.findAll();
	}
	
	// GET a course (by id)
	public Course getCourse(Long id) {
		Optional<Course> optCourse = courseRepo.findById(id);
		if(optCourse.isPresent()) {
			return optCourse.get();
		} else {
			return null;
		}
	}
	
}
