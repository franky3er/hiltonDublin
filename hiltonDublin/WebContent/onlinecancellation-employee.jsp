<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.users.Guest" %>
<%@page import="com.hiltondublin.classes.ConsumerProduct" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import= "java.sql.ResultSet" %>
<%@ include file="navigationSlideEmployeeHeader.jsp" %>

<%
/*Get Bookings data*/
String reservationID = null;
String guestID = null;
String arrivalDate = null;
String departureDate = null;
String paid = null;
String additionalSQLCondition = null;	
List<String> reservationAsStrings = new ArrayList<String>();
List<Reservation> reservations = dbConnection.getReservations(reservationID, guestID, arrivalDate, departureDate, paid, additionalSQLCondition);
 System.out.println("reservations.size() : "+reservations.size());
 
for(Reservation reservation : reservations)
{
	//reservationAsStrings.add("Booking Number: "+reservation.getBookingNumber()+"  guestID: "+reservation.getGuestID());
	System.out.println("Booking Number: "+reservation.getBookingNumber()+"  guestID: "+reservation.getGuestID());
	
}  	 

%>

<%
boolean inputSearch = false;
String inputForSearch = request.getParameter("inputForSearch");

if(inputForSearch!=null)
{
	
}
%>

<h1>Cancel Booking</h1>
<select  value = "list" name = "list">
	<option value = "list_ReservationID">Reservation ID</option>
	<option value = "list_GuestID">Guest ID </option>
	<option value = "list_GuestName">Guest Name </option>
	<option value = "list_RoomNumber">Room Number</option>	
</select>	
<input type="text" name="inputForSearch" placeholder="Booking reference" size="30" value="<%=inputForSearch%>"/>
<input type="submit" value="Find Booking"/>
<input type="submit" value="Cancellation"/>				
<table border="1px" cellsapcing="" cellpadding="4">
<tr>
		<!-- 1 haeng -->
		<th>Select</th>
		<th>Reservation ID</th>
		<th>Guest ID</th>
		<th>Arrival Date</th>
		<th>Departure Date</th>
	</tr>
<%
for(Reservation reservation : reservations)
{
%>	
	<tr>
		<!-- N haeng -->
		<th><input type="checkbox" name = "selete" value ="seleted" /></th>
		<th><%=reservation.getBookingNumber()%></th>
		<th><%=reservation.getGuestID() %></th>
		<th><%=reservation.getArrivalDate() %></th>
		<th><%=reservation.getDepartureDate() %></th>
	</tr>
	
<%
}
%>	
</table>
<%@ include file="navigationSlideEmployeeFooter.jsp" %>