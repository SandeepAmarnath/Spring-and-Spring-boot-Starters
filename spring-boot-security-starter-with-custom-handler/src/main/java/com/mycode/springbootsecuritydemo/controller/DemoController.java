package com.mycode.springbootsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	// add request mapping for /leaders

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
	// Landing page for all the users
	
	@GetMapping("/welcome")
	public String welcome() {
		
		return "welcome";
	}
	
	
	@GetMapping("/showDbContent")
	public String dbContent(Model theModel) {
		
		
		
		return "data";
	}
	
}










