package com.ase_group6_4.AttendanceTrackingSystem.Services;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Lecturer;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.ObjectifyService;

public class LecturerService {
	private static LecturerService instance;

    public static LecturerService getInstance() {
        if (instance == null) {
            instance = new LecturerService();
        }
        return instance;
    }

    private LecturerService() {
    }

    public Lecturer getLecturerByUser(User user) {
    		Lecturer lecturer = ObjectifyService.ofy().load().type(Lecturer.class).id(user.getUserId()).now();
        return lecturer;
    }
    
    public void saveLecturer(Lecturer lecturer) {
		ObjectifyService.ofy().save().entities(lecturer).now();
    }
}
