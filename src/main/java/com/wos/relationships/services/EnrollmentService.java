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
	
	//drop enrollment by its entity
	public void dropEnrollmentByEntity(Enrollment enrollment) {
		enrollRepo.delete(enrollment);
		return;
	}
	
	//drop a course (by studentId and courseId)
	public void dropEnrollment(Long studentId, Long courseId) {
		enrollRepo.deleteEnrollment(studentId, courseId);
		return;
	}
	
	//enrollment queries
	
	public Enrollment getStudentEnrollment(Long studentId, Long courseId) {
		return enrollRepo.findSpecificEnrollment(studentId, courseId);
	}
	
	public List<Object> getStudentCourses(Long studentId) {
		List<Object> courses = enrollRepo.findStudentEnrollments(studentId);
		return courses;
	}
	
	public List<Object> getStudentNoCourses(Long studentId) {
		return enrollRepo.findStudentNoEnrollments(studentId);
	}
	
	
	// END SERVICES
}
