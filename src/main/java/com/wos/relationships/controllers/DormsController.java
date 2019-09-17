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
import org.springframework.web.bind.annotation.RequestParam;

import com.wos.relationships.models.Dorm;
import com.wos.relationships.models.Student;
import com.wos.relationships.services.DormService;
import com.wos.relationships.services.StudentService;

@Controller
@RequestMapping("/dorms")
public class DormsController {
	// DEPENDENCY INJECTIONS
	@Autowired
	private DormService dormService;
	@Autowired
	private StudentService studentService;
	
	public DormsController(
			DormService dormService,
			StudentService studentService
			) {
		this.dormService = dormService;
		this.studentService = studentService;
	}
	
	// RESTFUL ROUTES //
	
	// INDEX DORMS
	@GetMapping("")
	public String index(Model model) {
		List<Dorm> dorms = dormService.getAll();
		model.addAttribute("dorms", dorms);
		return "dorms.jsp";
	}
	
	// NEW DORM FORM
	@GetMapping("/new")
	public String showNew(@ModelAttribute("dorm") Dorm dorm) {
		return "newdorm.jsp";
	}
	
	// CREATE DORM
	@PostMapping("")
	public String createStudent(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result) {
		if(result.hasErrors()) {
			return "newdorm.jsp";
		} else {
			dormService.createOrUpdateDorm(dorm);
			return "redirect:/dorms";
		}
	}
	
	// SHOW DORM
	@GetMapping("/{id}")
	public String showDorm(@PathVariable("id") Long id, Model model) {
		Dorm dorm = dormService.getDorm(id);
		model.addAttribute("dorm", dorm);
		List<Student> noDormStudents = studentService.getNoDorm();
		model.addAttribute("students", noDormStudents);
		
		List<Student> dormStudents = dorm.getStudents();
		System.out.println(dormStudents);
		model.addAttribute("dormStudents", dormStudents);
		
		return "showdorm.jsp";
	}
	
	// CREATE RELATIONSHIP
	@PostMapping("/{id}/add")
	public String addStudent(
			@RequestParam(value="student")Long studentId, 
			@PathVariable("id") Long dormId,
			Model model) {
		System.out.println("IN CREATE RELATIONSHIP with: " + studentId);

		Student student = studentService.findStudent(studentId);
		System.out.println(student.getFirstName() + " " + student.getLastName());
		
		Dorm dorm = dormService.getDorm(dormId);
		System.out.println(dorm.getName());
		
		student.setDorm(dorm);
		
		studentService.createOrUpdateStudent(student);
		return "redirect:/dorms/{id}";
	}
	
}
