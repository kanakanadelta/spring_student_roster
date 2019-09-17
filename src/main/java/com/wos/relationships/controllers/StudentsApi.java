package com.wos.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wos.relationships.models.Student;
import com.wos.relationships.services.StudentService;

// API - test on postman

@RestController
@RequestMapping("/api")
public class StudentsApi {
	private final StudentService studentService;
	
	public StudentsApi(StudentService studentService) {
		this.studentService = studentService;
	}
	
	// INDEX 
	@GetMapping("/students")
	public List<Student> index() {
		return studentService.allStudents();
	}
	
	// CREATE 
	@PostMapping("/students/create")
	public Student create(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="age") Integer age
			) {
		Student student = new Student(firstName, lastName, age);
		studentService.createOrUpdateStudent(student);
		return student;
	}
	
	// SHOW
	@GetMapping("/students/{id}")
	public Student show(@PathVariable("id") Long id) {
		Student student = studentService.findStudent(id);
		if(student != null) {
			return student;
		} else {
			return null;
		}
	}
	
	// UPDATE
	@PutMapping("/students/{id}")
	public Student update(
			@PathVariable("id") Long id,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="age") Integer age
			) {
		studentService.updateStudent(id, firstName, lastName, age);
		return studentService.findStudent(id);
	}
	
	// DESTROY
	@DeleteMapping("/students/{id}")
	public Student destroy(@PathVariable("id") Long id) {
		Student student = studentService.findStudent(id);
		studentService.deleteStudent(id);
		return student;
	}
	
}
