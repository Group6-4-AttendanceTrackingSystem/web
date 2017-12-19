<%-- //[START all]--%>
<%@page import="com.googlecode.objectify.annotation.Load"%>
<%@ page import="com.ase_group6_4.AttendanceTrackingSystem.*" %>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Models.*"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Server.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>

<%-- //[START imports]--%>
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
	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    Student student;
    Lecturer lecturer;
    if (user != null)
    {
        student = ObjectifyService.ofy().load().type(Student.class).id(user.getUserId()).now();
        lecturer = ObjectifyService.ofy().load().type(Lecturer.class).id(user.getUserId()).now();
        if (student!=null)
        {
            pageContext.setAttribute("student", student);
%>
            <p>Hello, ${fn:escapeXml(student.firstname)}! (You can <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%
            Registration reg = ObjectifyService.ofy().load().type(Registration.class).filter("user_id", student.getUserId()).first().now();
            pageContext.setAttribute("reg", reg);
            if(reg != null)
            {
                Group group = ObjectifyService.ofy().load().type(Group.class).id(reg.getGroup().getId()).now(); // filter("number",reg.getGroup().getId()).first().now();
                pageContext.setAttribute("regestired_group", group);
%>
<p>You are registered to group ${fn:escapeXml(regestired_group.number)} with the following detail:</p>
<ul>
<li>Group date: ${fn:escapeXml(regestired_group.date)}</li>
<li>Group room: ${fn:escapeXml(regestired_group.date)}</li>
<li>Group instructor name: ${fn:escapeXml(regestired_group.instructor_name)}</li>
</ul>
<%
            }
            else
            {
                List<Group> groups = ObjectifyService.ofy()
                                                    .load()
                                                    .type(Group.class) // We want only Greetings
                                                    .list();
                if (groups.isEmpty())
                {
%>
<p>No groups are available in the system</p>
<%                   
                }
                else
                {
%>
<p>Groups: </p>
<ul>
<%          
                for (Group group: groups) {
                    pageContext.setAttribute("available_group", group);
%>
<li>Group number: ${fn:escapeXml(available_group.number)}, Group date: ${fn:escapeXml(available_group.date)}, Group room: ${fn:escapeXml(available_group.room)}, Group instructor name: ${fn:escapeXml(available_group.instructor_name)}</li>
<form action="/register" method="post">
<input type="hidden" name = "group" value= "${fn:escapeXml(available_group.number)}"/>
<input type="submit" value= "Register"/>
</form>
<%                  
                }
%>
</ul>
<%
                }
            }
        }
        else if (lecturer!=null)
        {
            pageContext.setAttribute("lecturer", lecturer);
%>
<p>Hello, ${fn:escapeXml(lecturer.firstname)}! (You can <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%            
        }
        else
        {
%>
<p>Hello, an Error occured!  (You can <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%        
        }   
    }
    else
    {
%>
<p>
<a href="student.jsp">Student</a>
</p>
<p>
<a href="lecturer.jsp">Lecturer</a>
</p>
<%        
    }
%>

</body>
</html>
<%-- //[END all]--%>
