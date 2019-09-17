package com.wos.relationships.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
		model.addAttribute("dormStudents", dormStudents);
		
		return "showdorm.jsp";
	}
	
	// CREATE RELATIONSHIP
	@PostMapping("/{id}/add")
	public String addStudent(
			@RequestParam(value="student")Long studentId, 
			@PathVariable("id") Long dormId) {
		// Retrieve student via student Id
		Student student = studentService.findStudent(studentId);
		// Retrieve dorm via path variable ID
		Dorm dorm = dormService.getDorm(dormId);
		// set student dorm via Setter
		student.setDorm(dorm);
		
		// Update the student in DB with service
		studentService.createOrUpdateStudent(student);
		
		// redirect back to the show page with new rendered list
		return "redirect:/dorms/{id}";
	}
	
	// DELETE DORM RELATIONSHIP
	@DeleteMapping("/{id}/remove")
	public String removeStudent(
			@RequestParam(value="student")Long studentId, 
			@PathVariable("id") Long dormId
			) {
		// Retrieve student via student Id
		Student student = studentService.findStudent(studentId);
		// Set dorm value as null
		student.setDorm(null);
		
		studentService.createOrUpdateStudent(student);
		return "redirect:/dorms/{id}"; 
	}
	
}
