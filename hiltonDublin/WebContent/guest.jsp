<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Hilton Dublin</title>
</head>
<%@ include file="navigationSlideGuestHeader.jsp" %>
<body>
	<h1>Welcome to Guest Page</h1>
		<div>
		<form action="onlinereservation.html" method="get">
			<input class="navigationPage" type="submit" value="Reservation" />
		</form>
		<form action="cancellation.html" method="get">
			<input class="navigationPage" type="submit" value="Canccellation" />
		</form>
		<form action="rating.html" method="get">
			<input class="navigationPage" type="submit" value="Rating" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideGuestFooter.jsp" %>
</html>