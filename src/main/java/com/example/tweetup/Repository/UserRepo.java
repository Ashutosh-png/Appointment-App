package com.example.tweetup.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tweetup.User.User;
import com.example.tweetup.User.User.Role;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);

	List<User> findByRole(Role rolename);
	 
	

}
