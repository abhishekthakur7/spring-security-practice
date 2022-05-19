package com.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping("/")
	public String getData() {
		return "Hello World";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "Hello Admin";
	}
	
	@GetMapping("/user")
	public String getLogin() {
		return "Hello User";
	}
	
}
