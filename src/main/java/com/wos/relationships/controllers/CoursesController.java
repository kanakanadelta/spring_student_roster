package com.wos.relationships.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@GetMapping("/new")
	public String newCourse(@ModelAttribute("course")Course course) {
		return "newcourse.jsp";
	}
	
}
