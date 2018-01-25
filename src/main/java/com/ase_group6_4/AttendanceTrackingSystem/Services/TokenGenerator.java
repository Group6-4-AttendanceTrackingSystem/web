package com.ase_group6_4.AttendanceTrackingSystem.Services;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Student;

public class TokenGenerator {
	private static TokenGenerator instance;
	
	protected static SecureRandom random = new SecureRandom();

    public static TokenGenerator getInstance() {
        if (instance == null) {
            instance = new TokenGenerator();
        }
        return instance;
    }

    private TokenGenerator() {
    }
    
	public List<String> generateTokensWithStudents(Student student, int numberofweeks){
    		List<String> tokens = new ArrayList<String>();
    			for (int i = 0 ; i < numberofweeks; i++) {
    				long longToken = Math.abs( random.nextLong() );
                String random1 = Long.toString( longToken, 8 );
                String random2 = Long.toString( longToken, 8 );
                tokens.add((student.getEmail()  + ":" + random1 + ":"+ student.getGroup().get().number+ ":" + random2 + ":" +i));
    			}
    		return tokens;
    }
}
