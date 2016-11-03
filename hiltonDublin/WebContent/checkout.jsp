
<%@ include file="navigationSlideEmployeeHeader.jsp" %>

<%
String checkoutError = (String) request.getAttribute("checkoutError");
String roomNumber = (String) request.getAttribute("roomNumber");
if(checkoutError == null){checkoutError = "0";}
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

<%@ include file="navigationSlideEmployeeFooter.jsp" %>