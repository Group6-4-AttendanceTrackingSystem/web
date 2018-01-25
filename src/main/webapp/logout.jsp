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

<form action="/logout" method="post">
    <div>
            <div>
                <label>E-Mail:</label>
                <input type="text" name="email"/>
            </div>
            <div>
                <label>Password:</label>
                <input type="password" name="password" />
            </div>
    </div>
    <button type="submit" id="loginButton">
        Login
    </button>
</form>

<%
    String message = request.getParameter("message");
    if(message != null && !message.isEmpty())
    {
        pageContext.setAttribute("message", message);
%>

<p> ${fn:escapeXml(message)}</p>

<%
    }
%>

</body>
</html>
<%-- //[END all]--%>
