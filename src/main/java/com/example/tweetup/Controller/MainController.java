package com.example.tweetup.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tweetup.Services.UserDetailsServiceImpl;
import com.example.tweetup.User.User;

@Controller
public class MainController {
	@Autowired
	UserDetailsServiceImpl s;
	
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/admin/")
    public String home() {
		  
	            return "admin";
			
	        
	}
	
	@GetMapping("/doctor/")
    public String doctorpage() {
		  
	            return "doctor";
			
	        
	}
	
	@GetMapping("/admin/accounts")
	public String showAccounts(Model model) {
		  List<User> users = s.findAllUsers();
		    model.addAttribute("users", users);
		return "Accounts";
	}
	
	
	@PostMapping("/admin/delete-user/{id}")
	public String delete(@PathVariable Long id) {
		s.deleteUser(id);
		return "redirect:/admin/accounts";
		
		
	}
	
	 @GetMapping("/admin/dashboard")
	    public String adminDashboard() {
	        // TODO: Implement logic to display admin dashboard
	        return "admin";
	    }
	    
	    @GetMapping("/doctor/dashboard")
	    public String doctorDashboard() {
	        // TODO: Implement logic to display doctor dashboard
	        return "doctorDashboard";
	    }
	    
	    @GetMapping("/patient/dashboard")
	    public String patientDashboard() {
	        // TODO: Implement logic to display patient dashboard
	        return "patient-mainpage";
	    }
	    
	    
	  
	

	}
