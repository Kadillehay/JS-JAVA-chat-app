package com.coderscampus.assignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderscampus.assignment14.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/welcome")
	public String getWelcome(Model model) {
//		System.out.println("yo yo ");
//		model.addAttribute("message", "Hello from the controller!");
		return "welcome";
	}
}
