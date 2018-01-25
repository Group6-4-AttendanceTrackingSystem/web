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

import com.ase_group6_4.AttendanceTrackingSystem.Models.Attendance;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Lecturer;
import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;
import com.ase_group6_4.AttendanceTrackingSystem.Models.User;
import com.ase_group6_4.AttendanceTrackingSystem.Services.AttendanceService;
import com.ase_group6_4.AttendanceTrackingSystem.Services.UserService;
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
	            	}else {
	            		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	            	}
            }
        };
        
        Restlet putAttendance = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
	            if (Method.PUT.equals(request.getMethod())) {
	            		JsonRepresentation jsonRep = null;
	    				try {
	    					jsonRep = new JsonRepresentation(request.getEntity());
	    					Gson gson = new Gson();
	    					String lecturer_id = (String)request.getAttributes().get("lecturer_id");		
		            		String session_key = (String)request.getAttributes().get("session_key");
		            		UserService userService = UserService.getInstance();
		            		Lecturer lecturer = (Lecturer)userService.getUserById(lecturer_id);
		            		if (lecturer != null && lecturer.getSessionKey().equals(session_key)) {
		            			Attendance attendance = gson.fromJson(jsonRep.getJsonObject().toString(), Attendance.class);
		            			Student student = (Student)userService.getUserById(attendance.student_id);
		            			if (student.getTokens().get(attendance.week_id.intValue()).equals(attendance.attendance_id)) {
		            				AttendanceService attendanceService = AttendanceService.getInstance();
	    	            	 			attendanceService.saveAttendace(attendance);
	    	            	 			response.setEntity(gson.toJson(attendance), MediaType.APPLICATION_JSON);
	    	            	 			response.setStatus(Status.SUCCESS_OK);
		            			}else {
		            				response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		            			}
		            		}else {
		            			response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
		            		}
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
	            		String student_id = (String)request.getAttributes().get("student_id");		
	            		String session_key = (String)request.getAttributes().get("session_key");
	    				Gson gson = new Gson();
	    				User user = UserService.getInstance().getUserById(student_id);
	    				if (user.getSessionKey().equals(session_key)) {
						AttendanceService attendanceService = AttendanceService.getInstance();
                	 		response.setEntity(gson.toJson(attendanceService.getAllAttendancesByGroupId(group_id)), MediaType.APPLICATION_JSON);
                	 		response.setStatus(Status.SUCCESS_OK);
					}else {
						response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
					}
	            	}else {
	            		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	            	}
            }
        };
        
        Restlet getAttendancesByUserId = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            		if (Method.GET.equals(request.getMethod())) {
            			String student_id = (String)request.getAttributes().get("student_id");	
	            		String session_key = (String)request.getAttributes().get("session_key");
					Gson gson = new Gson();
					User user = UserService.getInstance().getUserById(student_id);
					if (user.getSessionKey().equals(session_key)) {
						AttendanceService attendanceService = AttendanceService.getInstance();
	            	 		response.setEntity(gson.toJson(attendanceService.getAllAttendancesByStudentId(student_id)), MediaType.APPLICATION_JSON);
	            	 		response.setStatus(Status.SUCCESS_OK);
					}else {
						response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
					}
            		}else {
            			response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            		}
            }
        };
        
        Restlet getTokens = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            		if (Method.GET.equals(request.getMethod())) {
            			String student_id = (String)request.getAttributes().get("student_id");
	            		String session_key = (String)request.getAttributes().get("session_key");
					Gson gson = new Gson();
					User user = UserService.getInstance().getUserById(student_id);
					if (user.getSessionKey().equals(session_key)) {
						Student student = (Student) user;
	            	 		response.setEntity(gson.toJson(student.getTokens()), MediaType.APPLICATION_JSON);
	            	 		response.setStatus(Status.SUCCESS_OK);
					}else {
						response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
					}
            		}else {
            			response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            		}
            }
        };
        
        Restlet qrCode = new Restlet() {
            @Override
            public void handle(Request request, Response response) {
            		if (Method.GET.equals(request.getMethod())) {
            			String student_id = (String)request.getAttributes().get("student_id");
	            		String session_key = (String)request.getAttributes().get("session_key");
	            		int week_number = Integer.parseInt((String)request.getAttributes().get("week_number"));
					Gson gson = new Gson();
					User user = UserService.getInstance().getUserById(student_id);
					if (user.getSessionKey() != null && user.getSessionKey().equals(session_key)) {
						Student student = (Student) user;
						if ( student.getTokens() != null) {
							response.setEntity(gson.toJson(student.getTokens().get(week_number)), MediaType.APPLICATION_JSON);
	            	 			response.setStatus(Status.SUCCESS_OK);
						}else {
							response.setStatus(Status.CLIENT_ERROR_CONFLICT);
						}
					}else {
						response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
					}
            		}else {
            			response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            		}
            }
        };
        
        
		
     // Defines routes
        router.attach("/lecturer/{lecturer_id}/session/{session_key}",putAttendance);
        router.attach("/student/{student_id}/session/{session_key}",handleAttendance);
        router.attach("/group/{group_id}/{student_id}/session/{session_key}",getAttendancesByGroupId);
        router.attach("/student/{student_id}/session/{session_key}",getAttendancesByUserId);
        router.attach("/student/{student_id}/session/{session_key}/tokens",getTokens);
        router.attach("/qr/student/{student_id}/week/{week_number}/session/{session_key}",qrCode);
		
		return router;
	}

}