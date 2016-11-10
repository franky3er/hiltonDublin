<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
List<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");
Reservation reservation = (Reservation) request.getAttribute("reservation");
String error = (String) request.getAttribute("error");
String showContent = (String) request.getAttribute("showContent");

List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);

//ERRORS
boolean errorFirstNameEmpty = false;
boolean errorLastNameEmpty = false;
boolean errorEmailNotInRightFormat = false;
boolean errorPassportNrEmpty = false;
boolean errorPassportNrNotInRightFormat = false;
boolean successfullyMofifiedGuest = false;
boolean successfullyDeleteRoom = false;
boolean errorDeleteRoom = false;
boolean errorAddRoomToReservationNoAvailableRoomsFound = false;
boolean errorAddRoomToReservationFailed = false;
boolean successfullyAddRoomToReservation = false;

//Modify Reservation Details Params
String modifyReservationDetailsError = (String) request.getAttribute("modifyReservationDetailsError");
String modifyReservationDetailsSuccess = (String) request.getAttribute("modifyReservationDetailsSuccess");
if(error==null){
	error = "0";
}
if(showContent==null){
	showContent="reservationSelection";
}
if(modifyReservationDetailsError==null){
	modifyReservationDetailsError="0";
}
if(modifyReservationDetailsSuccess==null){
	modifyReservationDetailsSuccess="0";
}

//Modify Guest Params
String modifyGuestDetailsErrorFirstName = (String) request.getAttribute("modifyGuestDetailsErrorFirstName");
String modifyGuestDetailsErrorLastName = (String) request.getAttribute("modifyGuestDetailsErrorLastName");
String modifyGuestDetailsErrorEmail = (String) request.getAttribute("modifyGuestDetailsErrorEmail");
String modifyGuestDetailsErrorPassportNr = (String) request.getAttribute("modifyGuestDetailsErrorPassportNr");
String modifyGuestDetailsSuccessful = (String) request.getAttribute("modifyGuestDetailsSuccessful");
if(modifyGuestDetailsErrorFirstName == null){
	modifyGuestDetailsErrorFirstName = "0";
}
if(modifyGuestDetailsErrorLastName == null){
	modifyGuestDetailsErrorLastName = "0";
}
if(modifyGuestDetailsErrorEmail == null){
	modifyGuestDetailsErrorEmail = "0";
}
if(modifyGuestDetailsErrorPassportNr == null){
	modifyGuestDetailsErrorPassportNr = "0";
}
if(modifyGuestDetailsSuccessful == null){
	modifyGuestDetailsSuccessful = "0";
}

if(modifyGuestDetailsErrorFirstName.equals("1")){
	errorFirstNameEmpty = true;
}
if(modifyGuestDetailsErrorLastName.equals("1")){
	errorLastNameEmpty = true;
}
if(modifyGuestDetailsErrorEmail.equals("2")){
	errorEmailNotInRightFormat = true;
}
if(modifyGuestDetailsErrorPassportNr.equals("1")){
	errorPassportNrEmpty = true;
}
if(modifyGuestDetailsErrorPassportNr.equals("2")){
	errorPassportNrNotInRightFormat = true;
}
if(modifyGuestDetailsSuccessful.equals("1")){
	successfullyMofifiedGuest = true;
}

//Modify Rooms Params
String deleteRoomSuccessful = (String) request.getAttribute("deleteRoomSuccessful");
String deleteRoomError = (String) request.getAttribute("deleteRoomError");
String addRoomToReservationSuccessful = (String) request.getAttribute("addRoomToReservationSuccessful");
String addRoomToReservationError = (String) request.getAttribute("addRoomToReservationError");
if(deleteRoomSuccessful == null){
	deleteRoomSuccessful = "0";
}
if(deleteRoomError == null){
	deleteRoomError = "0";
}
if(addRoomToReservationSuccessful == null){
	addRoomToReservationSuccessful = "0";
}
if(addRoomToReservationError == null){
	addRoomToReservationError = "0";
}
if(deleteRoomSuccessful.equals("1")){
	successfullyDeleteRoom = true;
}
if(deleteRoomError.equals("1")){
	errorDeleteRoom = true;
}
if(addRoomToReservationError.equals("1")){
	errorAddRoomToReservationNoAvailableRoomsFound = true;
}
if(addRoomToReservationError.equals("2")){
	errorAddRoomToReservationFailed = true;
}
if(addRoomToReservationSuccessful.equals("1")){
	successfullyAddRoomToReservation = true;
}

%>

<h1><%=language.administratorModifyReservationHeading() %></h1>

