<%-- //[START all]--%>
<%@page import="com.googlecode.objectify.annotation.Load"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.*" %>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Models.*"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Services.*"%>
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
    StudentService studentService = StudentService.getInstance();
    Student student;
    if (user != null)
    {
        student = studentService.getStudentByUser(user);
        if (student == null)
        {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            if ( firstname != null && lastname != null)
            {
                student = new Student(user.getUserId(),user.getEmail(),firstname,lastname);
                studentService.saveStudent(student);
%>
        <jsp:forward page="home.jsp"/> 
<%
            }
            else
            {
%>
        <form action="student.jsp" method="POST" >
          <table width="75%">
            <tr> 
              <td width="48%">Firstname</td>
              <td width="52%">
                <input type="text" name="firstname" />
              </td>
            </tr>
            <tr> 
              <td width="48%">Lastname</td>
              <td width="52%">
                <input type="text" name="lastname" />
              </td>
            </tr>
          </table>
          <p> 
            <input type="submit" name="Submit" value="Submit Personal Details" />
          </p>
        </form>
<%
            }
        }
        else
        {   
%>
        <jsp:forward page="home.jsp"/> 
<%
        }
    }
    else
    {
%>
<p>
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in as Student</a>
</p>
<%
    }
%>

</body>
</html>
<%-- //[END all]--%>
