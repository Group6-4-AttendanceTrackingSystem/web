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
    Lecturer lecturer;
    if (user != null)
    {
        lecturer = ObjectifyService.ofy().load().type(Lecturer.class).id(user.getUserId()).now();
        if (lecturer == null)
        {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            if ( firstname != null && lastname != null)
            {
                lecturer = new Lecturer(user.getUserId(),user.getEmail(),firstname,lastname);
                ObjectifyService.ofy().save().entities(lecturer).now();
%>
        <jsp:forward page="login.jsp"/> 
<%
            }
            else
            {
%>
        <form action="lecturer.jsp" method="POST" >
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
<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in as Lecturer</a>
</p>
<%
    }
%>

</body>
</html>
<%-- //[END all]--%>
