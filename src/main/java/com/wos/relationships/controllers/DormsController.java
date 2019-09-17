package com.wos.relationships.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	// INDEX DORMS
	
	// SHOW DORM
	
	// SHOW NEW DORM FORM
	
	// CREATE DORM
}
