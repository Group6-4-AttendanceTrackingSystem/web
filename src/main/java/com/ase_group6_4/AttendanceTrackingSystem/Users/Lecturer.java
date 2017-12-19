package com.ase_group6_4.AttendanceTrackingSystem.Users;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Lecturer extends Person{
	
	public Lecturer(String user_id, String email, String firstname, String surname, Date birthdate) {
		super(user_id,email,firstname,surname,birthdate);
	}
	
	public Lecturer(String user_id, String email) {
		super(user_id,email);
	}
	
	public Boolean isLecturer() {
		return true;
	}
}
