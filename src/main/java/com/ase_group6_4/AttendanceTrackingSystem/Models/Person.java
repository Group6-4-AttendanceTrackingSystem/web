package com.ase_group6_4.AttendanceTrackingSystem.Models;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public abstract class Person extends User{
	
	private String firstname;
	private String lastname;
	
	public Person() {
		super();
	}

	public Person(String email, String password, String firstname, String lastname) {
		super(email,password);
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public void setPersonalData(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
}
