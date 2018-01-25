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
	UserService userService = UserService.getInstance();
    User user = userService.getCurrentUser(request);
    Student student;
    if (user != null)
    {
        if (!user.isLecturer())
        {
            student = (Student) user;
            GroupService groupService = GroupService.getInstance();
            pageContext.setAttribute("student", student);
%>
            <p>Hello, ${fn:escapeXml(student.firstname)}! (You can <form name="LogoutForm" method="post" action="/logout"  style="margin: 0; padding: 0; display: inline;"><input type="hidden" name="email" style="display: inline;" value=${fn:escapeXml(user.email)}><input type="hidden" name="sessionKey" style="display: inline;" value=%{fn:escapeXml(user.sessionKey)}><a style="display: inline;" href="#" onclick="document.LogoutForm.submit()">sign out</a></form>.)</p>
<%
            if(student.getGroup() != null)
            {
                Group group = student.getGroup().get();
                pageContext.setAttribute("regestired_group", group);
                User lecturer = userService.getUserByEmail(group.instructor_email);
                pageContext.setAttribute("instructor_name", ((Person)lecturer).getFirstname());
%>
<p>You are registered to group ${fn:escapeXml(regestired_group.number)} with the following detail:</p>
<ul>
<li>Group date: ${fn:escapeXml(regestired_group.date)}</li>
<li>Group room: ${fn:escapeXml(regestired_group.room)}</li>
<li>Group instructor name: ${fn:escapeXml(instructor_name)}</li>
</ul>
<%
            }
            else
            {

                List<Group> groups = groupService.getAllGroups();
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
                    User lecturer = userService.getUserByEmail(group.instructor_email);
                    pageContext.setAttribute("instructor_name", ((Person)lecturer).getFirstname());


%>
<li>Group date: ${fn:escapeXml(available_group.date)}</li>
<li>Group room: ${fn:escapeXml(available_group.room)}</li>
<li>Group instructor name: ${fn:escapeXml(instructor_name)}</li>
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
        else
        {
%>
       <p>Hello, an Error occured! (You can <form name="LogoutForm" method="post" action="/logout"  style="margin: 0; padding: 0; display: inline;"><input type="hidden" name="email" style="display: inline;" value=${fn:escapeXml(user.email)}><input type="hidden" name="sessionKey" style="display: inline;" value=%{fn:escapeXml(user.sessionKey)}><a style="display: inline;" href="#" onclick="document.LogoutForm.submit()">sign out</a></form>.)</p>
<%       
        }   
    }
    else
    {
%>
        <jsp:forward page="student.jsp"/> 
<%      
    }
%>

</body>
</html>
<%-- //[END all]--%>
