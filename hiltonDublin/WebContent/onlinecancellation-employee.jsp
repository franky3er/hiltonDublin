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
	/*Get Bookings data*/
		String reservationID = null;
		String guestID = null;
		String arrivalDate = null;
		String departureDate = null;
		String paid = null;
		String additionalSQLCondition = null;
		List<String> reservationAsStrings = new ArrayList<String>();
		List<Reservation> reservations = dbConnection.getReservations(reservationID, guestID, arrivalDate,
				departureDate, paid, additionalSQLCondition);
	

		/*Get Geusts data*/
		String firstName = null;
		String lastName = null;
		String phoneNumber = null;
		String email = null;
		String address = null;
		String passportNr = null;
		List<String> guestsAsStrings = new ArrayList<String>();

		//Get every saved guests datas from DB.
		List<Guest> guests = dbConnection.getGuests(guestID, firstName, lastName, phoneNumber, email, address,
				passportNr, additionalSQLCondition);
		System.out.println("guest.size(): " + guests.size());
		for (Guest guest : guests) {
			guestsAsStrings.add("GuestId: " + guest.getGuestID() + "  LastName :" + guest.getLastName());
		}
		/*Get Rooms data*/
		String roomNumber = null;
		String typeID = null;
		String smoking = "true";
		String occupied = "false";
		String additionalSQL = HiltonDublinDBConnection.ROOM_NUMBER + ">'100'";

		List<String> roomsAsStrings = new ArrayList<String>();

		List<Room> rooms = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, additionalSQL);
		System.out.println("rooms.size() : " + rooms.size());
		System.out.println("HiltonDublinDBConnection.ROOM_NUMBER : " + HiltonDublinDBConnection.ROOM_NUMBER);
		System.out.println("additionalSQL : " + additionalSQL);
		for (Room room : rooms) {
			roomsAsStrings.add("RoomNumber: " + room.getRoomNumber() + "   typeID: " + room.getTypeID()
					+ "   smoking: " + room.isSmoking() + "   occupied: " + room.isOccupied());
		}
%>


<%
	String inputForSearch = request.getParameter("inputForSearch");
		String searchKey = request.getParameter("dropdownlist");
		String radio = request.getParameter("regState");
		String checkBox[] = request.getParameterValues("checkbox_1");
		boolean isinputSearch = false;
		boolean isradio = false;
		boolean ischeckBox = false;
		List<Reservation> searchedReservations = dbConnection.getReservations(reservationID, guestID,
				arrivalDate, departureDate, paid, additionalSQLCondition);

		if (inputForSearch != null) {
			isinputSearch = true;
			if (searchKey != null) {
				System.out.println("Drop down check : " + searchKey);
			}
			if (searchKey.equals("list_ReservationID"))
				searchedReservations = dbConnection.getReservations(inputForSearch, guestID, arrivalDate,
						departureDate, paid, additionalSQLCondition);

			if (searchKey.equals("list_GuestID"))
				searchedReservations = dbConnection.getReservations(reservationID, inputForSearch, arrivalDate,
						departureDate, paid, additionalSQLCondition);

			/* 
			if(searchKey.equals( "list_GuestName"))
				searchedReservations = dbConnection.getReservations(reservationID, guestID, arrivalDate, departureDate, paid, additionalSQLCondition);
			
			if(searchKey.equals( "list_RoomNumber"))
				searchedReservations = dbConnection.getReservations(reservationID, guestID, arrivalDate, departureDate, paid, additionalSQLCondition);
			 */
			if (checkBox != null) {
				for (int i = 0; i < checkBox.length; i++) {
					ischeckBox = true;
				}
			}
			if (radio != null) {
				isradio = true;
			}
		}
%>

