package com.example.tweetup.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	
	 private String adminTargetUrl;
	    private String doctorTargetUrl;
	    private String patientTargetUrl;
	    
	    
	    public void setAdminTargetUrl(String adminTargetUrl) {
	        this.adminTargetUrl = adminTargetUrl;
	    }

	    public void setDoctorTargetUrl(String doctorTargetUrl) {
	        this.doctorTargetUrl = doctorTargetUrl;
	    }

	    public void setPatientTargetUrl(String patientTargetUrl) {
	        this.patientTargetUrl = patientTargetUrl;
	    }

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	            response.sendRedirect(request.getContextPath() + adminTargetUrl);
	        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DOCTOR"))) {
	            response.sendRedirect(request.getContextPath() + doctorTargetUrl);
	        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("PATIENT"))) {
	            response.sendRedirect(request.getContextPath() + patientTargetUrl);
	        } else {
	            throw new IllegalStateException();
	        }
		
	}

}
