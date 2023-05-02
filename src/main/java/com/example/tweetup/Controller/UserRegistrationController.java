package com.example.tweetup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tweetup.Dto.UserRegistrationDto;
import com.example.tweetup.Services.UserDetailsServiceImpl;
import com.example.tweetup.Services.UserService;
import com.example.tweetup.User.User;



@Controller
public class UserRegistrationController {
	
	private UserDetailsServiceImpl service;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserRegistrationController (UserDetailsServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/forgot-pass")
	public String forgot() {
		return "password";
	}
	
	@PostMapping("/change-pass")
	public String change(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirm-password") String conpass,RedirectAttributes redirectAttributes) {
	if(password.equals(conpass)) {
		String encodedPassword = passwordEncoder.encode(password);
		User user = service.getByUsername(username);
		user.setPassword(encodedPassword);
		user.getRole();
		service.save(user);
		return "redirect:/login";
		
	}
	
    redirectAttributes.addFlashAttribute("error", "password did not matched");

	return "redirect:/forgot-pass";
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm( ) {
		return "registration";
	}
	
	@PostMapping("/register")
	public String registeruser(@ModelAttribute("user") UserRegistrationDto userregistrationDto,RedirectAttributes redirectAttributes) {
		String name = userregistrationDto.getUsername();
		try {
		    User user = service.getByUsername(name);
		    if(user!=null) {
		        redirectAttributes.addFlashAttribute("message", "Username already exists");

		        return "redirect:/registration";
		    }
		    service.save(userregistrationDto);
	        redirectAttributes.addFlashAttribute("register", "Registered Succesfully");

		    return "redirect:/registration";
		} catch (Exception e) {
	        redirectAttributes.addFlashAttribute("message", "Username already exists");

		    return "redirect:/registration";}
	

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
