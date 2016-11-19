<%@ include file="navigationSlideEmployeeHeader.jsp" %>
<body>
	<h1>Welcome to Employee Page</h1>
	<p>This is the main page for the Employees.<br>
	Employees can make online reservations, cancel reservations, check in, check out and charge products.
	</p>
	<div id="plaintextNavigationArea">
		<form action="<%=getURLWithContextPath(request) %>/Guest/Online-Reservation" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="Reservation" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Employee/Cancel-Reservation" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.employeeCancelReservationHeading() %>" />
		</form>
		<form action="checkin.jsp" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="Check-in" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Employee/Checkout" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.employeeCheckoutHeading() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Employee/Charge-Product" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.employeeChargeProductHeading() %>" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideEmployeeFooter.jsp" %>