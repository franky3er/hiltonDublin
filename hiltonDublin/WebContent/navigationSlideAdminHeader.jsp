<%@page import="com.hiltondublin.users.Administrator"%>
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.users.User" %>
<%@page import="com.hiltondublin.users.Employee" %>
<%@page import="com.hiltondublin.languages.Language" %>
<%@page import="com.hiltondublin.languages.English" %>
<%@page import="com.hiltondublin.languages.German" %>
<%@page import="com.hiltondublin.languages.Korean" %>
<%@page import="java.util.*"%> 
<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%> 
<%!
//Constants
private static final String ENGLISH = "english";
private static final String GERMAN = "german";
private static final String KOREAN = "korean";

private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
private User user = new Administrator();
private Language language;
private boolean loggedIn;

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

public String getCurrentURL(HttpServletRequest request){
	return request.getRequestURL().toString();
}

public boolean isLoggedIn(){
	return loggedIn;
}

public void setLoggedIn(boolean loggedIn){
	this.loggedIn = loggedIn;
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

//Check if logged in
String loginError = null;
Administrator admin = null;
if(session.getAttribute("user") != null){
	user = (User) session.getAttribute("user");
	
	if(user instanceof Administrator){
		admin = (Administrator) user;
		setLoggedIn(true);
	}
	else{
		setLoggedIn(false);
	}
}
else{
	setLoggedIn(false);
	loginError = (String) request.getAttribute("loginError");
}

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
			<%if(isLoggedIn()) { %>
			<form action="<%=request.getContextPath() %>/logout" id="loginForm" accept-charset="UTF-8" method="post" >
				<p><%=language.navigationSlideLoginLoggedInAs() %> <b><%=admin.getUsername() %></b></p>
				<input type="hidden" name="username" value="<%=admin.getUsername() %>" />
				<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>">
				<input type="submit" class="loginLogoutButton" name="<%=language.navigationSlideLoginLogout() %>" value="<%=language.navigationSlideLoginLogout() %>" />
			</form>
			<%} else { %>
			<form action="<%=request.getContextPath() %>/login" id="loginForm" accept-charset="UTF-8" method="post" >
				<input class="loginTextField" type="text" name="username" size="20" placeholder="<%=language.navigationSlideLoginUsername() %>"  /><br/>
				<input class="loginTextField" type="password" name="password" size="20" placeholder="<%=language.navigationSlideLoginPassword() %>"  /><br/>
				<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>">
				<input class="loginLogoutButton" id="login" type="submit" name="<%=language.navigationSlideLoginLogin() %>" value="<%=language.navigationSlideLoginLogin() %>"/>
			</form>
				<%if(loginError != null){ %>
					<%if(loginError.equals("1")) {%>
			<p class="loginError"><%=language.navigationSlideLoginWrongUsername() %></p>
					<%} else if(loginError.equals("2")) {%>
			<p class="loginError"><%=language.navigationSlideLoginWrongPassword() %></p>
					<%} %>
				<%} %>
			<%} %>
		</div>
		<div id="loginArea">
		</div>
		<form action="<%=getURLWithContextPath(request) %>/Home" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideHome() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideGuest() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Employee" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideEmployee() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Admin" method="get">
			<input class="navigationPage" type="submit" value="<%=language.navigationSlideAdmin() %>" />
		</form>
		<%if(loggedIn){ %>
		<form action="modifyRoom.html" method="get">
			<input class="navigationDetail" type="submit" value="Modify Room" />
		</form>
		<form action="modifyReservation.html" method="get">
			<input class="navigationDetail" type="submit" value="Modify Reservation" />
		</form>
		<form action="modifyProduct.html" method="get">
			<input class="navigationDetail" type="submit" value="Modify Product" />
		</form>
		<form action="registerEmployee.html" method="get">
			<input class="navigationDetail" type="submit" value="Register Employee" />
		</form>
		<%} %>
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
		<%if(!isLoggedIn()){ %>
		<h1><%=language.administratorAreaHeading() %></h1>
		<p class="loginError"><%=language.administratorLoginMessage() %></p>
		<%} else {%>