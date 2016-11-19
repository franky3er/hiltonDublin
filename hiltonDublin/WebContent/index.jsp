<%@page import="java.util.List"%>
<%@page import="com.hiltondublin.classes.Rating" %>
<%@page import="com.hiltondublin.helper.Helper" %>
<%@page import="java.sql.ResultSet" %>

<%@ include file="navigationSlideGuestHeader.jsp" %>

<h1><%=language.homeWelcome() %></h1>

<%

List<Rating> ratings = dbConnection.getRatings(null, null, null, null, null, dbConnection.RATING_RATING + ">='4'" + " ORDER BY RAND() LIMIT 5");

double averageRating = 0;

ResultSet rs = dbConnection.executeQueryAndReturnResultSet("SELECT AVG(" + dbConnection.RATING_RATING + ") FROM " + dbConnection.RATING + "; ");

if(rs != null){
	if(rs.next()){
		averageRating = rs.getDouble(1);
	}
}

%>

<img src="${pageContext.request.contextPath}/resources/Pictures/hilton.jpg" alt="Hilton" title="Hilton" width="450" height="294" />

<p><%=language.homeWelcomeText() %></p>

<br/>

<div id="plaintextNavigationArea">
		<form action="<%=getURLWithContextPath(request) %>/Guest/Online-Reservation" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.homeBookNow() %>" />
		</form>
	</div>

<%if(ratings != null){ %>

	<h3 style="margin-bottom:0;"><%=language.guestRatingReviewsOurGuests() %> </h3>
	
	<div style="display:block; margin:0;">
		<div class="rating-box">
			<div style="width: <%=(int) Math.round((averageRating / 5.0)*100) %>%" class="ratingdisplay">
			</div>
		</div>
	</div>
	
	<p style="display:block; margin:0;"><%=averageRating %> <%=language.guestRatingOverall() %></p>
	
	<br/>
	
	<%for(Rating rating : ratings){ %>
	
		<table style="width:100%;" border="1">
			<col width="20%" />
	    	<col width="80%" />
			<tr>
				<td></td>
				<td>
					<div class="rating-box">
						<div style="width: <%=(int) Math.round((rating.getRating() / 5.0)*100) %>%" class="ratingdisplay">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td><%=language.guestRatingRoomTypeSimple() %></td>
				<td><%=dbConnection.getRoomTypes(Integer.toString(rating.getTypeID()), null, null, null, null, null).get(0).getName() %></td>
			</tr>
			<tr>
				<td><%=language.guestRatingCommentSimple() %></td>
				<td><%=rating.getComment() %></td>
			</tr>
		</table>
		
		<br/>
		<br/>
		
	<%} %>

<%} %>

<%@ include file="navigationSlideGuestFooter.jsp" %>