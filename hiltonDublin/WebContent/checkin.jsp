<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="com.hiltondublin.classes.Reservation"%>
<%@ include file="navigationSlideGuestHeader.jsp" %>
<h1>Check In</h1>
<%
String firstName = (String) request.getParameter("firstName");
String lastName = (String) request.getParameter("lastName");
List<Reservation> reservations = dbConnection.getReservationsFromGuestName(firstName, lastName);

%>
	<form id="checkinForm" name="checkinForm" action="<%=request.getRequestURI()%>" method="post">
	<label>Name: 
		<input type="text" name="firstName" placeholder="First Name" />
		<input type="text" name="lastName" placeholder="Last Name" />
		<input type="submit" name="checkin" value="submit"/>
	</label>
	</form>
	<%if(firstName != null && lastName!= null) {%>
	<%if(reservations == null){out.println("There are no reservations for " + firstName + " " + lastName);}else{ %>
	<% for(Reservation res: reservations){ %>
	<select name="reservations">
		<option><%=res.getBookingNumber() %></option>
	</select>
	<%} %>
	<%}} %>
	
<%@ include file="navigationSlideGuestFooter.jsp" %>