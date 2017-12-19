package com.ase_group6_4.AttendanceTrackingSystem.Services;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.ObjectifyService;

public class StudentService {
	private static StudentService instance;

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    private StudentService() {
    }

    public Student getStudentByUser(User user) {
    		Student student = ObjectifyService.ofy().load().type(Student.class).id(user.getUserId()).now();
        return student;
    }
    
    public void saveStudent(Student student) {
    		ObjectifyService.ofy().save().entities(student).now();
    }
}
