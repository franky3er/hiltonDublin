<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.users.User" %>
<%@page import="com.hiltondublin.users.Guest" %>
<%!
//Constants
private static final String ENGLISH = "english";
private static final String GERMAN = "german";
private static final String KOREAN = "korean";

private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
private User user = new Guest();

public String selectedLanguage(String language, String selectedLanguage){
	String selected = "selected";
	if(language.equals(selectedLanguage)){
		return selected;
	}
	else {
		return "";
	}
}
%>

<%
//Language selected
String language = request.getParameter("language");
if(language!=null){
	System.out.println("Selected Language: " + language);
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
		</div>
		<form action="index.jsp" method="get">
			<input class="navigationPage" type="submit" value="Home" />
		</form>
		<form action="guest.jsp" method="get">
			<input class="navigationPage" type="submit" value="Guest" />
		</form>
		<form action="employee.html" method="get">
			<input class="navigationPage" type="submit" value="Employee" />
		</form>
		<form action="admin.html" method="get">
			<input class="navigationPage" type="submit" value="Admin" />
		</form>
	</div>
	<div id="headline">
			<div id="headLeft" class="head1_3"><p class="heading"><%=pageName %></p></div>
			<div id="headCenter" class="head1_3">
				<label>Disability
					<input type="radio" name="disabled" value="1"/>
					<input type="radio" name="disabled" value="0" checked/>
				</label>
			</div>
			<div id="headRight" class="head1_3">
			<form action="/hiltonDublin/Home" method="post">
				<select name="language" size="1" onchange="this.form.submit()">
					<option value="<%=ENGLISH %>" <%=selectedLanguage(ENGLISH, language) %>>English</option>
					<option value="<%=GERMAN %>" <%=selectedLanguage(GERMAN, language) %>>German</option>
					<option value="<%=KOREAN %>" <%=selectedLanguage(KOREAN, language) %>>Korean</option>
				</select>
			</form>
			</div>
    </div>
	
	<div id="plain">
		<div id="plaintext">