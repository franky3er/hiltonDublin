<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.classes.Reservation"%>
<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="com.hiltondublin.users.Guest"%>
<%@page import="com.hiltondublin.classes.ConsumerProduct"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page language="java" contentType="text/html; charset=EUC-KR"%>

<%@ include file="navigationSlideEmployeeHeader.jsp"%>

<%
List<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");
String error = (String) request.getAttribute("error");
String selectedReservationID = (String) request.getParameter("selectedReservationID");
String cancelReservationSuccessful = (String) request.getAttribute("cancelReservationSuccessful");

Reservation selectedReservation = null;

boolean cancelReservationSuccess = false;

if(error==null){
	error = "0";
}
if(cancelReservationSuccessful == null) {
	cancelReservationSuccessful = "0";
}

if(selectedReservationID != null){
	List<Reservation> selectedReservations = dbConnection.getReservations(selectedReservationID, null, null, null, null, null);
	if(selectedReservations != null){
		if(!selectedReservations.isEmpty()){
			selectedReservation = selectedReservations.get(0);
		}
	}
}
if(cancelReservationSuccessful.equals("1")){
	cancelReservationSuccess = true;
}
%>

<h1><%=language.employeeCancelReservationHeading() %></h1>

<%if(selectedReservation == null){ %>

	<form action="<%=request.getContextPath() %>/Employee/Cancel-Reservation-get-reservations" method="get" novalidate>
		<table class="showValues">
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
				<td><input type="date" name="arrivalDate"> </td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationDepartureDate() %></td>
				<td><input type="date" name="departureDate"> </td>
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
		<%if(cancelReservationSuccess){ %><p class="informational"><%=language.employeeCancelReservationSuccessful() %></p><%} %>
	</form>

<%} %>

<%if(reservations != null){ %>
	
	<br/>
	
	<p class="informational"><%=language.administratorModifyReservationNumberOfReservationsFound(reservations.size()) %></p>
	
	<%int i = 1; for(Reservation reservation : reservations){ %>
		
		<form action="<%=request.getRequestURI()%>" method="post">
			<fieldset>
			<legend><b><%=language.employeeCancelReservationNumber(i) %></b></legend>
				<table class="showValues">
					<tr>
						<td><%=language.administratorModifyReservationGuestFirstName() %></td>
						<td><b><%=reservation.getGuest().getFirstName() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationGuestLastName() %></td>
						<td><b><%=reservation.getGuest().getLastName() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationGuestPhoneNumber() %></td>
						<td><b><%=reservation.getGuest().getPhoneNumber() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationGuestEmail() %></td>
						<td><b><%=reservation.getGuest().getEmail() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationGuestAddress() %></td>
						<td><b><%=reservation.getGuest().getAddress() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationGuestPassportNr() %></td>
						<td><b><%=reservation.getGuest().getPassportNr() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationBookingNummber() %></td>
						<td><b><%=reservation.getBookingNumber() %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationArrivalDate() %></td>
						<td><b><%=dbConnection.onlyDayDateFormat.format(reservation.getArrivalDate()) %></b></td>
					</tr>
					<tr>
						<td><%=language.administratorModifyReservationDepartureDate() %></td>
						<td><b><%=dbConnection.onlyDayDateFormat.format(reservation.getDepartureDate()) %></b></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="<%=language.employeeCancelReservationCancelButton() %>"/></td>
					</tr>
				</table>
			</fieldset>
			<input type="hidden" name="selectedReservationID" value="<%=reservation.getBookingNumber() %>"/>
		</form>
		
		<br/>
		
	<%i++;} %>
	
<%} %>

<%if(selectedReservation != null){ %>

	<h4><%=language.employeeCancelReservationConfirmation() %></h4>
	
	<form action="<%=request.getContextPath() %>/Employee/Cancel-Reservation-confirmed" method="post">
	<fieldset>
	<legend><b><%=language.employeeCancelReservation() %></b></legend>
		<table class="showValues">
			<tr>
				<td><%=language.administratorModifyReservationGuestFirstName() %></td>
				<td><b><%=selectedReservation.getGuest().getFirstName() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationGuestLastName() %></td>
				<td><b><%=selectedReservation.getGuest().getLastName() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationGuestPhoneNumber() %></td>
				<td><b><%=selectedReservation.getGuest().getPhoneNumber() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationGuestEmail() %></td>
				<td><b><%=selectedReservation.getGuest().getEmail() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationGuestAddress() %></td>
				<td><b><%=selectedReservation.getGuest().getAddress() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationGuestPassportNr() %></td>
				<td><b><%=selectedReservation.getGuest().getPassportNr() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationBookingNummber() %></td>
				<td><b><%=selectedReservation.getBookingNumber() %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationArrivalDate() %></td>
				<td><b><%=dbConnection.onlyDayDateFormat.format(selectedReservation.getArrivalDate()) %></b></td>
			</tr>
			<tr>
				<td><%=language.administratorModifyReservationDepartureDate() %></td>
				<td><b><%=dbConnection.onlyDayDateFormat.format(selectedReservation.getDepartureDate()) %></b></td>
			</tr>
		</table>
	</fieldset>
	<input type="hidden" name="selectedReservationID" value="<%=selectedReservation.getBookingNumber() %>"/>
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	
	<br/>
	
	<input type="submit" value="<%=language.yes() %>"/>
	<a href="<%=getURLWithContextPath(request) %>/Employee/Cancel-Reservation"><input type="button" value="<%=language.no() %>"/></a>
		
	</form>

<%} %>

<%@ include file="navigationSlideEmployeeFooter.jsp"%>