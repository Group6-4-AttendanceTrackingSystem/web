package com.ase_group6_4.AttendanceTrackingSystem.Models;

import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Student extends Person{
	
	private Ref<Group> group;
	private List<String> tokens;
	
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
			return ("Student with\n\tUser_id: "+this.getUserId()+"\n\tEmail: "+this.getEmail()+"\n\tFirstname: "+this.getFirstname()+"\n\tLastname: "+this.getLastname());
		}else {
			return ("Student with\n\tUser_id: "+this.getUserId()+"\n\tEmail: "+this.getEmail());
		}
    }
	
}
