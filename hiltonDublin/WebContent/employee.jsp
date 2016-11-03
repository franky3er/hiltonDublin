<%@ include file="navigationSlideEmployeeHeader.jsp" %>
<body>
	<h1>Welcome to Employee Page</h1>
	<p>This is the main page for the Employees.<br>
	Employees can make online reservations, cancel reservations, check in, check out and charge products.
	</p>
		<div>
		<form action="<%=getURLWithContextPath(request) %>/Guest/Online-Reservation" method="get">
			<input class="navigationPage" type="submit" value="Reservation" />
		</form>
		<form action="cancellation.jsp" method="get">
			<input class="navigationPage" type="submit" value="Cancellation" />
		</form>
		<form action="checkin.jsp" method="get">
			<input class="navigationPage" type="submit" value="Check-in" />
		</form>
		<form action="checkout.jsp" method="get">
			<input class="navigationPage" type="submit" value="Check-out" />
		</form>
		<form action="chargeproduct.jspl" method="get">
			<input class="navigationPage" type="submit" value="Charge Prodruct" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideEmployeeFooter.jsp" %>