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

  private String speciality;
  private String address;
  private int number;
  private String bio;

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

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public User() {}

  public User(Long id, String username, String password, Role role, String speciality, String address, int number,
      String bio) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.speciality = speciality;
    this.address = address;
    this.number = number;
    this.bio = bio;
  }

  //public User(String username, String password, Role role) {
   // this.username = username;
  //  this.password = password;
  //  this.role = role;
// }

  public User(String username2, String hashedPassword, Role role2, String speciality2, String address2, int number2,
		String bio2) {
	// TODO Auto-generated constructor stub
	  this.username = username2;
	  this.password = hashedPassword;
	  this.role = role2;
	  this.address = address2;
	  this.bio = bio2;
	  this.number = number2;
	  this.speciality = speciality2;
}

public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String username;
    private String password;
    private Role role;
    private String speciality;
    private String address;
    private int number;
    private String bio;

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

    public Builder speciality(String speciality) {
      this.speciality = speciality;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder number(int number) {
      this.number = number;
      return this;
    }

    public Builder bio(String bio) {
      this.bio = bio;
      return this;
    }

    public User build() {
      return new User(null, username, password, role, speciality, address, number, bio);
    }
  }

  public enum Role {
    DOCTOR,
    PATIENT,
    ADMIN
  }
}
