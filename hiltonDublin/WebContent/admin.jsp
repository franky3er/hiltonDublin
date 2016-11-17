<%@ include file="navigationSlideAdminHeader.jsp" %>
<body>
	<h1>Welcome to Administrator Page</h1>
	<p>This is the main page for the Administrator<br>
	Administrator can modify room, reservations, products and register employees.
	</p>
	<div id="plaintextNavigationArea">
		<form action="<%=getURLWithContextPath(request) %>/Admin/Modify-Room" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.administratorModifyRoomHeading() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Admin/Modify-Reservation" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.administratorModifyReservationHeading() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Admin/Modify-Product" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.administratorModifyProductHead() %>" />
		</form>
		<form action="<%=getURLWithContextPath(request) %>/Admin/Register-Employee" method="get">
			<input class="plaintextNavigationAreaDetail" type="submit" value="<%=language.administratorRegisterEmployeeHeading() %>" />
		</form>
	</div>
</body>
<%@ include file="navigationSlideAdminFooter.jsp" %>