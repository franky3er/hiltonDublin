
<%@ include file="navigationSlideEmployeeHeader.jsp" %>
<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.Iterator" %>

<%
Reservation reservation = (Reservation) request.getAttribute("reservation");
String checkoutInfo = (String) request.getAttribute("checkoutInfo");
String checkoutError = (String) request.getAttribute("checkoutError");
String roomNumber = (String) request.getAttribute("roomNumber");
String totalPrice = (String) request.getAttribute("totalPrice");
Map<String, Double> bill = (Map<String, Double>) request.getAttribute("bill");
List<Room> occupiedRooms = (ArrayList<Room>) request.getAttribute("occupiedRooms");
if(checkoutError == null){checkoutError = "0";}
if(checkoutInfo == null){checkoutInfo = "0";}

System.out.println("Total price: " + totalPrice);
%>

<h1><%=language.employeeCheckoutHeading() %></h1>

<%if(reservation==null){ %>
<form id="checkoutForm" action="<%=request.getContextPath() %>/Employee/Checkout-room" method="post">
	<label><%=language.employeeCheckoutRoomNumber() %>
		<input type="text" name="roomNumber" placeholder="Number" maxlength="3" size="3"/>
		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	</label>
	
	<input type="submit" name="checkout" value="<%=language.employeeCheckoutButton()%>"/>
	<%if(checkoutError.equals("1")){ %>
	<p class="error"><%=language.employeeCheckoutErrorRoomNumberNotInteger(roomNumber) %></p>
	<%} else if (checkoutError.equals("2")){%>
	<p class="error"><%=language.employeeCheckoutErrorRoomNumberNull() %></p>
	<%} else if (checkoutError.equals("3")){%>
	<p class="error"><%=language.employeeCheckoutErrorRoomNumberEmpty() %></p>
	<%} else if (checkoutError.equals("4")){%>
	<p class="error"><%=language.employeeCheckoutErrorNoSuitableReservation(roomNumber) %></p>
	<%} else if (checkoutError.equals("5")){%>
	<p class="error"><%=language.employeeCheckoutErrorAllreadyCheckedOut(roomNumber) %></p>
	<%} if(checkoutInfo.equals("1")){ %>
	<p class="informational"><%=language.employeeCheckoutSuccessfullyCheckedOut(roomNumber) %></p>
		<%if(occupiedRooms==null){ %>
	<p class="informational"><%=language.employeeCheckoutAllRoomsCheckedOut() %></p>
		<%} else {%>
	<p class="error"><%=language.employeeCheckoutOccupiedRooms(occupiedRooms) %></p>
		<%} %>
	<%} %>
</form>

<%} else { %>
<h3><%=language.employeeCheckoutVerificationHeading() %></h3>
<form action="<%=request.getContextPath() %>/Employee/Checkout-room-verified" method="post">
	<table>
		<tr>
			<td><%=language.employeeCheckoutVerificationQuestion(reservation.getGuest().getFirstName(), reservation.getGuest().getLastName()) %></td>
			<td><input type="submit" value="<%=language.yes() %>"/></td>
			<td><a href="<%=getURLWithContextPath(request) %>/Employee/Checkout"><input type="button" value="<%=language.no() %>"/></a></td>
		</tr>
	</table>
	<input type="hidden" name="reservationID" value="<%=Integer.toString(reservation.getBookingNumber()) %>"/>
	<input type="hidden" name="roomNumber" value="<%=roomNumber %>"/> 
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>
<%} %>

<%if(totalPrice!=null){ %>
<br/><br/>
<h4 style="margin:0;"><%=language.employeeCheckoutBillHeadline() %></h4>

<table style="border:1px solid black;border-collapse:collapse;">
	<%
	Iterator entries = bill.entrySet().iterator();
	while(entries.hasNext()){
		Entry entry = (Entry) entries.next();
		String product = (String) entry.getKey();
		Double price = (Double) entry.getValue();
	%>
	<tr>
		<td style="border:1px solid black;"><%=product %></td>
		<td align="right" style="border:1px solid black;">  <%=price %></td>
	</tr>
	<%} %>
	<tr>
		<td style="border:1px solid black;"><b><%=language.employeeCheckoutBillTotal() %></b></td>
		<td align="right" style="border:1px solid black;">  <b><%=totalPrice %></b></td>
	</tr>
</table>

<%} %>

<%@ include file="navigationSlideEmployeeFooter.jsp" %>