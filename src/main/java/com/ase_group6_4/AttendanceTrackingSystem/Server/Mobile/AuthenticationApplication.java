package com.ase_group6_4.AttendanceTrackingSystem.Server.Mobile;

import java.io.IOException;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.routing.Router;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Lecturer;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.ase_group6_4.AttendanceTrackingSystem.Models.User;
import com.ase_group6_4.AttendanceTrackingSystem.Services.AttendanceService;
import com.ase_group6_4.AttendanceTrackingSystem.Services.UserService;
import com.google.appengine.repackaged.com.google.gson.Gson;

public class AuthenticationApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		
		Router router = new Router(getContext()); 
		
		Restlet login = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
	            	if (Method.POST.equals(request.getMethod())) {
	            		JsonRepresentation jsonRep = null;
	            		try {
	    					jsonRep = new JsonRepresentation(request.getEntity());
	    					Gson gson = new Gson();
	    					User user = gson.fromJson(jsonRep.getJsonObject().toString(), User.class);
	    	            	 	UserService userService = UserService.getInstance();
	    	            	 	user = userService.getUserByEmail(user.getEmail());
	    	            	 	user = userService.login(user.getEmail(), user.getPassword());
	    	            	 	if (!user.isLecturer()) {
	    	            	 		response.setEntity(gson.toJson((Student)user), MediaType.APPLICATION_JSON);
	    	            	 	}else {
	    	            	 		response.setEntity(gson.toJson((Lecturer)user), MediaType.APPLICATION_JSON);
	    	            	 	}
	    	                response.setStatus(Status.SUCCESS_OK);
	    				} catch (IOException e) {
	    					response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	    				}    					
	            	}else {
	            		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	            	}
            }
        };
        
        Restlet logout = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
	            	if (Method.POST.equals(request.getMethod())) {
	            		JsonRepresentation jsonRep = null;
	            		try {
	    					jsonRep = new JsonRepresentation(request.getEntity());
	    					Gson gson = new Gson();
	    					User user = gson.fromJson(jsonRep.getJsonObject().toString(), User.class);
	    					UserService userService = UserService.getInstance();
	    					User data_user = userService.getUserByEmail(user.getEmail());
	    					if (data_user != null && data_user.getSessionKey()!=null && data_user.getSessionKey() == user.getSessionKey()) {
	    						data_user.setSessionKey(null);
	    						response.setStatus(Status.SUCCESS_OK);
	    					}else {
	    						response.setStatus(Status.SUCCESS_NO_CONTENT);
	    					}
	    				} catch (IOException e) {
	    					response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	    				}
	            	}else {
	            		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	            	}
            }
        };
		
     // Defines routes
        router.attach("/login",login);
        router.attach("/logout",logout);
		return router;
	}
	
}
