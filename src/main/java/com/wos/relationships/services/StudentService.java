package com.wos.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wos.relationships.models.Student;
import com.wos.relationships.repositories.StudentRepository;

@Service
public class StudentService {
	// dependency injection
	private final StudentRepository studentRepo;
	
	public StudentService(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	// return all students
	public List<Student> allStudents(){
//		return studentRepo.findAll();
		return studentRepo.findAllByNative();
	}
	
	// create a new student
	public Student createOrUpdateStudent(Student student) {
		return studentRepo.save(student);
	}
	
	// get a student by id
	public Student findStudent(Long id) {
//		Optional<Student> optionalStudent = studentRepo.findById(id);
		Optional<Student> optionalStudent = studentRepo.findOneByNative(id);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		} else {
			return null;
		}
	}
	
	// update an existing student
	public Student updateStudent(
			Long id,
			String firstName,
			String lastName,
			Integer age
			) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if(optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setAge(age);
			studentRepo.save(student);
			return student;
		} else {
			return null;
		}
	}
	
	// delete a student from the DB by their id
	public Student deleteStudent(Long id) {
		Optional<Student> optionalStudent = studentRepo.findById(id);
		if(optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			studentRepo.deleteById(id);
			return student;
		} else {
			return null;
		}
	}
	
	// EXTRA QUERIES
	public List<Student> getNoInfo() {
		return studentRepo.findByStudentInfoIsNullAndFirstNameNotNull();
	}
	
	public List<Student> getNoDorm() {
		return studentRepo.findByDormIsNullAndFirstNameNotNull();
	}
	
	public List<Student> getStudentsByEnrollment(Long courseId) {
		return studentRepo.findStudentsByEnrollment(courseId);
	}
	
	// END SERVICES 
}
