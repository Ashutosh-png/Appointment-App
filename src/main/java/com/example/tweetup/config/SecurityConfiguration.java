package com.example.tweetup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.tweetup.Services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private  UserService userService;

    public SecurityConfiguration(@Lazy UserService userService) {
        this.userService = userService;
    }
	   
	    
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
		@Primary
		AuthenticationManagerBuilder  authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
			
	    	auth.authenticationProvider(authenticationProvider());
	    
		    return auth;
		}
	    
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	        auth.setUserDetailsService(userService);
	        auth.setPasswordEncoder(passwordEncoder());
	        return auth;
	    }
	    
	    
	    @Bean
		 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	    	
	    	http
            .authorizeHttpRequests()
                .requestMatchers("/resources/**", "/registration", "/about","/forgot-pass","/change-pass","/register").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/doctor/**").hasAuthority("DOCTOR")
                .requestMatchers("/patient/**").hasAuthority("PATIENT")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(roleBasedAuthenticationSuccessHandler())
                .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")

                .permitAll()
                .and()
                .csrf().disable();
	    	
			return http.build();

	    }
	    
	    @Bean
	    public RoleBasedAuthenticationSuccessHandler roleBasedAuthenticationSuccessHandler() {
	        RoleBasedAuthenticationSuccessHandler successHandler = new RoleBasedAuthenticationSuccessHandler();
	        successHandler.setAdminTargetUrl("/admin/dashboard");
	        successHandler.setDoctorTargetUrl("/doctor/dashboard");
             successHandler.setPatientTargetUrl("/patient/dashboard");
	        return successHandler;
	    }
	 // @Autowired
		   // private PasswordEncoder passwordEncoder;

}
