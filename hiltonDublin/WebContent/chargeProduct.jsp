<%@page import="com.hiltondublin.classes.Reservation"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="com.hiltondublin.classes.ConsumerProduct"%>
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>

<%@ include file="navigationSlideEmployeeHeader.jsp" %>

<%
	boolean isSubmitted = false;
	String conProductname = request.getParameter("consumerproducts");
	String conPrice = request.getParameter("price");
	String productID = request.getParameter("consumerproducts");
	String roomno = request.getParameter("roomno");
	int chargeProductError = 0;

	List<ConsumerProduct> consumerProducts = dbConnection.getConsumerProducts(null,null,null, null);
	Reservation reservation = null;
	int reservationid;
	if(roomno != null ){
		if(roomno.isEmpty() || roomno.trim().equals("")){
			chargeProductError = 1; //No room number typed in
		} else {
			if(!roomno.matches("\\d+")){
				chargeProductError = 2; //Room number no integer
			} else {
				reservation = dbConnection.getReservationFromRoomNumber(roomno);
				if(reservation == null){
					chargeProductError = 3; //No reservation found for room number
				} else {
					reservationid = reservation.getBookingNumber();
					dbConnection.assignProductToReservation(null, productID, Integer.toString(reservationid));	
					isSubmitted = true;
				}
			}
		}
	}
	
%>
<h1><%=language.employeeChargeProductHeading() %></h1>
<form id="productForm" name="productForm" action="<%=request.getRequestURI()%>" method="post">
	<table>
		<tr>
		<td><%=language.employeeCheckoutRoomNumber() %> </td>
		<td>
		<input type="text" name="roomno" size="3" maxlength="3" <%if(roomno != null){ %>value="<%=roomno %>"/<%} %>>
		</td>
		</tr>
		<tr>
		<td><%=language.employeeChargeProductProduct() %> </td>
		<td>
			<select name="consumerproducts">
				<%for(ConsumerProduct conProducts: consumerProducts){ %>
				<option value="<%=conProducts.getProductID()%>"><%=conProducts.getName()%></option>
				<%} %>
			</select>
		</td>
		</tr>
		<tr>
			<td><input type="submit" value="<%=language.employeeChargeProductCharge() %>" /></td>
		</tr>
		<tr></tr>
		<tr></tr>
	</table>
	<%if(chargeProductError==1){ %>
	<p class="error"><%=language.employeeCheckoutErrorRoomNumberEmpty() %></p>
	<%} %>
	<%if(chargeProductError==2){ %>
	<p class="error"><%=language.employeeCheckoutErrorRoomNumberNotInteger(roomno) %></p>
	<%} %>
	<%if(chargeProductError==3){ %>
	<p class="error"><%=language.employeeCheckoutErrorNoSuitableReservation(roomno) %></p>
	<%} %>
	<%if(isSubmitted){ %>
	<p class="informational"><%=language.employeeChargeProductSuccessfully() %></p>
	<%} %>
</form>

<%@ include file="navigationSlideEmployeeFooter.jsp" %>