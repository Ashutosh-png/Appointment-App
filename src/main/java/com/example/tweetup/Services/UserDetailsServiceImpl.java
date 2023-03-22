package com.example.tweetup.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tweetup.Dto.UserRegistrationDto;
import com.example.tweetup.Repository.UserRepo;
import com.example.tweetup.User.User;
import com.example.tweetup.User.User.Role;


@Service
public class UserDetailsServiceImpl implements UserService {
	
	private UserRepo repo;
	@Autowired
	public UserDetailsServiceImpl(UserRepo repo) {
		super();
		this.repo = repo;
	}
	
	public List<User> findUserByRole(String rolename){
		Role role = Role.valueOf( rolename.toUpperCase());
				return repo.findByRole(role);
	}
	
	@Override
	public List<User> findAllUsers() {
	    return repo.findAll();
	}
	
	
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}
	
	  @Autowired
	    private JavaMailSender javaMailSender;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
		  String hashedPassword = passwordEncoder.encode(registrationDto.getPassword());
		User user = new User(registrationDto.getUsername(),hashedPassword, registrationDto.getRole());
		return repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repo.findByUsername(username);
		       
		
		 List<GrantedAuthority> authorities = new ArrayList<>();
		    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

		UserDetails userdetails = new org.springframework.security.core.userdetails.User(
		        user.getUsername(),
		        user.getPassword(),
		        authorities
		    );
		
		return userdetails;

				
	}
	
	public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

	public User getByUsername(String name) {
		// TODO Auto-generated method stub
		return repo.findByUsername(name);
	}

}
