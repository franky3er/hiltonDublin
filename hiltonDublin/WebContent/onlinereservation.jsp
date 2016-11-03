<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<script language = "javascript">

//checking info is correct
	function checkinfo()
	{
		Guest guest = new Guest();

		guest.setLastName(request.getParameter("lastname"));
		guest.setFirstName(request.getParameter("firstname"));
		guest.setPhoneNumber(request.getParameter("phonenr"));
		guest.setEmail(request.getParameter("email"));
		guest.setAddress(request.getParameter("address"));
		guest.setPassportNr(request.getParameter("passportnr"));
		
		int roomtype1 = Integer.parseInt(request.getParameter("numtype1"));
		int roomtype2 = Integer.parseInt(request.getParameter("numtype2"));
		int roomtype3 = Integer.parseInt(request.getParameter("numtype3"));
		
		if(roomtype1 + roomtype2 + roomtype3 > 1)
		{
			ResultSet key = dbConnection.insertGuest(guest);
			
			int guestID = 0;
			if(key.next()) {
				guestID = key.getInt(1);
				
				session.setAttribute("guestID", Integer.toString(guestID));
			}
			
			check_guestinfo.submit();
		}
		else
		{
			check_guestinfo.reset();
		}
		
	}
</script>


<h1>Online Reservation</h1>

<form id="check_guestinfo" action="onlinereservationsh.jsp" method="get">
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
		<td><input type="date" id="checkin" name="checkin" min="2016-11-01" required></td>
		<td><input type="date" id="checkout" name="checkout" min="2016-11-02" required></td>
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
		<td>Type 1</td>
		<td>Type 2</td>
		<td>Type 3</td>
		</tr>
		<tr>
		<td><input type="number" id="numtype1" name="numtype1" min="0" required></td>
		<td><input type="number" id="numtype2" name="numtype2" min="0" required></td>
		<td><input type="number" id="numtype3" name="numtype3" min="0" required></td>
		</tr>
		</table>
	</fieldset>
	<td colspan="2" align="center">
		<input type="submit" value="Submit">
		<input type="reset" value="Cancel">
	</td>
</form>

<%@ include file="navigationSlideGuestFooter.jsp" %>