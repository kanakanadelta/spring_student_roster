package com.wos.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wos.relationships.models.Course;
import com.wos.relationships.models.Student;
import com.wos.relationships.services.CourseService;
import com.wos.relationships.services.StudentService;

@Controller
@RequestMapping("/courses")
public class CoursesController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	
	public CoursesController(
			CourseService courseService,
			StudentService studentService
			) {
		this.courseService = courseService;
		this.studentService = studentService;
	}
	
	// ROUTES //
	
	// INDEX - show all courses
	@GetMapping("")
	public String index(Model model) {
		List<Course> courses = courseService.allCourses();
		model.addAttribute("courses", courses);
		return "courses.jsp";
	}
	
	// NEW - page for creating new course
	@GetMapping("/new")
	public String newCourse(@ModelAttribute("course")Course course) {
		return "newcourse.jsp";
	}
	
	// SHOW - page for specific course
	@GetMapping("/{courseId}")
	public String show(@PathVariable("courseId")Long courseId, Model model) {
		// retrieve the specific course for the course show page
		Course course = courseService.getCourse(courseId);
		//add to model
		model.addAttribute("course", course);

//		//retrieve list of students who are enrolled to the specific course
		List<Student> enrolledStudents = studentService.getStudentsByEnrollment(courseId);
//		//add to model
		model.addAttribute("students", enrolledStudents);
		
		return "showcourse.jsp";
	}
	
	// CREATE - create new course
	@PostMapping("")
	public String create(@Valid @ModelAttribute("course")Course course, BindingResult result) {
		if(result.hasErrors()) {
			return "newcourse.jsp";
		} else {
			courseService.createOrUpdateCourse(course);
			return "redirect:/courses";
		}
	}
	
}
