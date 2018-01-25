package com.ase_group6_4.AttendanceTrackingSystem.Server.Web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase_group6_4.AttendanceTrackingSystem.Models.User;
import com.ase_group6_4.AttendanceTrackingSystem.Services.UserService;

public class LoginServlet extends HttpServlet{
	@Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	    UserService userService = UserService.getInstance();
	    
	    User old_user = userService.getCurrentUser(req);
	    if ( old_user != null) {
	    		userService.logout(old_user.getEmail(), old_user.getSessionKey());
	    }
	    
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    
	    if ( email != null && password != null) {
	    		User user = userService.login(email, password);
	    		req.getSession().setAttribute("userId", user.getEmail());
	    		req.getSession().setAttribute("sessionKey", user.getSessionKey());
	    }
	    
	    resp.sendRedirect("/home.jsp");
	}
}
