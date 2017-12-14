<%-- //[START all]--%>
<%@page import="com.googlecode.objectify.annotation.Load"%>
<%@page import="com.example.AttendanceTrackingSystem.Group"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<%-- //[START imports]--%>
<%@ page import="com.example.AttendanceTrackingSystem.*" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%-- //[END imports]--%>

<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<%
    // pageContext.setAttribute("guestbookName", guestbookName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    
    if (user != null) {
%>
<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
    <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>

<%
    	pageContext.setAttribute("user", user);
        // Check if there is a regestration with user
        // if registered -> show group info
        // Else show all groups and allow regestration using forms!
        
        Regestration reg = ObjectifyService.ofy().load().type(Regestration.class).filter("user_id", user.getUserId()).first().now();
        if(reg != null){
        	Group group = ObjectifyService.ofy().load().type(Group.class).filter("number",reg.getGroup().getId()).first().now();
        	pageContext.setAttribute("regestired_group", group);
%>
<p>You are registered to group ${fn:escapeXml(regestired_group.number)} with the following details-> Group date: ${fn:escapeXml(regestired_group.date)}, Group room: ${fn:escapeXml(regestired_group.date)}, Group instructor_name: ${fn:escapeXml(regestired_group.instructor_name)}</p>
<%
        } else {
        	List<Group> groups = ObjectifyService.ofy()
        	          .load()
        	          .type(Group.class) // We want only Greetings
        	          .list();
        	if (groups.isEmpty()){
%>
<p>No groups are available in the system</p>
<%        		
        	} else {
%>
<p>Groups: </p>
<%        	
				for (Group group: groups) {
					pageContext.setAttribute("available_group", group);
%>
<p>Group number: ${fn:escapeXml(available_group.number)}, Group date: ${fn:escapeXml(available_group.date)}, Group room: ${fn:escapeXml(available_group.date)}, Group instructor_name: ${fn:escapeXml(available_group.instructor_name)}</p><%					
				}
        	}
%>

<%        	
        }
    } else {
%>
<p>Hello!
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
    to use the Attendance tracking system</p>
<%
    }
%>
<form action="/sign" method="post">
    <div><textarea name="content" rows="3" cols="60"></textarea></div>
    <div><input type="submit" value="Post Greeting"/></div>
    <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
</form>
<%-- //[END datastore]--%>
<form action="/guestbook.jsp" method="get">
    <div><input type="text" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/></div>
    <div><input type="submit" value="Switch Guestbook"/></div>
</form>

</body>
</html>
<%-- //[END all]--%>
