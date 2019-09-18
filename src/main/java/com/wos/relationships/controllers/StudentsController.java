package com.wos.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wos.relationships.models.Course;
import com.wos.relationships.models.Enrollment;
import com.wos.relationships.models.Student;
import com.wos.relationships.models.StudentInfo;
import com.wos.relationships.services.CourseService;
import com.wos.relationships.services.EnrollmentService;
import com.wos.relationships.services.StudentInfoService;
import com.wos.relationships.services.StudentService;

@Controller
@RequestMapping("/")
public class StudentsController {
	///////////////////////////
	// Dependency Injection //
	public final StudentService studentService;
	public final StudentInfoService infoService;
	public final CourseService courseService;
	public final EnrollmentService enrollService;
	
	public StudentsController (
			StudentService studentService,
			StudentInfoService infoService,
			CourseService courseService,
			EnrollmentService enrollService
			) {
		this.studentService = studentService;
		this.infoService = infoService;
		this.courseService = courseService;
		this.enrollService = enrollService;
	}
	
	//1. INDEX - Show all information onto the page //
	@GetMapping("/students")
	public String index(
			Model model
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
	
	//4. CREATE CONTACT
	@PostMapping("/contacts")
	public String createContact(@Valid @ModelAttribute("studentInfo") StudentInfo studentInfo, BindingResult result) {
		if(result.hasErrors()) {
			return "newcontact.jsp";
		} else {
			infoService.create(studentInfo);
			return "redirect:/students";
		}
	}
	
	//5. SHOW STUDENT
	@GetMapping("/students/{id}")
	public String showStudent(
			@PathVariable(value="id")Long id, 
			@ModelAttribute("enrollment") Enrollment enrollment, 
			Model model
			) {
		Student student = studentService.findStudent(id);
		model.addAttribute("student", student);
		
		List<Object> courses = enrollService.getStudentNoCourses(id);
		model.addAttribute("courses", courses);

		
		List<Object> enrolledCourses = enrollService.getStudentCourses(id);
		model.addAttribute("enrolledCourses", enrolledCourses);
//		System.out.println(enrolledCourses.toString());
		return "showstudent.jsp";
	}
	
	// ENROLLMENTS AND COURSES:
	
	
	// CREATE relationship - Enroll students to courses:
	@PostMapping("/students/{studentId}/add")
	public String addEnrollment(
			@PathVariable(value="studentId")Long studentId, 
			@RequestParam("course")Long courseId, 
			@ModelAttribute("enrollment")Enrollment enrollment
			) {
		Student student = studentService.findStudent(studentId);
		Course course = courseService.getCourse(courseId);
		
		enrollment.setStudent(student);
		enrollment.setCourse(course);
		enrollService.enrollStudentorUpdateEnrollment(enrollment);
		return "redirect:/students/{studentId}";
	}
	
	// DROP enrollment
	
	@DeleteMapping("/students/{studentId}/drop")
	public String dropEnrollment(
			@PathVariable(value="studentId")Long studentId, 
			@RequestParam("course")Long courseId
			) {
		
		Enrollment enrollment = enrollService.getStudentEnrollment(studentId, courseId);
//		enrollService.dropEnrollment(studentId, courseId);
		enrollService.dropEnrollmentByEntity(enrollment);

		return "redirect:/students/{studentId}";
	}
	
	
	// END CONTROLLER 
}
