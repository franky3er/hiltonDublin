<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
List<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");
String error = (String) request.getAttribute("error");
String showContent = (String) request.getAttribute("showContent");


if(error==null){
	error = "0";
}
if(showContent==null){
	showContent="reservationSelection";
}


Calendar currentDate = Calendar.getInstance();
currentDate.setTime(new Date());

Calendar tomorrowDate = Calendar.getInstance();
tomorrowDate.setTime(new Date());
tomorrowDate.add(Calendar.DAY_OF_MONTH, 1);


%>

<h1><%=language.administratorModifyReservationHeading() %></h1>

<%if(showContent.equals("reservationSelection")){ %>
<form action="<%=request.getContextPath() %>/Admin/Modify-Reservation-get-reservations" method="get" novalidate>
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
			<td><input type="date" name="departureDate" min="<%=dbConnection.onlyDayDateFormat.format(tomorrowDate.getTime()) %>" required> </td>
		</tr>
		<tr>
			<td><input type="submit" value="<%=language.administratorModifyReservationSearchReservation() %>"/> </td>
			<td> </td>
		</tr>
	</table>
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	<%if(error.equals("1")){ %>
	<p class="error"><%=language.administratorModifyReservationErrorBookingNumber() %></p>
	<%} else if(error.equals("2")){ %>
	<p class="error"><%=language.administratorModifyReservationErrorNoReservationFound() %></p>
	<%} %>
</form>

<%} else if(showContent.equals("showReservations")){ %>
<table>
  <tr>
    <th><%=language.administratorModifyReservationBookingNummber() %></th>
    <th><%=language.administratorModifyReservationGuestFirstName() %></th>
    <th><%=language.administratorModifyReservationGuestLastName() %></th>
    <th><%=language.administratorModifyReservationArrivalDate() %></th>
    <th><%=language.administratorModifyReservationDepartureDate() %></th>
    <th><%=language.administratorModifyReservationRooms() %></th>
    <th><%=language.administratorModifyReservationConsumerProducts() %></th>
    <th></th>
  </tr>
  <%for(Reservation reservation : reservations){ %>
  <form action="" method="get" >
  <input type="hidden" name="bookingNumber" value="<%=Integer.toString(reservation.getBookingNumber()) %>"/>
  <tr>
    <td><%=reservation.getBookingNumber() %></td>
    <td><%=reservation.getGuest().getFirstName() %></td>
    <td><%=reservation.getGuest().getLastName() %></td>
    <td><%=dbConnection.onlyDayDateFormat.format(reservation.getArrivalDate()) %></td>
    <td><%=dbConnection.onlyDayDateFormat.format(reservation.getDepartureDate()) %></td>
    <td></td>
    <td></td>
    <td><input type="submit" value="<%=language.administratorModifyReservationModifyButton() %>"/></td>
  </tr>
  </form>
  <%} %>
</table>

<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>