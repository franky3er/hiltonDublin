
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.helper.Helper" %>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="com.hiltondublin.users.Guest" %>
<%@page import="com.hiltondublin.classes.ConsumerProduct" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import= "java.sql.ResultSet" %>

<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
String inputReservationId = request.getParameter("inputReservationId");
String inputLastname = request.getParameter("inputlastname");

Reservation reservation = null;
Guest guest = null;

boolean isSubmitted = false;
boolean isFilledOutCorrect = true;
boolean reservationidWrong = false;
boolean lastNameWrong = false;
boolean reservationIdFound =false;
boolean lastnameFound =false;
boolean resultFound = false;

String errorMessage = "";
String resultMessage = "";
String resultMessage2 = "";

if(inputReservationId!=null && inputLastname!=null){
	if(inputReservationId.isEmpty() || inputReservationId.trim().equals(""))
	{
		errorMessage += "Booking reference is missing"+"<br/>";
		isFilledOutCorrect = false;
		reservationidWrong = true;
	} else {
		if(!Helper.isInteger(inputReservationId)){
			errorMessage += "Booking reference must be from type Integer"+"<br/>";
			isFilledOutCorrect = false;
		}
	}
	if(inputLastname.isEmpty() || inputLastname.trim().equals("") )
	{
		errorMessage += "Last name is missing"+"<br/>";
		isFilledOutCorrect = false;
		lastNameWrong = true;
	}
	if(isFilledOutCorrect){
		
		List<Reservation> reservations = dbConnection.getReservations(inputReservationId, null, null, null, null, null);
		if(reservations != null){
			if(!reservations.isEmpty()){
				reservationIdFound = true;
				reservation = reservations.get(0);
				
				guest = dbConnection.getGuests(Integer.toString(reservation.getBookingNumber()), null, null, null, null, null, null, null).get(0);
				
				if(guest.getLastName().equals(inputLastname)){
					lastnameFound = true;
				} else {
					resultMessage2 = "Last Name doesn't match to reservation";
				}
				
			} else {
				resultMessage = "Can't find Booking reference."+"<br/>";
			}
		} else {
			resultMessage = "Can't find Booking reference."+"<br/>";
		}
		
		isSubmitted = true;
		
	}
	
	if(reservationIdFound==true && lastnameFound==true) { 
		resultFound =true;
			dbConnection.deleteReservations(Integer.toString(reservation.getBookingNumber()), null, null, null, null, null);
		}
}
%>

<%if(isSubmitted==false ){ %>
<h1>Book Cancellation</h1>
<form id="onlinecancellationform" name="onlinecancellationform" action="<%=request.getRequestURI()%>" method="post">
<table>
		<tr>
			<td><b>Please fill it up to access your booking. </b></td>
			<td></td>
		</tr>
		<tr>
			<td>Booking reference </td>
			<td>	
				<input <%if(reservationidWrong)
						{ %>class="emptyTextField" <%} %> type="text" name="inputReservationId" placeholder="Booking reference" size="30" <%if(inputReservationId!=null){ %>value="<%=inputReservationId%>"/<%} %>>
			</td>
		</tr>
		<tr>
			<td>Last Name </td>
			<td>	
				<input <%if(lastNameWrong)
				{ %>class="emptyTextField" <%} %> type="text" name="inputlastname" placeholder="Last name" size="30" <%if(inputLastname!=null){ %>value="<%=inputLastname %>" <%} %>/>
			</td>
		</tr>
		<tr>
		<tr>
			<td><input type="submit" value="Find Booking"/></td>
		</tr>
</table>
</form>
<p class="loginError"><%=errorMessage %></p>
<p class="loginError"><%=resultMessage %><%=resultMessage2%></p>
<%} else if(resultFound) { 
	List<Room> reservedRooms = reservation.getRooms();
%>

<h1>Your Bookings</h1>

<p><b>Booking reference</b></p>  <%=inputReservationId %>
<p><b>Last name</b></p><%=inputLastname %>
<p><b>Room number </b></p>
<% for(Room reservedRoom : reservedRooms){ %>	
 <p><%=reservedRoom.getRoomNumber() %></p>
<%}%>

<p></p><b>The booking is canceled. We hope to see you again!</b>
<%} else { %>
<h1>Book Cancellation</h1>
<form id="onlinecancellationform" name="onlinecancellationform" action="<%=request.getRequestURI()%>" method="post">
<table>
		<tr>
			<td><b>Please fill it up to access your booking. </b></td>
			<td></td>
		</tr>
		<tr>
			<td>Booking reference </td>
			<td>	
				<input <%if(reservationidWrong)
						{ %>class="emptyTextField" <%} %> type="text" name="inputReservationId" placeholder="1" size="20" <%if(inputReservationId!=null){ %>value="<%=inputReservationId%>"/<%} %>>
			</td>
		</tr>
		<tr>
			<td>Last Name </td>
			<td>	
				<input <%if(lastNameWrong)
				{ %>class="emptyTextField" <%} %> type="text" name="inputlastname" placeholder="Mustermann" size="20" <%if(inputLastname!=null){ %>value="<%=inputLastname %>" <%} %>/>
			</td>
		</tr>
		<tr>
		<tr>
			<td><input type="submit" value="Find Booking"/></td>
		</tr>
</table>
</form>
<p class="loginError"><%=resultMessage %><%=resultMessage2%></p>
<b>We are unable to find this confirmation number. Please validate your entry and try again or contact us for further information.</b>
<%} %>


<%@ include file="navigationSlideGuestFooter.jsp" %>