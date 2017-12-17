/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//[START all]
package com.example.AttendanceTrackingSystem;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextListener;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletContextEvent;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
  public void contextInitialized(ServletContextEvent event) {
    // This will be invoked as part of a warmup request, or the first user request if no warmup
    // request.
    ObjectifyService.register(Group.class);
    ObjectifyService.register(Registration.class);
    
    ObjectifyService.begin();
    
    // create test data
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    try {
		Group g1 = new Group(1L, sdf.parse("06.11.2017 09:00"), "01.11.018", "Ana");
		Group g2 = new Group(2L, sdf.parse("06.11.2017 13:45"), "01.11.018", "Sebastian");
		Group g3 = new Group(3L, sdf.parse("06.11.2017 15:15"), "01.11.018", "Sebastian");
		Group g4 = new Group(4L, sdf.parse("07.11.2017 15:00"), "01.11.018", "Ehsan");
		Group g5 = new Group(5L, sdf.parse("15.11.2017 10:00"), "01.11.038", "Mohsen");
		Group g6 = new Group(6L, sdf.parse("08.11.2017 12:00"), "01.11.018", "Saahil");
		
		ObjectifyService.ofy().save().entities(g1,g2,g3,g4,g5,g6).now();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }

  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
//[END all]