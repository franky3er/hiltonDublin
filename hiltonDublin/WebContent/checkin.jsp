<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="com.hiltondublin.classes.Reservation"%>
<%@ include file="navigationSlideEmployeeHeader.jsp" %>
<h1>Check In</h1>
<form name="checkinform" id="checkinform" action="get">
	<table>
		<tr>
			<td><input type="text" name="firstName"/></td>
		</tr>
		<tr>
			<td><input type="text" name="lastName"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="submit"/></td>
	</table>
</form>
<%
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
List<Reservation> reservation = dbConnection.getReservationsFromGuestName(firstName, lastName);
for(Reservation res: reservation) {%>
	Room: <% List<Room> room = res.getRooms();
}
%>
<%@ include file="navigationSlideEmployeeFooter.jsp" %>