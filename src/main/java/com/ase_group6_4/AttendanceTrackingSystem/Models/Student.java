package com.ase_group6_4.AttendanceTrackingSystem.Models;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;

@Subclass(index=true)
public class Student extends Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Ref<Group> group;
	private List<String> tokens;
	
	public Student() {
		super();
	}
	
	public Student(String email,String password, String firstname, String lastname) {
		super(email,password,firstname,lastname);
	}
	
	public Ref<Group> getGroup() {
		return this.group;
	}
	
	public void setGroup(Ref<Group> group) {
		this.group = group;
	}
	
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	
	public List<String> getTokens(){
		return this.tokens;
	}
	
	@Override
    public String toString() {
		if (this.getFirstname() != null && this.getLastname() != null) {
			return ("Student with\n\tEmail: "+this.getEmail()+"\n\tFirstname: "+this.getFirstname()+"\n\tLastname: "+this.getLastname());
		}else {
			return ("Student with\n\tEmail: "+this.getEmail());
		}
    }
	
}
