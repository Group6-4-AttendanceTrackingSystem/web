package com.ase_group6_4.AttendanceTrackingSystem.Models;
import java.util.Date;

import com.ase_group6_4.AttendanceTrackingSystem.Services.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Group {
	@Index @Id public Long number;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm")
	public Date date;
	
	public String room;
	public String instructor_email;
	
	public Group() {
		date = new Date();
	}
	
	public Group(Long number, Date date, String room, String instructor_id) {
		super();
		this.number = number;
		this.date = date;
		this.room = room;
		this.instructor_email = instructor_id;
	}
	
	@Override
    public String toString() {
		Lecturer lecturer = (Lecturer)UserService.getInstance().getUserByEmail(this.instructor_email);
		return ("Group Number "+this.number+"\n\tDate: "+this.date+"\n\tRoom: "+this.room+"\n\tInstructor: "+lecturer.getFirstname());
    }


	public Long getNumber() {
		return number;
	}


	public void setNumber(Long number) {
		this.number = number;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public String getInstructor_email() {
		return instructor_email;
	}


	public void setInstructor_email(String instructor_email) {
		this.instructor_email = instructor_email;
	}
}
