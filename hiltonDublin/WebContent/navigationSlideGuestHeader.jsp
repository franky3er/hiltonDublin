<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%!private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/hilton.css" />
</head>
<body>
	<div id="navigationArea">
		<img src="${pageContext.request.contextPath}/resources/Pictures/hiltonLogo.png" alt="Hilton Logo" title="Hilton Logo" />
		<div id="loginArea">
		</div>
		<form action="index.html" method="get">
			<input class="navigationPage" type="submit" value="Home" />
		</form>
		<form action="guest.html" method="get">
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
			<div id="headLeft" class="head1_3"><p class="heading">Home</p></div>
			<div id="headCenter" class="head1_3">
				<label>Disability
					<input type="radio" name="disabled" value="1"/>
					<input type="radio" name="disabled" value="0" checked/>
				</label>
			</div>
			<div id="headRight" class="head1_3">
			<select name="language" size="1">
				<option selected>English</option>
				<option>German</option>
				<option>Korean</option>
			</select>
			</div>
    </div>
	
	<div id="plain">
		<div id="plaintext">