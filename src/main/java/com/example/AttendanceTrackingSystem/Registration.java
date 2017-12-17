package com.example.AttendanceTrackingSystem;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Regestration {
	@Load @Parent Key<Group> group;
	
	@Id public Long id;
	public String user_email;
	public String user_id;
	
	public Regestration (Long group_number, String email, String id) {
		if(group_number != null) {
			this.group = Key.create(Group.class, group_number);
		}
	    this.user_email = email;
	    this.user_id = id;
	  }

	public Key<Group> getGroup() {
		return group;
	}

	public void setGroup(Key<Group> group) {
		this.group = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	

}
