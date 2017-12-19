package com.ase_group6_4.AttendanceTrackingSystem.Services;

import java.util.List;

import com.ase_group6_4.AttendanceTrackingSystem.Models.Group;
import com.googlecode.objectify.ObjectifyService;

public class GroupService {
	private static GroupService instance;

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    private GroupService() {
    }
    
    public Group getGroupByGroupNumber(int group_number) {
    		Group group = ObjectifyService.ofy().load().type(Group.class).id(group_number).now();
    		return group;
    }
    
    public List<Group> getAllGroups(){
	    	List<Group> groups = ObjectifyService.ofy().load().type(Group.class).list();
	    	return groups;
    }
}
