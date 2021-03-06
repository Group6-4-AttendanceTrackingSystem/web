<%-- //[START all]--%>
<%@page import="com.googlecode.objectify.annotation.Load"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.*" %>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Models.*"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Services.*"%>
<%@page import="com.ase_group6_4.AttendanceTrackingSystem.Server.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    Student student;
    User user;
    UserService userService = UserService.getInstance();
    user = userService.getCurrentUser(request);

    if (user != null)
    { 
%>
        <jsp:forward page="home.jsp"/> 
<%
    }
    else
    {
%>

<p>
<a href="login.jsp">Login as Student</a>
</p>
<p>
<a href="signup.jsp">Signup as Student</a>
</p>

<%
    }
%>

</body>
</html>
<%-- //[END all]--%>
