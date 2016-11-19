<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.users.User" %>
<%@page import="com.hiltondublin.users.Guest" %>
<%@page import="com.hiltondublin.languages.Language" %>
<%@page import="com.hiltondublin.languages.English" %>
<%@page import="com.hiltondublin.languages.German" %>
<%@page import="com.hiltondublin.languages.Korean" %>
<%@page import="java.util.*"%> 
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%!
//Constants
private static final String ENGLISH = "english";
private static final String GERMAN = "german";
private static final String KOREAN = "korean";
private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
private User user = new Guest();
private Language language;
public String selectedLanguage(String language, String selectedLanguage){
	String selected = "selected";
	if(language.equals(selectedLanguage)){
		return selected;
	}
	else {
		return "";
	}
}
public static String getURLWithContextPath(HttpServletRequest request) {
   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
}
%>

<%
//Selected Language
String selectedLanguage = ENGLISH;
if(request.getParameter("language")!=null){
	System.out.println("Changed language to " + request.getParameter("language"));
	session.setAttribute("language", request.getParameter("language"));
}
if(session.getAttribute("language")!=null){
	selectedLanguage = (String) session.getAttribute("language");
}
if(selectedLanguage.equals(ENGLISH)){
	user.setLanguage(new English());
}
if(selectedLanguage.equals(GERMAN)){
	user.setLanguage(new German());
}
if(selectedLanguage.equals(KOREAN)){
	user.setLanguage(new Korean());
}
language = user.getLanguage();
//Page Name
String uri = request.getRequestURI();
String pageName = uri.substring(uri.lastIndexOf("/")+1);
if(pageName == null || pageName.trim() == "" || pageName.isEmpty()){
	pageName = "Home";
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome to Hilton Hotel</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/hilton.css" />
</head>
<body>
	<div id="navigationArea">
		<img src="${pageContext.request.contextPath}/resources/Pictures/hiltonLogo.png" alt="Hilton Logo" title="Hilton Logo" />
		<div id="loginArea">
		</div>
		<form action="<%=getURLWithContextPath(request) %>/Home" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideHome() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideGuest() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest/Online-Reservation" method="get">
			<input class="navigationDetail" type="submit" value="Reservation" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest/Cancellation" method="get">
			<input class="navigationDetail" type="submit" value="Cancellation" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest/Rating" method="get">
			<input class="navigationDetail" type="submit" value="<%=language.guestRatingRating() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Employee" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideEmployee() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Admin" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideAdmin() %>" />
		</form>
	</div>
	<div id="headline">
			<div id="headLeft" class="head1_3"><p class="heading"><%=pageName %></p></div>
			<div id="headCenter" class="head1_3">
				<label><%=language.navigationSlideDisability() %>
					<input type="radio" name="disabled" value="1"/>
					<input type="radio" name="disabled" value="0" checked/>
				</label>
			</div>
			<div id="headRight" class="head1_3">
			<form action="<%=uri %>" method="post">
				<select name="language" size="1" onchange="this.form.submit()">
					<option value="<%=ENGLISH %>" <%=selectedLanguage(ENGLISH, selectedLanguage) %>>English</option>
					<option value="<%=GERMAN %>" <%=selectedLanguage(GERMAN, selectedLanguage) %>>German</option>
					<option value="<%=KOREAN %>" <%=selectedLanguage(KOREAN, selectedLanguage) %>>Korean</option>
				</select>
			</form>
			</div>
    </div>
	
	<div id="plain">
		<div id="plaintext">
