package com.ase_group6_4.AttendanceTrackingSystem.Server.Web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Group;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.ase_group6_4.AttendanceTrackingSystem.Models.User;
import com.ase_group6_4.AttendanceTrackingSystem.Services.GroupService;
import com.ase_group6_4.AttendanceTrackingSystem.Services.TokenGenerator;
import com.ase_group6_4.AttendanceTrackingSystem.Services.UserService;
import com.googlecode.objectify.Ref;

public class RegistrationServlet extends HttpServlet {
    
	@Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	    UserService userService = UserService.getInstance();
	    User user = userService.getCurrentUser(req);  // Find out who the user is
	    
	    if(user!= null) {	
	    		if (!user.isLecturer()) {
	    			Student student = (Student) user;
    				Integer group_number = Integer.parseInt(req.getParameter("group"));
	    			GroupService groupService = GroupService.getInstance();
	    			Group group = groupService.getGroupByGroupNumber(group_number);
	    			if (group != null) {
	    				student.setGroup(Ref.create(group));
	    				TokenGenerator generator = TokenGenerator.getInstance();
	    				student.setTokens(generator.generateTokensWithStudents(student, 13));
		    			userService.saveStudent(student);	
	    			}
	    			resp.sendRedirect("/home.jsp");
	    		}
	    }
	}
}
