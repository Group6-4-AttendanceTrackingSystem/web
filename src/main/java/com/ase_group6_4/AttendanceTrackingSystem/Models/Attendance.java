package com.ase_group6_4.AttendanceTrackingSystem.Models;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Attendance {
	@Index @Id public String attendance_id;
	public String student_id;
	public Long  tutorial_id;
	public Long  week_id;
	public boolean presented;
	
	public Attendance() {
		super();
	}
	
	public Attendance(String attendance_id, String student_id, Long tutorial_id, Long week_id, boolean presented) {
		super();
		this.attendance_id = attendance_id;
		this.student_id = student_id;
		this.tutorial_id = tutorial_id;
		this.week_id = week_id;
		this.presented = presented;
	}

	public String getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(String attendance_id) {
		this.attendance_id = attendance_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public Long getTutorial_id() {
		return tutorial_id;
	}
	public void setTutorial_id(Long tutorial_id) {
		this.tutorial_id = tutorial_id;
	}
	public Long getWeek_id() {
		return week_id;
	}
	public void setWeek_id(Long week_id) {
		this.week_id = week_id;
	}
	public boolean isPresented() {
		return presented;
	}
	public void setPresented(boolean presented) {
		this.presented = presented;
	}
	
	
}