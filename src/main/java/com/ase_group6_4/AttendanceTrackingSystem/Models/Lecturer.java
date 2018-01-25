package com.ase_group6_4.AttendanceTrackingSystem.Models;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class Lecturer extends Person{
	
	private Ref<Group> group;
	
	public Lecturer() {
		super();
	}
	
	public Lecturer(String email,String password, String firstname, String lastname) {
		super(email,password,firstname,lastname);
	}
	
	@Override
    public String toString() {
		if (this.getFirstname() != null && this.getLastname() != null) {
			return ("Lecturer with\n\tEmail: "+this.getEmail()+"\n\tFirstname: "+this.getFirstname()+"\n\tLastname: "+this.getLastname());
		}else {
			return ("Lecturer with\n\tEmail: "+this.getEmail());
		}
    }
}
