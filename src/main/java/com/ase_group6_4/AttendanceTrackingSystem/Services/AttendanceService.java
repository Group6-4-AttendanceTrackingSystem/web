package com.ase_group6_4.AttendanceTrackingSystem.Services;


public class AttendanceService {
	private static AttendanceService instance;

    public static AttendanceService getInstance() {
        if (instance == null) {
            instance = new AttendanceService();
        }
        return instance;
    }

    private AttendanceService() {
    }
    
}
