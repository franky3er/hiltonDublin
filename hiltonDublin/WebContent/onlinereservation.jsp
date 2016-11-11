<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
	Calendar currentDay = Calendar.getInstance();
	currentDay.setTime(new Date());
	String currentDayAsString = dbConnection.onlyDayDateFormat.format(currentDay.getTime());
	
	Calendar tomorrowDay = Calendar.getInstance();
	tomorrowDay.setTime(new Date());
	tomorrowDay.add(Calendar.DAY_OF_MONTH, 1);
	String tomorrowDayAsString = dbConnection.onlyDayDateFormat.format(tomorrowDay.getTime());
	
	//TODO: Load Room Types Dynamically from database for selection
%>

<h1>Online Reservation</h1>

<form id="check_guestinfo" action="<%=request.getContextPath() %>/Guest/Online-Reservation-check" method="post">
	<fieldset>
		<legend>Guest Info</legend>
		<table>
			<tr>
				<td><label for="firstname">FIRST NAME</label></td>
				<td><input type="text" id="firstname" name="firstname" placeholder="Insert First Name" required></td>
			</tr>
			<tr>
				<td><label for="lastname">LAST NAME</label></td>
				<td><input type="text" id="lastname" name="lastname" placeholder="Insert Last Name" required></td>
			</tr>
			<tr>
				<td><label for="address">ADDRESS</label></td>
				<td><input type="text" id="address" name="address" placeholder="Insert Address" required></td>
			</tr>
			<tr>
				<td><label for="email">EMAIL</label></td>
				<td><input type="email" id="email" name="email" placeholder="ex) example@example.com" required></td>
			</tr>
			<tr>
				<td><label for="phonenr">PHONE NR.</label></td>
				<td><input type="tel" id="phonenr" name="phonenr" placeholder="Input just number" required></td>
			</tr>
			<tr>
				<td><label for="passportnr">PASSPORT NR.</label></td>
				<td><input type="text" id="passportnr" name="passportnr" placeholder="Insert PassportNumber" required></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<br>
	<fieldset>
		<legend>Booking Details</legend>
		<table>
		<tr>
		<td>Check In</td>
		<td>Check Out</td>
		<td>Person</td>
		<td>Smoking</td>
		</tr>
		<tr>
		<td><input type="date" id="checkin" name="checkin" value="<%=currentDayAsString %>" min="<%=currentDayAsString %>" required></td>
		<td><input type="date" id="checkout" name="checkout" value="<%=tomorrowDayAsString %>" min="<%=tomorrowDayAsString %>" required></td>
		<td><input type="number" id="numberOfGuests" name="numberOfGuests" min="1" max="6" required></td>
		<td>
		<input id="smoking" type="radio" name="smoking" value="true" checked>
		<label for="smoking">Yes</label>
		<input id="smoking" type="radio" name="smoking" value="false" checked>
		<label for="smoking">No</label>
		</td>
		</tr>
		</table>
	</fieldset>
	<br>
	<fieldset>
		<legend>Number of Room</legend>
		<table>
		<tr>
		<td>Single</td>
		<td>Double</td>
		<td>Triple</td>
		</tr>
		<tr>
		<td><input type="number" id="numtype1" name="numtype1" value="0" min="0" required></td>
		<td><input type="number" id="numtype2" name="numtype2" value="0" min="0" required></td>
		<td><input type="number" id="numtype3" name="numtype3" value="0" min="0" required></td>
		</tr>
		</table>
	</fieldset>
	<table>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="Submit">
			<input type="reset" value="Cancel">
			</td>
		</tr>
	</table>
</form>

<%@ include file="navigationSlideGuestFooter.jsp" %>