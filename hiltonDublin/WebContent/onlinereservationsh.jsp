<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
	//guestID
	String guestID = (String)session.getAttribute("guestID");
	
	//room info
	String checkin = request.getParameter("checkin");
	String checkout = request.getParameter("checkout");
	String numberofguest = request.getParameter("numberOfGuests");
	String smoking = request.getParameter("smoking");
	
	String roomNumber = null;
	String typeID = null;
	String occupied = "false";
	
	int roomtype1 = Integer.parseInt(request.getParameter("numtype1"));
	int roomtype2 = Integer.parseInt(request.getParameter("numtype2"));
	int roomtype3 = Integer.parseInt(request.getParameter("numtype3"));
	
	String additionalSQLCondition = null;
	
	List<String> roomsAsStrings = new ArrayList<String>();
	List<String> roomNumbers = new ArrayList<String>();
	List<Room> roomtype1s = new ArrayList<Room>();
	List<Room> roomtype2s = new ArrayList<Room>();
	List<Room> roomtype3s = new ArrayList<Room>();
	
	if(roomtype1 != 0) {
		roomtype1s = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, additionalSQLCondition);
	}
	if(roomtype2 != 0) {
		roomtype2s = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, additionalSQLCondition);
	}
	if(roomtype3 != 0) {
		roomtype3s = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, additionalSQLCondition);
	}
	
	for(Room room : roomtype1s){
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
		boolean request = dbConnection.insertReservation(reservationID, guestID, checkin, checkout, smoking);
		
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
	
	
	<fieldset>
		<legend>Type1</legend>
	
	</fieldset>
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
