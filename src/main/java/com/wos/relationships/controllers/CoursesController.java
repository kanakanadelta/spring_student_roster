package com.wos.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wos.relationships.models.Course;
import com.wos.relationships.services.CourseService;

@Controller
@RequestMapping("/courses")
public class CoursesController {
	@Autowired
	private CourseService courseService;
	
	public CoursesController(CourseService courseService) {
		this.courseService = courseService;
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
