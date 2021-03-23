package com.example.demo.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping(path = "login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, HttpSession session) {
		model.addAttribute("showErrorMsg", false);
		if (error != null) {
			if (session != null) {
				AuthenticationException ex = (AuthenticationException) session
						.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
				if (ex != null) {
					model.addAttribute("showErrorMsg", true);
					model.addAttribute("errorMsg", ex.getMessage());
				}
			}
		}
		return "login";
	}

	@GetMapping("/top")
	public String top() {
		System.out.println("/posttop");
		return "top.html";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/top")
	public String root() {
		System.out.println("/gettop");
		return "top";
	}

}
