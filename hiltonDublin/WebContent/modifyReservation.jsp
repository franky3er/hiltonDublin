<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
List<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations");
Reservation reservation = (Reservation) request.getAttribute("reservation");
String error = (String) request.getAttribute("error");
String showContent = (String) request.getAttribute("showContent");
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

	<%if(modifyReservationDetailsSuccess.equals("1")){ %> 
		<p class="informational"><%=language.administratorModifyReservationDetailsSuccessful() %></p> 
	<%} else{ %>
		<br/>
	<%} %>

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
		<td><input type="text" name="firstName" value="<%=reservation.getGuest().getFirstName() %>"/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestLastName() %></td>
		<td><input type="text" name="lastName" value="<%=reservation.getGuest().getLastName() %>"/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestPhoneNumber() %></td>
		<td><input type="text" name="phoneNumber" <%if(reservation.getGuest().getPhoneNumber()!=null){ %>value="<%=reservation.getGuest().getPhoneNumber() %>" <%} %>/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestEmail() %></td>
		<td><input type="text" name="email" <%if(reservation.getGuest().getEmail()!=null){ %>value="<%=reservation.getGuest().getEmail() %>" <%} %>/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestAddress() %></td>
		<td><input type="text" name="address" <%if(reservation.getGuest().getAddress()!=null){ %>value="<%=reservation.getGuest().getAddress() %>"<%} %>/></td>
	</tr>
	<tr>
		<td><%=language.administratorModifyReservationGuestPassportNr() %></td>
		<td><input type="text" name="passportNr" value="<%=reservation.getGuest().getPassportNr() %>"/></td>
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
<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>