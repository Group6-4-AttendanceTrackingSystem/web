package com.ase_group6_4.AttendanceTrackingSystem.Users;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

public abstract class Person {
	@Id private String user_id;
	private String email;
	
	private String firstname;
	private String surname;
	
	private Date birthdate;
	
	abstract public Boolean isLecturer();

	public Person(String user_id, String email, String firstname, String surname, Date birthdate) {
		this.user_id = user_id;
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
		this.birthdate = birthdate;
	}
	
	public Person(String user_id, String email) {
		this.user_id = user_id;
		this.email = email;
	}
	
	public void setPersonalData(String firstname, String surname, Date birthdate) {
		this.firstname = firstname;
		this.surname = surname;
		this.birthdate = birthdate;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}
	
}
