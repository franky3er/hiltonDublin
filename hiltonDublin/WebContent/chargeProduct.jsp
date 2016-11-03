<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="com.hiltondublin.classes.Reservation"%>
<%@page import="com.hiltondublin.classes.ConsumerProduct"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>

<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
String productID = "1";
String reservationID = "1";
dbConnection.assignProductToReservation(productID, reservationID);

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>