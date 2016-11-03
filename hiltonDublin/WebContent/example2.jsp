<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="navigationSlideGuestHeader.jsp" %>    
<%
	String roomNumber = null;
	String typeID = null;
	String smoking = "true";
	String occupied = "false";
	String additionalSQL = HiltonDublinDBConnection.ROOM_NUMBER + ">'200'";
	
	List<String> roomsAsStrings = new ArrayList<String>();
	
	List<Room> rooms = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, additionalSQL);
	for(Room room : rooms){
		roomsAsStrings.add("RoomNumber: " + room.getRoomNumber() + "   typeID: " + room.getTypeID() + "   smoking: " + room.isSmoking() + "   occupied: " + room.isOccupied());
	}
%>






<h1>Welcome to Hilton Dublin</h1>
<%
for(String room : roomsAsStrings){
%>	
<p> Room Number 1: <b><%=room %></b> </p>
<%
}
%>



<%@ include file="navigationSlideGuestFooter.jsp" %>