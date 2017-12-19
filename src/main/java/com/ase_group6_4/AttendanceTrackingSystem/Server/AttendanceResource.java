package com.ase_group6_4.AttendanceTrackingSystem.Server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Attendance;
import com.googlecode.objectify.ObjectifyService;

public class AttendanceResource extends ServerResource {
	
	@Get("json")
	public List<Attendance> getAttendanceLog() {
		
		List<Attendance> attendanceLog = ObjectifyService.ofy()
  	          .load()
  	          .type(Attendance.class)
  	          .list();
		
		return attendanceLog;
	}
	
	@Post("json")
	public Attendance createAttendances(Attendance attendance) {
		ObjectifyService.ofy().save().entity(attendance).now();
		return attendance;
	}
	
}