<%if(showContent.equals("reservationSelection")){ %>
<form action="<%=request.getContextPath() %>/Admin/Modify-Reservation-get-reservations" method="get" novalidate>
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
</form>

<%} else if(showContent.equals("showReservations")){ int i = 0;%>

<p class="informational"><%=language.administratorModifyReservationNumberOfReservationsFound(reservations.size()) %></p>

<table>
  <tr>
    <th align="left"><%=language.administratorModifyReservationBookingNummber() %></th>
    <th align="left"><%=language.administratorModifyReservationGuestFirstName() %></th>
    <th align="left"><%=language.administratorModifyReservationGuestLastName() %></th>
    <th align="left"><%=language.administratorModifyReservationArrivalDate() %></th>
    <th align="left"><%=language.administratorModifyReservationDepartureDate() %></th>
    <th align="left"></th>
  </tr>
  <%for(Reservation reserv : reservations){ %>
  <form action="<%=request.getContextPath() %>/Admin/Modify-Reservation-get-reservation" method="get" >
  <input type="hidden" name="bookingNumber" value="<%=Integer.toString(reserv.getBookingNumber()) %>"/>
  <input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
  <tr>
    <td <%if(i%2==0){ %>style="background-color:blue; color:white;"<%} %>><%=reserv.getBookingNumber() %></td>
    <td <%if(i%2==0){ %>style="background-color:blue; color:white;"<%} %>><%=reserv.getGuest().getFirstName() %></td>
    <td <%if(i%2==0){ %>style="background-color:blue; color:white;"<%} %>><%=reserv.getGuest().getLastName() %></td>
    <td <%if(i%2==0){ %>style="background-color:blue; color:white;"<%} %>><%=dbConnection.onlyDayDateFormat.format(reserv.getArrivalDate()) %></td>
    <td <%if(i%2==0){ %>style="background-color:blue; color:white;"<%} %>><%=dbConnection.onlyDayDateFormat.format(reserv.getDepartureDate()) %></td>
    <td ><input type="submit" value="<%=language.administratorModifyReservationModifyButton() %>"/></td>
  </tr>
  </form>
  <%i++;} %>
</table>

<%} else if(showContent.equals("showReservation")){ %>

	<%  if(modifyReservationDetailsSuccess.equals("1")){ %> 
		<p class="informational"><%=language.administratorModifyReservationDetailsSuccessful() %></p> 
	<%} if(successfullyMofifiedGuest){ %> 
		<p class="informational"><%=language.administratorModifyGuestDetailsSuccessful() %></p> 
	<%} if(successfullyDeleteRoom){ %> 
		<p class="informational"><%=language.administratorDeleteRoomSuccessful() %></p> 
	<%} if(successfullyAddRoomToReservation){ %> 
		<p class="informational"><%=language.administratorAddRoomToReservationSuccessful() %></p> 
	<%} if(errorFirstNameEmpty){ %> 
		<p class="error"><%=language.administratorModifyGuestErrorFirstNameEmpty() %></p> 
	<%} if(errorAddRoomToReservationNoAvailableRoomsFound){ %> 
		<p class="error"><%=language.administratorAddRoomToReservationErrorNoAvailableRoom() %></p> 
	<%} if(errorAddRoomToReservationFailed){ %> 
		<p class="error"><%=language.administratorAddRoomToReservationErrorFailed() %></p> 
	<%} if(errorLastNameEmpty){ %> 
		<p class="error"><%=language.administratorModifyGuestErrorLastNameEmpty() %></p> 
	<%} if(errorEmailNotInRightFormat){ %> 
		<p class="error"><%=language.administratorModifyGuestErrorEmailNotInRightFormat() %></p> 
	<%} if(errorPassportNrEmpty){ %> 
		<p class="error"><%=language.administratorModifyGuestErrorPassportNrEmpty() %></p>
	<%} if(errorPassportNrNotInRightFormat){ %> 
		<p class="error"><%=language.administratorModifyGuestErrorPassportNrNotInRightFormat() %></p>
	<%} if(errorDeleteRoom){ %> 
		<p class="error"><%=language.administratorDeleteRoomError() %></p>
	<%} %>
	
<br/>

<h2><%=language.administratorModifyReservationReservationDetailsHeading() %></h2>

<form action="<%=request.getContextPath() %>/Admin/Modify-Reservation-details" method="post">
<table class="showValues">
  <tr>
    <td><b><%=language.administratorModifyReservationBookingNummber() %></b></td>
    <td><%=reservation.getBookingNumber() %></td>
  </tr>
  <tr>
    <td><b><%=language.administratorModifyReservationArrivalDate() %></b></td>
    <td><input type="date" name="arrivalDate" value="<%=dbConnection.onlyDayDateFormat.format(reservation.getArrivalDate()) %>" required/></td>
  </tr>
  <tr>
    <td><b><%=language.administratorModifyReservationDepartureDate() %></b></td>
    <td><input type="date" name="departureDate" value="<%=dbConnection.onlyDayDateFormat.format(reservation.getDepartureDate()) %>" required/></td>
  </tr>
  <tr>
  	<td></td>
  	<td><input type="submit" value="<%=language.administratorModifyReservationModifyButton() %>"/></td>
  </tr>
</table>
<input type="hidden" name="bookingNumber" value="<%=Integer.toString(reservation.getBookingNumber()) %>"/>
<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/> 
<input type="hidden" name="guestID" value="<%=Integer.toString(reservation.getGuestID()) %>"/>
</form>

<br/>

<h2><%=language.administratorModifyReservationGuestDetailsHeading() %></h2>

<form action="<%=request.getContextPath() %>/Admin/Modify-Guest-details" method="post">
<table class="showValues">
	<tr>
		<td><%=language.administratorModifyReservationGuestFirstName() %></td>
		<td><input <%if(errorFirstNameEmpty){ %>class="emptyTextField" <%} %> type="text" name="firstName" value="<%=reservation.getGuest().getFirstName() %>"/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestLastName() %></td>
		<td><input <%if(errorLastNameEmpty){ %>class="emptyTextField" <%} %> type="text" name="lastName" value="<%=reservation.getGuest().getLastName() %>"/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestPhoneNumber() %></td>
		<td><input type="text" name="phoneNumber" <%if(reservation.getGuest().getPhoneNumber()!=null){ %>value="<%=reservation.getGuest().getPhoneNumber() %>" <%} %>/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestEmail() %></td>
		<td><input <%if(errorEmailNotInRightFormat){ %>class="emptyTextField" <%} %> type="text" name="email" <%if(reservation.getGuest().getEmail()!=null){ %>value="<%=reservation.getGuest().getEmail() %>" <%} %>/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestAddress() %></td>
		<td><input type="text" name="address" <%if(reservation.getGuest().getAddress()!=null){ %>value="<%=reservation.getGuest().getAddress() %>"<%} %>/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestPassportNr() %></td>
		<td><input <%if(errorPassportNrEmpty || errorPassportNrNotInRightFormat){ %>class="emptyTextField" <%} %> type="text" name="passportNr" value="<%=reservation.getGuest().getPassportNr() %>"/></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="<%=language.administratorModifyReservationModifyButton() %>"/></td>
	</tr>
</table>
<input type="hidden" name="bookingNumber" value="<%=Integer.toString(reservation.getBookingNumber()) %>"/>
<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/> 
<input type="hidden" name="guestID" value="<%=reservation.getGuestID() %>"/>
</form>

<br/>

<h2><%=language.administratorModifyRoomDetailsHeading() %></h2>

<table class="showValues">
	<tr>
		<th align="left"><%=language.administratorModifyRoomDetailsRoomNumber() %></th>
		<th align="left"><%=language.administratorModifyRoomDetailsType() %></th>
		<th align="left"><%=language.administratorModifyRoomDetailsSmoking() %></th>
		<th align="left"><%=language.administratorModifyRoomDetailsOccupied() %></th>
		<th align="left"></th>
	</tr>
	<%
	List<Room> rooms = reservation.getRooms();
	for(Room room : rooms){
	%>
	<tr>
		<td><%=room.getRoomNumber() %></td>
		<td><%=room.getType().getName() %></td>
		<td><%=room.isSmoking() %></td>
		<td><%=room.isOccupied() %></td>
		<td>
			<form action="<%=request.getContextPath() %>/Admin/Modify-Reservation-delete-room" method="post">
				<input type="hidden" name="roomNumber" value="<%=room.getRoomNumber() %>"/>
				<input type="hidden" name="bookingNumber" value="<%=Integer.toString(reservation.getBookingNumber()) %>"/>
				<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
				<input type="submit" value="<%=language.delete() %>"/>
			</form>
		</td>
	</tr>	
	<%} %>
	
	<form action="<%=request.getContextPath() %>/Admin/Modify-Reservation-add-room" method="post">
	<tr>
		<td></td>
		<td>
			<select name="typeID">
				<%for(RoomType roomType : roomTypes){ %>
				<option value="<%=roomType.getRoomTypeID() %>"><%=roomType.getName() %></option>
				<%} %>
			</select>
		</td>
		<td>
			<input type="radio" name="smoking" value="true"><%=language.yes() %>
			<input type="radio" name="smoking" value="false"><%=language.no() %>
		</td>
		<td>
		</td>
		<td><input type="submit" value="<%=language.add() %>"/></td>
	</tr>
	<input type="hidden" name="bookingNumber" value="<%=Integer.toString(reservation.getBookingNumber()) %>"/>
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>	
	</form>
	
</table>

<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>