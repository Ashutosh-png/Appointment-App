package com.example.tweetup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tweetup.Dto.UserRegistrationDto;
import com.example.tweetup.Services.UserDetailsServiceImpl;
import com.example.tweetup.Services.UserService;



@Controller
public class UserRegistrationController {
	
	private UserDetailsServiceImpl service;
	
	public UserRegistrationController (UserDetailsServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm( ) {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registeruser(@ModelAttribute("user") UserRegistrationDto userregistrationDto ) {
		service.save(userregistrationDto);
		 return "redirect:/registration";

	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userregistrationDto () {
		return new UserRegistrationDto();
	}
	
	@GetMapping("/")
	public String home() {
		return "redirect:/home";
	}


}
