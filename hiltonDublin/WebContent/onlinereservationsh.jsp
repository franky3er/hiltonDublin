<%@page import="com.hiltondublin.classes.Reservation"%>
<%@page import="com.hiltondublin.users.GuestSingleton"%>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<h1>Online Reservation</h1>

<form id="roomsh" action="Showroom" method="post">
	<%
		List<Room> roomtype1 = new ArrayList<Room>();
		List<Room> roomtype2 = new ArrayList<Room>();
		List<Room> roomtype3 = new ArrayList<Room>();
	
		Guest guest = (Guest) request.getAttribute("guest");
		Room room = (Room) request.getAttribute("room");
		Reservation reservation = (Reservation) request.getAttribute("reservation");
		
		int[] Type = (int[]) request.getAttribute("Type");
		
		if(Type[0] != 0) {
			roomtype1 = dbConnection.getAvailableRooms(1, Type[0], reservation.getArrivalDate(), reservation.getDepartureDate(), room.isSmoking());
			%>
			<fieldset>
			<legend>Single</legend>
			<%
			if(roomtype1 != null) {
				%><input type="hidden" name="type1" value="exist"><%
				for(Room rooms : roomtype1) {%>
				<input type="checkbox" id=rooms.getRoomNumber() name="type1list" value=<%=rooms.getRoomNumber()%>>
				<label for=<%=rooms.getRoomNumber()%>><%=rooms.getRoomNumber()%></label><br>
				<%}
			}
			else {%>
				<input type="hidden" name="type1" value="null">
				<label for="type1">There is not available single room.</label>
			<%}	%>
		</fieldset> <%
		}
		else {
			%><input type="hidden" name="type1" value="null"><%
		}
		
		
		
		if(Type[1] != 0) {
			roomtype2 = dbConnection.getAvailableRooms(2, Type[1], reservation.getArrivalDate(), reservation.getDepartureDate(), room.isSmoking());
			%>
			<fieldset>
			<legend>Double</legend>
			<%
			if(roomtype2 != null) {
				%><input type="hidden" name="type2" value="exist"><%
				for(Room rooms : roomtype2) {%>
				<input type="checkbox" id=rooms.getRoomNumber() name="type2list" value=<%=rooms.getRoomNumber()%>>
				<label for=<%=rooms.getRoomNumber()%>><%=rooms.getRoomNumber()%></label><br>
				<%}
			}
			else {%>
				<input type="hidden" name="type2" value="null">
				<label for="type2">There is not available double room.</label>
			<%} %>
		</fieldset> <%
		}
		else {
			%><input type="hidden" name="type2" value="null"><%
		}
		
		
		if(Type[2] != 0) {
			roomtype3 = dbConnection.getAvailableRooms(3, Type[2], reservation.getArrivalDate(), reservation.getDepartureDate(), room.isSmoking());
			%>
			<fieldset>
			<legend>Triple</legend>
			<%
			if(roomtype3 != null) {
				%><input type="hidden" name="type3" value="exist"><%
				for(Room rooms : roomtype3) {%>
				<input type="checkbox" id=rooms.getRoomNumber() name="type3list" value=<%=rooms.getRoomNumber()%>>
				<label for=<%=rooms.getRoomNumber()%>><%=rooms.getRoomNumber()%></label><br>
				<%}
			}
			else {%>
				<input type="hidden" name="type3" value="null">
				<label for="type3">There is not available double room.</label> <%
			}%>
		</fieldset><%
		}
		else {
			%><input type="hidden" name="type3" value="null"><%
		}
		
		if(roomtype1 != null || roomtype2 != null || roomtype3 != null)
		{%>
			 <input type="submit" value="Submit">
		<%}
	%>
	<input type="button" value="Back" onclick="location='onlinereservation.jsp'">
</form>



<%@ include file="navigationSlideGuestFooter.jsp" %>
