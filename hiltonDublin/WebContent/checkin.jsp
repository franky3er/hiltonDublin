<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="com.hiltondublin.classes.Reservation"%>
<%@ include file="navigationSlideGuestHeader.jsp" %>



<h1>Check In</h1>
<%

String searchedForReservation = request.getParameter("searchedForReservation");

if(searchedForReservation == null){
	searchedForReservation = "0";
}

String firstName = (String) request.getParameter("firstName");
String lastName = (String) request.getParameter("lastName");
List<Reservation> reservations = dbConnection.getReservationsFromGuestName(firstName, lastName);

%>
	<form id="checkinForm" name="checkinForm" action="<%=request.getRequestURI()%>" method="post">
		<label>Name: 
			<input type="text" name="firstName" placeholder="First Name" />
			<input type="text" name="lastName" placeholder="Last Name" />
			<input type="hidden" name="searchedForReservation" value="1"/>
			<input type="submit" name="checkin" value="<%=language.search() %>"/>
		</label>
	</form>

	<%if(searchedForReservation.equals("1")){ %>
		<%if(reservations != null){ %>
			<%if(!reservations.isEmpty()){ %>
				<p class="informational"><%=language.employeeCheckinReservationsFound(reservations.size()) %></p>
			<%} else {%>
				<p class="error"><%=language.employeeCheckinErrorNoReservationFound() %></p>
			<%} %>
		<%} else {%>
			<p class="error"><%=language.employeeCheckinErrorNoReservationFound() %></p>
		<%} %>
	<%} %>
	
<%@ include file="navigationSlideGuestFooter.jsp" %>