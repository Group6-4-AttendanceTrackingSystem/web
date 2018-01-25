package com.ase_group6_4.AttendanceTrackingSystem.Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Attendance;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Lecturer;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.ase_group6_4.AttendanceTrackingSystem.Models.User;
import com.googlecode.objectify.ObjectifyService;

public class UserService {

	private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    private UserService() {
    }
    
    public User getCurrentUser(HttpServletRequest request) {
    		User user = null;
    		if (request.getSession(false) != null) {
    			HttpSession session = request.getSession(false);
    			if (session.getAttribute("userId")!=null && session.getAttribute("sessionKey")!=null){
    				String userId = (String)session.getAttribute("userId");
        			String sessionKey = (String) session.getAttribute("sessionKey");
    				User temp_user = this.getUserById(userId);
    				if (temp_user.getSessionKey().equals(sessionKey)) {
    					user = temp_user;
    				}
    			}
    		}
    		return user;
    }
    
    public User getUserById(String id) {
		User user = ObjectifyService.ofy().load().type(User.class).id(id).now();
		return user;
}
    
    public User getUserByEmail(String email) {
    		User user = ObjectifyService.ofy().load().type(User.class).id(email).now();
    		return user;
    }
    
    public void saveStudent(Student student) {
    		ObjectifyService.ofy().save().entity(student).now();
    }
    
    public void saveLecturer(Lecturer lecturer) {
    		ObjectifyService.ofy().save().entity(lecturer).now();
    }
    
    public User login(String email, String password) {
    		User user = this.getUserByEmail(email);
    		if (user != null) {
    			if (user.getPassword().equals(password)) {
    				user.generateKey();
    				if (!user.isLecturer()) {
    					this.saveStudent((Student)user);
    				}else {
    					this.saveLecturer((Lecturer)user);
    				}
    	    		}
    		}
    		return this.getUserByEmail(email);
    }
    
    public void logout(String email, String sessionKey) {
    		User user = this.getUserByEmail(email);
    		if (user.getSessionKey().equals(sessionKey)) {
    			user.setSessionKey(null);
    			if (!user.isLecturer()) {
    				this.saveStudent((Student)user);
    			}else {
    				this.saveLecturer((Lecturer)user);
    			}
    		}
    }
	
}
