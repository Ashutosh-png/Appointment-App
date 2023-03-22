package com.example.tweetup.Services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.tweetup.Dto.UserRegistrationDto;
import com.example.tweetup.User.User;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

	List<User> findAllUsers();

}
