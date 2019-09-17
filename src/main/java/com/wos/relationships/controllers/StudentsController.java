package com.wos.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wos.relationships.models.Student;
import com.wos.relationships.models.StudentInfo;
import com.wos.relationships.services.StudentInfoService;
import com.wos.relationships.services.StudentService;

@Controller
@RequestMapping("/")
public class StudentsController {
	///////////////////////////
	// Dependency Injection //
	public final StudentService studentService;
	public final StudentInfoService infoService;
	
	public StudentsController (
			StudentService studentService,
			StudentInfoService infoService
			) {
		this.studentService = studentService;
		this.infoService = infoService;
	}
	
	//1. INDEX - Show all information onto the page //
	@GetMapping("/students")
	public String index(
			Model model, 
			@ModelAttribute("student")Student student
			) {
		List<Student> students = studentService.allStudents();
		model.addAttribute("students", students);
		List<StudentInfo> info = infoService.allInfo();
		model.addAttribute("infos", info);
		
		return "index.jsp";
	}
	
	//2. NEW - Show pages for Student Creation / Student Info Creation
	
	// New Student Page
	@GetMapping("/students/create")
	public String newStudent(@ModelAttribute("student") Student student) {
		return "newstudent.jsp";
	}
	
	//New Contact Page
	@GetMapping("/contacts/create")
	public String newContact(
			@ModelAttribute("studentInfo") StudentInfo studentInfo,
			@ModelAttribute("students")Student student,
			Model model
			) {
		List<Student> noInfoStudents = studentService.getNoInfo();
		model.addAttribute("noInfoStudents", noInfoStudents);
		return "newcontact.jsp";
	}
	
	//3. CREATE - Create new Student and/or Contact Info
	// CREATE STUDENT
	@PostMapping("/students")
	public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
		if(result.hasErrors()) {
			return "newstudent.jsp";
		} else {
			studentService.createOrUpdateStudent(student);
			return "redirect:/students";
		}
	}
	
	//CREATE CONTACT
	@PostMapping("/contacts")
	public String createContact(@Valid @ModelAttribute("studentInfo") StudentInfo studentInfo, BindingResult result) {
		if(result.hasErrors()) {
			return "newcontact.jsp";
		} else {
			infoService.create(studentInfo);
			return "redirect:/students";
		}
	}
	
	// END CONTROLLER 
}
