package com.ase_group6_4.AttendanceTrackingSystem.Models;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Student extends Person{
	
	public Student() {
		super();
	}
	
	public Student(String userId, String email, String firstname, String lastname) {
		super(userId,email,firstname,lastname);
	}
	
	public Student(String userId, String email) {
		super(userId,email);
	}
	
	public Boolean isLecturer() {
		return false;
	}
	
	@Override
    public String toString() {
		if (this.getFirstname() != null && this.getLastname() != null) {
			return ("Student with\n\tUser_id: "+this.getUserId()+"\n\tEmail: "+this.getEmail()+"\n\tFirstname: "+this.getFirstname()+"\n\tlastname: "+this.getLastname());
		}else {
			return ("Student with\n\tUser_id: "+this.getUserId()+"\n\tEmail: "+this.getEmail());
		}
    }
	
}
