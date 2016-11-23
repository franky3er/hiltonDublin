<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="com.hiltondublin.classes.Reservation"%>


<%@ include file="navigationSlideEmployeeHeader.jsp" %>

<h1><%=language.employeeCheckinHeading() %></h1>
<%

String searchedForReservation = request.getParameter("searchedForReservation");
String selectedReservationID = request.getParameter("selectedReservationID");
String firstName = (String) request.getParameter("firstName");
String lastName = (String) request.getParameter("lastName");
String checkinReservationSuccessful = (String) request.getAttribute("checkinReservationSuccessful");

Reservation selectedReservation = null;

boolean checkinReservationSuccess = false;

if(searchedForReservation == null){
	searchedForReservation = "0";
}
if(checkinReservationSuccessful == null){
	checkinReservationSuccessful = "0";
}

if(checkinReservationSuccessful.equals("1")){
	checkinReservationSuccess = true;
}

if(selectedReservationID != null){
	List<Reservation> res = dbConnection.getReservations(selectedReservationID, null, null, null, null, null);
	if(res != null){
		if(!res.isEmpty()){
			selectedReservation = res.get(0);
		}
	}
}

List<Reservation> reservations = dbConnection.getUnCheckedInReservationsFromGuestName(firstName, lastName);

%>
	<form id="checkinForm" name="checkinForm" action="<%=request.getRequestURI()%>" method="post">
		<label>Name: 
			<input type="text" name="firstName" placeholder="First Name" />
			<input type="text" name="lastName" placeholder="Last Name" />
			<input type="hidden" name="searchedForReservation" value="1"/>
			<input type="submit" name="checkin" value="<%=language.search() %>"/>
		</label>
	</form>
	
	<%if(checkinReservationSuccess){ %><p class="informational"><%=language.checkinReservationSuccessful() %></p><%} %>

	<%if(searchedForReservation.equals("1")){ %>
		<%if(reservations != null){ %>
			<%if(!reservations.isEmpty()){ %>
				<p class="informational"><%=language.employeeCheckinReservationsFound(reservations.size()) %></p>
				
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
									<td><%=language.rooms() %></td>
									<td><b><%=reservation.getRoomNumbersAsString() %></b></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" value="<%=language.checkin() %>"/></td>
								</tr>
							</table>
						</fieldset>
						<input type="hidden" name="selectedReservationID" value="<%=reservation.getBookingNumber() %>"/>
					</form>
					
					<br/>
					
				<%i++;} %>
			<%} else {%>
				<p class="error"><%=language.employeeCheckinErrorNoReservationFound() %></p>
			<%} %>
		<%} else {%>
			<p class="error"><%=language.employeeCheckinErrorNoReservationFound() %></p>
		<%} %>
	<%} %>
	
	
	<%if(selectedReservation != null && !checkinReservationSuccess){ %>

		<h4><%=language.employeeCheckinReservationConfirmation() %></h4>
		
		<form action="<%=request.getContextPath() %>/Employee/Checkin-confirmed" method="post">
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
				<tr>
					<td><%=language.rooms() %></td>
					<td><b><%=selectedReservation.getRoomNumbersAsString() %></b></td>
				</tr>
			</table>
		</fieldset>
		<input type="hidden" name="selectedReservationID" value="<%=selectedReservation.getBookingNumber() %>"/>
		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
		
		<br/>
		
		<input type="submit" value="<%=language.yes() %>"/>
		<a href="<%=getURLWithContextPath(request) %>/Employee/Checkin"><input type="button" value="<%=language.no() %>"/></a>
			
		</form>
	
	<%} %>
	
<%@ include file="navigationSlideEmployeeFooter.jsp" %>