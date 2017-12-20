package com.ase_group6_4.AttendanceTrackingSystem.Server;

import java.io.IOException;

import org.json.JSONObject;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.routing.Router;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Attendance;
import com.ase_group6_4.AttendanceTrackingSystem.Services.AttendanceService;
import com.google.appengine.repackaged.com.google.gson.Gson;

public class AttendanceApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		
		Router router = new Router(getContext()); 
		
		Restlet handleAttendance = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
	            	if (Method.GET.equals(request.getMethod())) {
	            		Gson gson = new Gson();
	            	 	AttendanceService attendanceService = AttendanceService.getInstance();
	                response.setEntity(gson.toJson(attendanceService.getAllAttendances()), MediaType.APPLICATION_JSON);
	                response.setStatus(Status.SUCCESS_OK);
	            	}else if (Method.PUT.equals(request.getMethod())) {
	            		JsonRepresentation jsonRep = null;
	    				try {
	    					jsonRep = new JsonRepresentation(request.getEntity());
	    					Gson gson = new Gson();
	    	            	 	Attendance attendance = gson.fromJson(jsonRep.getJsonObject().toString(), Attendance.class);
	    	            	 	AttendanceService attendanceService = AttendanceService.getInstance();
	    	            	 	attendanceService.saveAttendace(attendance);
	    	                response.setEntity(gson.toJson(attendance), MediaType.APPLICATION_JSON);
	    	                response.setStatus(Status.SUCCESS_OK);
	    				} catch (IOException e) {
	    					response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	    				}
	            	}else {
	            		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	            	}
            }
        };
        
        Restlet getAttendancesByGroupId = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
	            	if (Method.GET.equals(request.getMethod())) {
	            		Long group_id = Long.valueOf((String)request.getAttributes().get("group_id"));		
	    				Gson gson = new Gson();
                	 	AttendanceService attendanceService = AttendanceService.getInstance();
                	 	response.setEntity(gson.toJson(attendanceService.getAllAttendancesByGroupId(group_id)), MediaType.APPLICATION_JSON);
                	 	response.setStatus(Status.SUCCESS_OK);
	            	}else {
	            		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	            	}
            }
        };
        
        Restlet getAttendancesByUserId = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            		if (Method.GET.equals(request.getMethod())) {
	            		Long student_id = Long.valueOf((String)request.getAttributes().get("student_id"));		
					Gson gson = new Gson();
	            	 	AttendanceService attendanceService = AttendanceService.getInstance();
	            	 	response.setEntity(gson.toJson(attendanceService.getAllAttendancesByStudentId(student_id)), MediaType.APPLICATION_JSON);
	            	 	response.setStatus(Status.SUCCESS_OK);
            		}else {
            			response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            		}
            }
        };
        
        
		
     // Defines routes
        router.attachDefault(handleAttendance);
        router.attach("/group/{group_id}",getAttendancesByGroupId);
        router.attach("/student/{student_id}",getAttendancesByUserId);
		
		return router;
	}

}