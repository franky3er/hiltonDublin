<%@ include file="navigationSlideAdminHeader.jsp" %>

<%

Calendar currentDate = Calendar.getInstance();
currentDate.setTime(new Date());

Calendar tomorrowDate = Calendar.getInstance();
tomorrowDate.setTime(new Date());
tomorrowDate.add(Calendar.DAY_OF_MONTH, 1);


%>

<h1>Modify Reservation</h1>

<form action="" method="get">
	<table>
		<tr>
			<td>Booking Number: </td>
			<td><input type="text" name="bookingNumber"> </td>
		</tr>
		<tr>
			<td>Guest First Name: </td>
			<td><input type="text" name="firstName"> </td>
		</tr>
		<tr>
			<td>Guest Last Name: </td>
			<td><input type="text" name="lastName"> </td>
		</tr>
		<tr>
			<td>Arrival Date: </td>
			<td><input type="date" name="arrivalDate" min="<%=dbConnection.onlyDayDateFormat.format(currentDate.getTime()) %>" required> </td>
		</tr>
		<tr>
			<td>Departure Date: </td>
			<td><input type="date"  name="departureDate" min="<%=dbConnection.onlyDayDateFormat.format(tomorrowDate.getTime()) %>" required> </td>
		</tr>
		<tr>
			<td><input type="submit" value="search for Reservation"/> </td>
			<td> </td>
		</tr>
	</table>
</form>

<%@ include file="navigationSlideAdminFooter.jsp" %>