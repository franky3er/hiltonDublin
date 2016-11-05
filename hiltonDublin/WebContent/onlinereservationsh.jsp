<%@page import="com.hiltondublin.users.GuestSingleton"%>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<h1>Online Reservation</h1>

<form id="roomsh" action="Showroom" method="post">
	<%
		List<Room> roomtype1 = new ArrayList<Room>();
		List<Room> roomtype2 = new ArrayList<Room>();
		List<Room> roomtype3 = new ArrayList<Room>();
		GuestSingleton guestinfo = GuestSingleton.getInstatnce();
	
		roomtype1 = dbConnection.getRooms(null, "1", guestinfo.getSmoking(), null, null);
		roomtype2 = dbConnection.getRooms(null, "2", guestinfo.getSmoking(), null, null);
		roomtype3 = dbConnection.getRooms(null, "3", guestinfo.getSmoking(), null, null);
	%>
	<fieldset>
		<legend>Type1</legend>
		<%
		for(Room rooms : roomtype1) {%>
		<input type="checkbox" id=rooms.getRoomNumber() name="type1" value=<%=rooms.getRoomNumber()%>>
		<label for=<%=rooms.getRoomNumber()%>><%=rooms.getRoomNumber()%></label><br>
		<%} %>
	</fieldset>
	<fieldset>
		<legend>Type2</legend>
		<%
		for(Room rooms : roomtype2) {%>
		<input type="checkbox" id=rooms.getRoomNumber() name="type2" value=<%=rooms.getRoomNumber()%>>
		<label for=<%=rooms.getRoomNumber()%>><%=rooms.getRoomNumber()%></label><br>
		<%} %>
	</fieldset>
	<fieldset>
		<legend>Type3</legend>
		<%
		for(Room rooms : roomtype3) {%>
		<input type="checkbox" id=rooms.getRoomNumber() name="type3" value=<%=rooms.getRoomNumber()%>>
		<label for=<%=rooms.getRoomNumber()%>><%=rooms.getRoomNumber()%></label><br>
		<%} %>
	</fieldset>
	<input type="submit" value="Submit">

</form>



<%@ include file="navigationSlideGuestFooter.jsp" %>
