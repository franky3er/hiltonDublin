<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
	//guest info
	String firstname = request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String address = request.getParameter("address");
	String email = request.getParameter("email");
	String phonenr = request.getParameter("phonenr");
	String passportnr = request.getParameter("passportnr");


	//room info
	String checkin = request.getParameter("checkin");
	String checkout = request.getParameter("checkout");
	String numberofguest = request.getParameter("numberOfGuests");
	String smoking = request.getParameter("smoking");
	
	String roomNumber = null;
	String typeID = null;
	String occupied = "false";
	//String additionalSQL = HiltonDublinDBConnection.ROOM_NUMBER + ">'200'";
	
	List<String> roomsAsStrings = new ArrayList<String>();
	List<String> roomNumbers = new ArrayList<String>();
	List<Room> rooms = new ArrayList<Room>();
	rooms = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, null);
	
	for(Room room : rooms){
		roomNumbers.add(String.valueOf(room.getRoomNumber()));
		roomsAsStrings.add("RoomNumber: " + room.getRoomNumber() + "   typeID: " + room.getTypeID() + "   smoking: " + room.isSmoking());
	}
%>

<script language="javascript">

//checking info is correct
	function reserve()
	{
		//reservationID = roomNumber+name guestID = name+passportnr
		roomNumber = request.getParameter("reserveroom");
		boolean request = dbConnection.insertReservation(roomNumber+name, name+passportnr, checkin, checkout, "true");
		
		if(request != "false")
		{
			roomsh.submit();
		}
		else
		{
			roomsh.reset();
		}
	}
</script>


<h1>Online Reservation</h1>

<form id="roomsh" action="payment.jsp" method="post">

	<%
	for(String room : roomsAsStrings){
	%>	
	<p>
		<input id="reserveroom" type="radio" name="reserveroom" value="room.substring(12,15)" checked>
		<b><%=room %></b>
	</p>
	<%
	}
	%>
	<input type="submit" value="Payment">

</form>



<%@ include file="navigationSlideGuestFooter.jsp" %>