
<%@ include file="navigationSlideEmployeeHeader.jsp" %>
<%@page import="com.hiltondublin.classes.Room"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.Iterator" %>

<%
String checkoutError = (String) request.getAttribute("checkoutError");
String roomNumber = (String) request.getAttribute("roomNumber");
String totalPrice = (String) request.getAttribute("totalPrice");
Map<String, Double> bill = (Map<String, Double>) request.getAttribute("bill");
List<Room> occupiedRooms = (ArrayList<Room>) request.getAttribute("occupiedRooms");
if(checkoutError == null){checkoutError = "0";}

System.out.println("Total price: " + totalPrice);
%>

<h1>Checkout</h1>
<form id="checkoutForm" action="<%=request.getContextPath() %>/Employee/Checkout-room" method="post">
	<label>Room Number: 
		<input type="text" name="roomNumber" placeholder="Number" maxlength="3" size="3"/>
		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	</label>
	
	<input type="submit" />
	<%if(checkoutError.equals("1")){ %>
	<p class="loginError">Room number <%=roomNumber %> was not an Integer!</p>
	<%} else if (checkoutError.equals("2")){%>
	<p class="loginError">Room number null</p>
	<%} else if (checkoutError.equals("4")){%>
	<p class="loginError">No suitable reservation found for room number <%=roomNumber %></p>
	<%} %>
</form>

<%if(totalPrice!=null){ %>
<h4>Bill</h4>

<table>
	<%
	Iterator entries = bill.entrySet().iterator();
	while(entries.hasNext()){
		Entry entry = (Entry) entries.next();
		String product = (String) entry.getKey();
		Double price = (Double) entry.getValue();
	%>
	<tr>
		<td><%=product %></td>
		<td><%=price %></td>
	</tr>
	<%} %>
	<tr>
		<td></td>
		<td><%=totalPrice %></td>
	</tr>
</table>
<%} %>

<%@ include file="navigationSlideEmployeeFooter.jsp" %>