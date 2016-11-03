<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.exceptions.MySQLDataException"%>
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.classes.Rating" %>
<%@ page import = "java.util.*"%>
<%@ page import = "java.lang.Integer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
String typeid = request.getParameter("roomtype");
String rating = request.getParameter("rating");
String comment = request.getParameter("comment");

Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3304/hiltonhotel","root", "root");
Statement st = con.createStatement();
ResultSet rs;
st.executeUpdate("insert into rating values ('4','" + typeid + "',null,'" + rating + "','" + comment + "')");
%>    
<p> Thank you for your review</p>
<p>Your rating was: ${param.rating}</p>
<p>Your comment was: ${param.comment}</p>

<%@ include file="navigationSlideGuestFooter.jsp" %>