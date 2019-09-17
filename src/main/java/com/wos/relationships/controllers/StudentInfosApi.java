package com.wos.relationships.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wos.relationships.models.StudentInfo;
import com.wos.relationships.services.StudentInfoService;
import com.wos.relationships.services.StudentService;

@RestController
@RequestMapping("/api/contacts")
public class StudentInfosApi {
	// dependency injection
	private final StudentInfoService infoService;
	private final StudentService studentService;
	
	public StudentInfosApi(StudentInfoService infoService, StudentService studentService) {
		this.infoService = infoService;
		this.studentService = studentService;
	}
	
	// INDEX - retrieve all students' information
	@GetMapping("")
	public List<StudentInfo> index() {
		return infoService.allInfo();
	}
	
	@GetMapping("/join")
	public List<Object[]> joinedTable(){
		
		return infoService.joinInfo();
	}
	
	// CREATE - give a student their contact info via foreign key assignment
	@PostMapping("/create")
	public StudentInfo create(
			@RequestParam(value="student") Long studentId,
			@RequestParam(value="address") String address,
			@RequestParam(value="city") String city,
			@RequestParam(value="state") String state
			) {
		StudentInfo info = new StudentInfo();
		info.setAddress(address);
		info.setCity(city);
		info.setState(state);
		info.setStudent(studentService.findStudent(studentId));
		infoService.create(info);
		return info;
	}
}
