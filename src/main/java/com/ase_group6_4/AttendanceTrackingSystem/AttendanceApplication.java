package com.ase_group6_4.AttendanceTrackingSystem;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.ase_group6_4.AttendanceTrackingSystem.Server.AttendanceResource;

public class AttendanceApplication extends Application {
	
	public Restlet createInboundRoot() {
		
		Router router = new Router(getContext());
		router.attachDefault(AttendanceResource.class);
		
		return router;
	}

}