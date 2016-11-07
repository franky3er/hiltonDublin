<%@ include file="navigationSlideAdminHeader.jsp" %>
<body>
	<h1>Welcome to Administrator Page</h1>
	<p>This is the main page for the Administrator<br>
	Administrator can modify room, reservations, products and register employees.
	</p>
		<div>
		<form action="modifyRoom.jsp" method="get">
			<input class="navigationPage" type="submit" value="Modify Room" />
		</form>
		<form action="modifyReservation.jsp" method="get">
			<input class="navigationPage" type="submit" value="Modify Reservation" />
		</form>
		<form action="modifyProduct.jsp" method="get">
			<input class="navigationPage" type="submit" value="Modify Product" />
		</form>
		<form action="addProduct.jsp" method="get">
			<input class="navigationPage" type="submit" value="Add Product" />
		</form>
		<form action="registerEmployee.jsp" method="get">
			<input class="navigationPage" type="submit" value="Register Employee" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideAdminFooter.jsp" %>