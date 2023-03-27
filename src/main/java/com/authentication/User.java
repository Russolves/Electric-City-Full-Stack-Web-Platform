// package net.codejava;
package com.authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;

// import java.util.HashSet;
// import java.util.Set;

// import jakarta.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length= 64)
    private String password;

    // Store user latitude and longitudinal details in database
    @Column(name = "latitude", nullable = true)
    private Double latitude;

    @Column(name = "longitude", nullable=true)
    private Double longitude;


    // public Set<GrantedAuthority> getAuthorities() {
    //     Set<GrantedAuthority> authorities = new HashSet<>();
    //     for (Role role : roles) {
    //         authorities.add(new SimpleGrantedAuthority(role.getName()));
    //     }
    //     return authorities;
    // }

    // Constructors, getters, and setters
    public Double getLatitude(){
      return latitude;
    }
    public void setLatitude(Double latitude){
      this.latitude = latitude;
    }
    public Double getLongitude(){
      return longitude;
    }
    public void setLongitude(Double longitude){
      this.longitude = longitude;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
  this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
