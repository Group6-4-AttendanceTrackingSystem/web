package com.example.AttendanceTrackingSystem;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AttendanceApplication extends Application {
	
	public Restlet createInboundRoot() {
		
		Router router = new Router(getContext());
		router.attachDefault(AttendanceResource.class);
		
		return router;
	}

}