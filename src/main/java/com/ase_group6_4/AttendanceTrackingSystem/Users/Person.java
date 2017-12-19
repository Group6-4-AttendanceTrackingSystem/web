package com.ase_group6_4.AttendanceTrackingSystem.Users;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

public abstract class Person {
	@Id private String userId;
	private String email;
	
	private String firstname;
	private String lastname;
	
	abstract public Boolean isLecturer();
	
	public Person() {
		
	}

	public Person(String userId, String email, String firstname, String lastname) {
		this.userId = userId;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Person(String userId, String email) {
		this.userId = userId;
		this.email = email;
	}
	
	public void setUserId(String email) {
		this.email = email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPersonalData(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
}
