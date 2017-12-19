package com.ase_group6_4.AttendanceTrackingSystem.Server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Group;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.ase_group6_4.AttendanceTrackingSystem.Services.GroupService;
import com.ase_group6_4.AttendanceTrackingSystem.Services.StudentService;
import com.ase_group6_4.AttendanceTrackingSystem.Services.TokenGenerator;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Ref;

public class RegistrationServlet extends HttpServlet {
    
	@Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();  // Find out who the user is
	    
	    if(user!= null) {
	    		StudentService studentService = StudentService.getInstance();
	    		Student student = studentService.getStudentByUser(user);
	    		if (student!=null) {
    				Integer group_number = Integer.parseInt(req.getParameter("group"));
	    			GroupService groupService = GroupService.getInstance();
	    			Group group = groupService.getGroupByGroupNumber(group_number);
	    			if (group != null) {
	    				student.setGroup(Ref.create(group));
	    				TokenGenerator generator = TokenGenerator.getInstance();
	    				student.setTokens(generator.generateTokensWithStudents(student));
		    			studentService.saveStudent(student);	
	    			}
	    			resp.sendRedirect("/home.jsp");
	    		}
	    }
	}
}
