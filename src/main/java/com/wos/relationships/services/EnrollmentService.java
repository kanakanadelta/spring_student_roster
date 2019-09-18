package com.wos.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wos.relationships.models.Enrollment;
import com.wos.relationships.repositories.EnrollmentRepository;

@Service
public class EnrollmentService {
	@Autowired
	private EnrollmentRepository enrollRepo;
	
	public EnrollmentService(EnrollmentRepository enrollRepo) {
		this.enrollRepo = enrollRepo;
	}
	
	// SERVICES:
	
	// get all enrollments
	public List<Enrollment> allEnrolls() {
		return enrollRepo.findAll();
	}
	
	//find an enrollment
	public Enrollment findEnrollment(Long id) {
		Optional<Enrollment> optEnroll = enrollRepo.findById(id);
		if(optEnroll.isPresent()) {
			return optEnroll.get();
		} else {
			return null;
		}
	}
	
	//enroll a student to a course
	public Enrollment enrollStudentorUpdateEnrollment(Enrollment enrollment) {
		return enrollRepo.save(enrollment);
	}
	
	//enrollment queries
	
	public List<Object> getStudentCourses(Long studentId) {
		List<Object> courses = enrollRepo.findStudentEnrollments(studentId);
		return courses;
	}
	
	public List<Object> getStudentNoCourses(Long studentId) {
		return enrollRepo.findStudentNoEnrollments(studentId);
	}
	
	// END SERVICES
}
