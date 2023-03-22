package com.example.tweetup.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
@Builder
@Entity
public class User {
	
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private String username;

	  private String password;

	  @Enumerated(EnumType.STRING)
	  private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(Long id, String username, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	  
	public User() {}

	
	public User(String username2, String password2, Role role2) {
		// TODO Auto-generated constructor stub
		this.username = username2;
	    this.password = password2;
	    this.role = role2;
	}

	public static Builder builder() {
	    return new Builder();
	  }

	  public static class Builder {
	    private String username;
	    private String password;
	    private Role role;

	    public Builder username(String username) {
	      this.username = username;
	      return this;
	    }

	    public Builder password(String password) {
	      this.password = password;
	      return this;
	    }

	    public Builder role(Role role) {
	      this.role = role;
	      return this;
	    }

	    public User build() {
	      return new User(username, password, role);
	    }

}


 public enum Role {
	  DOCTOR,
	  PATIENT,
	  ADMIN
	}}