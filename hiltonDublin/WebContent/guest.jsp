<%@ include file="navigationSlideGuestHeader.jsp" %>
<body>
	<h1>Welcome to Guest Page</h1>
		<div>
		<form action="onlinereservation.html" method="get">
			<input class="navigationPage" type="submit" value="Reservation" />
		</form>
		<form action="cancellation.html" method="get">
			<input class="navigationPage" type="submit" value="Canccellation" />
		</form>
		<form action="rating.html" method="get">
			<input class="navigationPage" type="submit" value="Rating" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideGuestFooter.jsp" %>
</html>