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
    
    public List<String> generateTokensWithStudents(Student student){
    		List<String> tokens = new ArrayList<String>();
    			for (int i = 0 ; i < 13; i++) {
    				long longToken = Math.abs( random.nextLong() );
                String random = Long.toString( longToken, 16 );
                tokens.add((student.getUserId() + ":" + random));
    			}
    		return tokens;
    }
}
