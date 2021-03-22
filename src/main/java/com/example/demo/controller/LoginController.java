package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		System.out.println("/getlogin");
		return "login";
	}

	@PostMapping("/top")
	public String top() {
		System.out.println("/posttop");
		return "top";
	}

	@GetMapping("/top")
	public String root() {
		System.out.println("/gettop");
		return "top";
	}

}
