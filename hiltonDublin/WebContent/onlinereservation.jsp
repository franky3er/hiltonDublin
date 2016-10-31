<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<script language = "javascript">

//checking info is correct
	function checkinfo()
	{
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String phonenr = request.getParameter("phonenr");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String passportnr = request.getParameter("passportnr");
		
		ResultSet Guestresult = dbConnection.insertGuest(null, firstname, lastname, phonenr, email, address, passportnr);
		
		check_guestinfo.submit();
	}
</script>


<h1>Online Reservation</h1>

<form id="check_guestinfo" action="onlinereservationsh.jsp" method="post">
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
	<td colspan="2" align="center">
		<input type="submit" value="Submit">
		<input type="reset" value="Cancel">
	</td>
</form>

<%@ include file="navigationSlideGuestFooter.jsp" %>