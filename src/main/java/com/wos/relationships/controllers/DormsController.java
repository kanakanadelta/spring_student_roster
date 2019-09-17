package com.wos.relationships.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wos.relationships.models.Dorm;
import com.wos.relationships.services.DormService;

@Controller
@RequestMapping("/dorms")
public class DormsController {
	// DEPENDENCY INJECTIONS
	@Autowired
	private DormService dormService;
	
	public DormsController(DormService dormService) {
		this.dormService = dormService;
	}
	
	// RESTFUL ROUTES //
	
	// INDEX DORMS
	@GetMapping("")
	public String index(Model model) {
		List<Dorm> dorms = dormService.getAll();
		model.addAttribute("dorms", dorms);
		return "dorms.jsp";
	}
	
	// SHOW NEW DORM FORM
	@GetMapping("/new")
	public String showNew(@ModelAttribute("dorm") Dorm dorm) {
		return "newdorm.jsp";
	}
	
	// CREATE DORM
	
	// SHOW DORM
}