<%
	if (isinputSearch == false) {
%>
<h1>Cancel Booking</h1>
<form id="onlinecancellationform-employee"
	name="onlinecancellationform-employee"
	action="<%=request.getRequestURI()%>" method="post">
	<select name="dropdownlist">
		<option value="list_ReservationID">Reservation ID</option>
		<option value="list_GuestID">Guest ID</option>
	</select> <input type="text" name="inputForSearch" placeholder="Reference"
		size="30" /> <input name="regState" type="radio" value="Search"
		checked>Search <input name="regState" type="radio"
		value="Cancel">Cancel <input type="submit" value="Ok" />
	<table border="1px" cellsapcing="" cellpadding="4">
		<tr>
			<!-- 1st Line -->
			<th>Select</th>
			<th>Reservation ID</th>
			<th>Guest ID</th>
			<th>Arrival Date</th>
			<th>Departure Date</th>
		</tr>

	</table>
</form>

<%
	} else if (isinputSearch == true && (radio.equals("Search"))) {
%>

<h1>Cancel Booking</h1>
<form id="onlinecancellationform-employee"
	name="onlinecancellationform-employee"
	action="<%=request.getRequestURI()%>" method="post">
	<select name="dropdownlist">
		<option value="list_ReservationID">Reservation ID</option>
		<option value="list_GuestID">Guest ID</option>
	</select> <input type="text" name="inputForSearch" placeholder="Reference"
		size="30" /> <input name="regState" type="radio" value="Search"
		checked>Search <input name="regState" type="radio"
		value="Cancel">Cancel <input type="submit" value="Ok" />
	<table border="1px" cellsapcing="" cellpadding="4">
		<tr>
			<!-- 1st Line -->
			<th>Select</th>
			<th>Reservation ID</th>
			<th>Guest ID</th>
			<th>Arrival Date</th>
			<th>Departure Date</th>
		</tr>
		<%
			int checkbox_index = 0;
					for (Reservation searchedReservation : searchedReservations) {
		%>
		<tr>
			<!-- Nth Line -->
			<th><input type="checkbox" name="checkbox_1"
				value=<%=searchedReservation.getBookingNumber()%> /></th>
			<th><%=searchedReservation.getBookingNumber()%></th>
			<th><%=searchedReservation.getGuestID()%></th>
			<th><%=searchedReservation.getArrivalDate()%></th>
			<th><%=searchedReservation.getDepartureDate()%></th>
		</tr>

		<%
			}
		%>
	</table>
</form>
<%
	} else if (isinputSearch == true && (radio.equals("Cancel"))) {
%>
<h1>Cancel Booking</h1>
<form id="onlinecancellationform-employee"
	name="onlinecancellationform-employee"
	action="<%=request.getRequestURI()%>" method="post">
	<select name="dropdownlist">
		<option value="list_ReservationID">Reservation ID</option>
		<option value="list_GuestID">Guest ID</option>
	</select> <input type="text" name="inputForSearch" placeholder="Reference"
		size="30" /> <input name="regState" type="radio" value="Search">Search
	<input name="regState" type="radio" value="Cancel" checked>Cancel
	<input type="submit" value="Ok" />
	<table border="1px" cellsapcing="" cellpadding="4">
		<tr>
			<!-- 1st Line -->
			<th>Select</th>
			<th>Reservation ID</th>
			<th>Guest ID</th>
			<th>Arrival Date</th>
			<th>Departure Date</th>
		</tr>

		<%
			if (ischeckBox)
						for (int i = 0; i < checkBox.length; i++) {
							dbConnection.deleteReservations(checkBox[i], guestID, arrivalDate, departureDate, paid,
									additionalSQLCondition);
						}
		%>
		<%
			reservations = dbConnection.getReservations(reservationID, guestID, arrivalDate, departureDate,
							paid, additionalSQLCondition);
					for (Reservation Reservation : reservations) {
		%>
		<tr>
			<!-- Nth Line-->
			<th><input type="checkbox" name="checkbox_1"
				value=<%=Reservation.getBookingNumber()%> /></th>
			<th><%=Reservation.getBookingNumber()%></th>
			<th><%=Reservation.getGuestID()%></th>
			<th><%=Reservation.getArrivalDate()%></th>
			<th><%=Reservation.getDepartureDate()%></th>
		</tr>

		<%
			}
		%>
	</table>
</form>

<%
	}
%>

<%@ include file="navigationSlideEmployeeFooter.jsp"%>