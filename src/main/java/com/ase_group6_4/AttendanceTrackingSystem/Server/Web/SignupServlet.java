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

public class SignupServlet extends HttpServlet{

	@Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	    UserService userService = UserService.getInstance();
	    
	    User old_user = userService.getCurrentUser(req);
	    if ( old_user != null) {
	    		userService.logout(old_user.getEmail(), old_user.getSessionKey());
	    }
	    
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    String firstname = req.getParameter("firstname");
	    String lastname = req.getParameter("lastname");
	    
	    if ( email != null && password != null && firstname != null && lastname != null) {
	    		Student student = new Student(email,password,firstname,lastname);
	    		userService.saveStudent(student);
	    		User user = userService.login(email, password);
	    		req.getSession().setAttribute("userId", user.getEmail());
	    		req.getSession().setAttribute("sessionKey", user.getSessionKey());
	    }
	    
	    resp.sendRedirect("/home.jsp");
	}
}
