package com.ase_group6_4.AttendanceTrackingSystem.Users;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Student extends Person{
	
	public Student(String user_id, String email, String firstname, String surname, Date birthdate) {
		super(user_id,email,firstname,surname,birthdate);
	}
	
	public Student(String user_id, String email) {
		super(user_id,email);
	}
	
	public Boolean isLecturer() {
		return false;
	}
	
}
