<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
List<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");


Calendar currentDate = Calendar.getInstance();
currentDate.setTime(new Date());

Calendar tomorrowDate = Calendar.getInstance();
tomorrowDate.setTime(new Date());
tomorrowDate.add(Calendar.DAY_OF_MONTH, 1);


%>

<h1><%=language.administratorModifyReservationHeading() %></h1>


<form action="" method="get">
	<table>
		<tr>
			<td><%=language.administratorModifyReservationBookingNummber() %></td>
			<td><input type="text" name="bookingNumber"> </td>
		</tr>
		<tr>
			<td><%=language.administratorModifyReservationGuestFirstName() %></td>
			<td><input type="text" name="firstName"> </td>
		</tr>
		<tr>
			<td><%=language.administratorModifyReservationGuestLastName() %></td>
			<td><input type="text" name="lastName"> </td>
		</tr>
		<tr>
			<td><%=language.administratorModifyReservationArrivalDate() %></td>
			<td><input type="date" name="arrivalDate" min="<%=dbConnection.onlyDayDateFormat.format(currentDate.getTime()) %>" required> </td>
		</tr>
		<tr>
			<td><%=language.administratorModifyReservationDepartureDate() %></td>
			<td><input type="date"  name="departureDate" min="<%=dbConnection.onlyDayDateFormat.format(tomorrowDate.getTime()) %>" required> </td>
		</tr>
		<tr>
			<td><input type="submit" value="<%=language.administratorModifyReservationSearchReservation() %>"/> </td>
			<td> </td>
		</tr>
	</table>
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>

<%@ include file="navigationSlideAdminFooter.jsp" %>