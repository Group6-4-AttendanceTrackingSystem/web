<%-- //[START all]--%>
<%@page import="com.googlecode.objectify.annotation.Load"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Group"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Users.Student"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Users.Lecturer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>

<%-- //[START imports]--%>
<%@ page import="com.ase_group6_4.AttendanceTrackingSystem.*" %>
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
            <p>Hello, ${fn:escapeXml(student.firstname)}!</p>
<%            
        }
        else if (lecturer!=null)
        {
            pageContext.setAttribute("lecturer", lecturer);
%>
            <p>Hello, ${fn:escapeXml(lecturer.firstname)}!</p>
<%            
        }
        else
        {
%>
            <p>Hello, an Error occured.</p>
<%        
        }   
%>
<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>
<% 
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
