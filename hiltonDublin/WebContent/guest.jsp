<%@ include file="navigationSlideGuestHeader.jsp" %>
<body>
	<h1><%=language.guestWelcome() %></h1>
	<p>This is the main page for the Guests.<br>
	Guests can make online reservations, cancel reservations, and check the ratings.
	</p>
	<div id="plaintextNavigationArea">
		<form action="<%=getURLWithContextPath(request) %>/Guest/Online-Reservation" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="Reservation" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest/Cancellation" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="Cancellation" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Guest/Rating" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.guestRatingRating() %>" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideGuestFooter.jsp" %>