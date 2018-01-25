package com.ase_group6_4.AttendanceTrackingSystem.Models;

import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {
	@Id @Index private String email;
	private String password;
	
	@Index private String sessionKey;
	
	public User() {
		
	}
	
	public boolean isLecturer() {
		return this instanceof Lecturer;
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public void setSessionKey(String key) {
		this.sessionKey = key;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSessionKey() {
		return this.sessionKey;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String generateKey() {
		this.sessionKey = UUID.randomUUID().toString().replace("-", ""); 
		return this.sessionKey;
	}
}
