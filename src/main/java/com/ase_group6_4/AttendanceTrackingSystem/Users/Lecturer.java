package com.ase_group6_4.AttendanceTrackingSystem.Users;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Lecturer extends Person{
	
	public Lecturer(String userId, String email, String firstname, String lastname) {
		super(userId,email,firstname,lastname);
	}
	
	public Lecturer(String userId, String email) {
		super(userId,email);
	}
	
	public Boolean isLecturer() {
		return true;
	}
	
	@Override
    public String toString() {
		if (this.getFirstname() != null && this.getLastname() != null) {
			return ("Lecturer with\n\tUser_id: "+this.getUserId()+"\n\tEmail: "+this.getEmail()+"\n\tFirstname: "+this.getFirstname()+"\n\tLastname: "+this.getLastname());
		}else {
			return ("Lecturer with\n\tUser_id: "+this.getUserId()+"\n\tEmail: "+this.getEmail());
		}
    }
}
