<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.jdbc.exceptions.MySQLDataException"%>
<%@page import="com.hiltondublin.classes.Rating" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<% 
			String ratingID = "6";				
			//String guestID =  user.getGuestID();		
			String typeid = request.getParameter("roomtype");
			String comment = request.getParameter("comment");
			String rating = request.getParameter("rating");
				
// 			Rating rate = new Rating();
// 			rate.setRatingID(ratingID);
// 			rate.setTypeID(Integer.parseInt(typeid));
// 			rate.setGuestID(guestID);
// 			rate.setRating(Integer.parseInt(rating));
// 			rate.setComment(comment);
 			ResultSet result = dbConnection.insertRating(ratingID,typeid, null, rating,comment);
			
			%>	
	 		
<script language = "javascript">
	var ratingSubmitted = document.getElementById('ratingSubmitted');
	ratingSubmitted.hide();
	
	function insert(){
		int ratingID = '6';
		int guestID =  user.getGuestID();		
		String typeid = request.getParameter("roomtype");
		String comment = request.getParameter("comment");
		String rating = request.getParameter("rating");
		
		ResultSet result = dbConnection.insertRating(ratingID, typeid, guestID, rating, comment);
		System.out.println(result);
		ratingform.submit();
		ratingform.hide();
		ratingSubmitted.show();
		
	}
</script>

<form id="ratingform" name="ratingform" action="ratingSubmitted.jsp" method="post">
	<table>
		<tr>
			<th colspan="2">Your Review</th>
		</tr>
		<tr>
			<td>Your room Type: </td>
			<td>	
				<select name="roomtype">
					<option value="1">Single</option>
					<option value="2">Double</option>
					<option value="3">Twin</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Your rating</td>
			<td>
				<select name="rating">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Comment: </td>
			<td>
				<input type="text" id="comment" name="comment">
			</td>
		</tr>
		<tr>
			<td><input type="submit" onclick="insert()" value="submit" /></td>
		</tr>
	</table>
	</form>
	
	<%@ include file="navigationSlideGuestFooter.jsp" %>
