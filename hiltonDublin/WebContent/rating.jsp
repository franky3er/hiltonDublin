<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="com.mysql.jdbc.exceptions.MySQLDataException"%>
<%@page import="com.hiltondublin.classes.Rating" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="navigationSlideGuestHeader.jsp" %>    

<%
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String email = request.getParameter("email");
String roomTypeID = request.getParameter("roomtype");
String rating = request.getParameter("rating");
String comment = request.getParameter("comment");

boolean isSubmitted = false;
boolean isFilledOutCorrect = true;
boolean firstNameWrong = false;
boolean lastNameWrong = false;
boolean emailWrong = false;
String errorMessage = "";
String roomTypeName = "";


if(roomTypeID!=null && rating!=null && comment!=null && firstName!=null && lastName!=null && email!=null){
	if(firstName.isEmpty() || firstName.trim().equals("")){
		errorMessage += language.guestRatingErrorMessageFirstName() + "<br/>";
		isFilledOutCorrect = false;
		firstNameWrong = true;
	} 
	if (lastName.isEmpty() || lastName.trim().equals("")){
		errorMessage += language.guestRatingErrorMessageLastName() + "<br/>";
		isFilledOutCorrect = false;
		lastNameWrong = true;
	} 
	if (email.isEmpty() || email.trim().equals("")){
		errorMessage += language.guestRatingErrorMessageEmail() + "<br/>";
		isFilledOutCorrect = false;
		emailWrong = true;
	} 
	if(isFilledOutCorrect){
		ResultSet guestKeys = dbConnection.insertGuest(null, firstName, lastName, null, email, null, "-1");
		if(guestKeys != null){
			if(guestKeys.next()){
				String guestID = guestKeys.getString(1);
				dbConnection.insertRating(null, roomTypeID, guestID, rating, comment);
				isSubmitted = true;
				roomTypeName = dbConnection.getRoomTypes(roomTypeID, null, null, null, null, null).get(0).getName();
			}
		}
	}
}


List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);
%>

<h1><%=language.guestRatingReview() %></h1>
<%if(!isSubmitted){ %>
<form id="ratingform" name="ratingform" action="<%=request.getRequestURI()%>" method="post">
	<table>
		<tr>
			<td><b><%=language.guestRatingGuestInformation() %> </b></td>
			<td></td>
		</tr>
		<tr>
			<td><%=language.guestRatingFirstName() %> </td>
			<td>	
				<input <%if(firstNameWrong){ %>class="emptyTextField" <%} %> type="text" name="firstName" placeholder="Max" size="20" <%if(firstName!=null){ %>value="<%=firstName %>"/<%} %>>
			</td>
		</tr>
		<tr>
			<td><%=language.guestRatingLastName() %> </td>
			<td>	
				<input <%if(lastNameWrong){ %>class="emptyTextField" <%} %> type="text" name="lastName" placeholder="Mustermann" size="20" <%if(lastName!=null){ %>value="<%=lastName %>" <%} %>/>
			</td>
		</tr>
		<tr>
			<td><%=language.guestRatingEmail() %> </td>
			<td>	
				<input <%if(emailWrong){ %>class="emptyTextField" <%} %> type="text" name="email" placeholder="somebody@sth.com" size="20" <%if(email!=null){ %>value="<%=email %>" <%} %>/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><b><%=language.guestRatingRoomInformation() %> </b></td>
			<td></td>
		</tr>
		<tr>
			<td><%=language.guestRatingRoomType() %> </td>
			<td>	
				<select name="roomtype">
					<%int i = 0; for(RoomType roomType : roomTypes){ %>
					<option <%if(i==0){ %>selected<%} %> value="<%=roomType.getRoomTypeID() %>"><%=roomType.getName() %></option>
					<%i++;} %>
				</select>
			</td>
		</tr>
		<tr>
			<td><%=language.guestRatingRating() %>
				<p class="information"><%=language.guestRatingRatingBestGrade() %></p>
				<p class="information"><%=language.guestRatingRatingWorstGrade() %></p>
			</td>
			<td>
				<select name="rating">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option selected value="5">5</option>
				</select>
			</td>
		</tr>
		<tr>
			<td><%=language.guestRatingComment() %></td>
			<td>
				<textarea rows="10" cols="50" name="comment" placeholder="<%=language.guestRatingCommentDetail()%>" ></textarea>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="submit"/></td>
		</tr>
	</table>
</form>
<p class="loginError"><%=errorMessage %></p>
<%} else { %>
<p><%=language.guestRatingThanksForYourReview() %> <b><%=firstName %> <%=lastName %></b></p>
<p><%=language.guestRatingThanksRoomType() %> <b><%=roomTypeName %></b></p>
<p><%=language.guestRatingThanksRating() %> <b><%=rating %></b></p>
<p><%=language.guestRatingThanksComment() %> <b><%=comment %></b></p>
<%} %>
	
	<%@ include file="navigationSlideGuestFooter.jsp" %>
