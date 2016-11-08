<%@page import="com.hiltondublin.classes.Reservation"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="com.hiltondublin.classes.ConsumerProduct"%>
<%@ include file="navigationSlideGuestHeader.jsp" %>
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>

<%
	boolean isSubmitted = false;
	String conProductname = request.getParameter("consumerproducts");
	String conPrice = request.getParameter("price");
	String productID = request.getParameter("consumerproducts");
	String roomno = request.getParameter("roomno");

	List<ConsumerProduct> consumerProducts = dbConnection.getConsumerProducts(null,null,null, null);
	
		Reservation reservation = dbConnection.getReservationFromRoomNumber(roomno);
		int reservationid = reservation.getBookingNumber();	
		dbConnection.assignProductToReservation(productID, Integer.toString(reservationid));	
		isSubmitted = true;
	
%>
<%if(!isSubmitted){ %>
<form id="productForm" name="productForm" action="<%=request.getRequestURI()%>" method="post">
	<table>
		<tr>
		<td>Room No: </td>
		<td>
		<input type="text" name="roomno" placeholder="Max" size="20" <%if(roomno != null){ %>value="<%=roomno %>"/<%} %>>
		</td>
		</tr>
		<tr>
		<td>Choose: </td>
		<td>
			<select name="consumerproducts">
				<%for(ConsumerProduct conProducts: consumerProducts){ %>
				<option value="<%=conProducts.getProductID()%>"><%=conProducts.getName()%></option>
				<%} %>
			</select>
		</td>
		</tr>
		<tr>
			<td><input type="submit" value="submit" /></td>
		</tr>
		<tr></tr>
		<tr></tr>
	</table>
</form>
<%isSubmitted= true;} %>

<%@ include file="navigationSlideGuestFooter.jsp" %>