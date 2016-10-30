<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.classes.Rating" %>
<%@ page import = "java.util.*"%>
<%@ page import = "java.lang.Integer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ include file="navigationSlideGuestHeader.jsp" %>	

<form name="ratingform" action="rating.jsp" method="post">
	<table>
		<tr>
			<th colspan="2">Your Review</th>
		</tr>
		<tr>
			<td>Your rating</td>
			<td>
				<select id="rating" name="rating">
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
			<td><input type="submit" onclick="submitRating()" value="submit" /></td>
		</tr>
	</table>
	</form>
<%
		String comment = request.getParameter("comment");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int typeID = '3';
		int ratingID = '1';
		int guestID = '1';
		
		
		Rating rate = new Rating();
		rate.setRatingID(ratingID);
		rate.setTypeID(typeID);
		rate.setGuestID(guestID);
		rate.setRating(rating);
		rate.setComment(comment);
		
		String typeid = String.valueOf(typeID);
		String ratingid = String.valueOf(ratingID);
		String guestid = String.valueOf(guestID);
		String rating = String.valueOf(rate);
		
		try{		dbConnection.insertRating(rate);
		}catch (Exception e){
            out.println("An exception occurred: " + e.getMessage());
        }
%>	
	<%@ include file="navigationSlideGuestFooter.jsp" %>
