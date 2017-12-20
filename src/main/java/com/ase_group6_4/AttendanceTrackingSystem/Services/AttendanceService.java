package com.ase_group6_4.AttendanceTrackingSystem.Services;

import java.util.ArrayList;
import java.util.List;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Attendance;
import com.googlecode.objectify.ObjectifyService;

public class AttendanceService {
	private static AttendanceService instance;

    public static AttendanceService getInstance() {
        if (instance == null) {
            instance = new AttendanceService();
        }
        return instance;
    }

    private AttendanceService() {
    }
    
    public Attendance getAttendanceByAttendanceId(Long attendance_id) {
    		Attendance attendance = ObjectifyService.ofy().load().type(Attendance.class).id(attendance_id).now();
    		return attendance;
    }
    
    public Attendance setAttendanceWith(long attendance_id,long student_id, long tutorial_id, long week_id, boolean presented) {
    		Attendance attendance = new Attendance(attendance_id,student_id,tutorial_id,week_id,presented);
    		this.saveAttendace(attendance);
    		return attendance;
    }
    
    public void saveAttendace(Attendance attendance) {
    		ObjectifyService.ofy().save().entities(attendance).now();
    }
    
    public List<Attendance> getAllAttendances(){
    		return ObjectifyService.ofy().load().type(Attendance.class).list();
    }
    
    public List<Attendance> getAllAttendancesByGroupId(Long groupId){
    		List<Attendance> tmp_attendances = this.getAllAttendances();
    		List<Attendance> attendances = new ArrayList<Attendance>();
    		//Objectify Filter didin't work
    		for (Attendance attendance : tmp_attendances) {
    			if (attendance.getTutorial_id().equals(groupId)) {
    				attendances.add(attendance);
    			}
    		}
    		return attendances;
    }
    
    public List<Attendance> getAllAttendancesByStudentId(Long studentId){
    		List<Attendance> tmp_attendances = this.getAllAttendances();
		List<Attendance> attendances = new ArrayList<Attendance>();
		//Objectify Filter didin't work		
		for (Attendance attendance : tmp_attendances) {
			if (attendance.getStudent_id().equals(studentId)) {
				attendances.add(attendance);
			}
		}
		return attendances;
    }
    
}
