package com.ase_group6_4.AttendanceTrackingSystem.Server.Web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ase_group6_4.AttendanceTrackingSystem.Models.User;
import com.ase_group6_4.AttendanceTrackingSystem.Services.UserService;

public class LogoutServlet extends HttpServlet{
	@Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
	    UserService userService = UserService.getInstance();
	    
	    User old_user = userService.getCurrentUser(req);
	    if ( old_user != null) {
	    		userService.logout(old_user.getEmail(), old_user.getSessionKey());
	    		req.getSession().setAttribute("userId", null);
	    		req.getSession().setAttribute("sessionKey", null);
	    }
	    
	    resp.sendRedirect("/home.jsp");
	}
}
