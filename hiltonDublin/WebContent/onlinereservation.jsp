<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<%
	String reservationErrorFirstName = (String) request.getAttribute("reservationErrorFirstName");
	String reservationErrorLastName = (String) request.getAttribute("reservationErrorLastName");
	String reservationErrorAddress = (String) request.getAttribute("reservationErrorAddress");
	String reservationErrorEmail = (String) request.getAttribute("reservationErrorEmail");
	String reservationErrorPassportNr = (String) request.getAttribute("reservationErrorPassportNr");
	String reservationErrorDate = (String) request.getAttribute("reservationErrorDate");

	boolean reservationErrorFirstNameMissing = false;
	boolean reservationErrorLastNameMissing = false;
	boolean reservationErrorAddressMissing = false;
	boolean reservationErrorEmailMissing = false;
	boolean reservationErrorEmailNotInRightFormat = false;
	boolean reservationErrorPassportNrMissing = false;
	boolean reservationErrorPassportNrNotInRightFormat = false;
	boolean reservationErrorDateDepartureDateBeforeArrivalDate = false;
	
	if(reservationErrorFirstName == null){
		reservationErrorFirstName = "0";
	}
	if(reservationErrorLastName == null){
		reservationErrorLastName = "0";
	}
	if(reservationErrorAddress == null){
		reservationErrorAddress = "0";
	}
	if(reservationErrorEmail == null){
		reservationErrorEmail = "0";
	}
	if(reservationErrorPassportNr == null){
		reservationErrorPassportNr = "0";
	}
	if(reservationErrorDate == null){
		reservationErrorDate = "0";
	}
	
	if(reservationErrorFirstName.equals("1")){
		reservationErrorFirstNameMissing = true;
	}
	if(reservationErrorLastName.equals("1")){
		reservationErrorLastNameMissing = true;
	}
	if(reservationErrorAddress.equals("1")){
		reservationErrorAddressMissing = true;
	}
	if(reservationErrorEmail.equals("1")){
		reservationErrorEmailMissing = true;
	}
	if(reservationErrorEmail.equals("2")){
		reservationErrorEmailNotInRightFormat = true;
	}
	if(reservationErrorPassportNr.equals("1")){
		reservationErrorPassportNrMissing = true;
	}
	if(reservationErrorPassportNr.equals("2")){
		reservationErrorPassportNrNotInRightFormat = true;
	}
	if(reservationErrorDate.equals("1")){
		reservationErrorDateDepartureDateBeforeArrivalDate = true;
	}
	
	
	Calendar currentDay = Calendar.getInstance();
	currentDay.setTime(new Date());
	String currentDayAsString = dbConnection.onlyDayDateFormat.format(currentDay.getTime());
	
	Calendar tomorrowDay = Calendar.getInstance();
	tomorrowDay.setTime(new Date());
	tomorrowDay.add(Calendar.DAY_OF_MONTH, 1);
	String tomorrowDayAsString = dbConnection.onlyDayDateFormat.format(tomorrowDay.getTime());
	
	
	String firstName = (String) request.getAttribute("firstName");
	String lastName = (String) request.getAttribute("lastName");
	String address = (String) request.getAttribute("address");
	String email = (String) request.getAttribute("email");
	String passportNr = (String) request.getAttribute("passportNr");
	String phone = (String) request.getAttribute("phone");
	String arrivalDate = (String) request.getAttribute("arrivalDate");
	String departureDate = (String) request.getAttribute("departureDate");
	
	if(firstName == null) firstName = "";
	if(lastName == null) lastName = "";
	if(address == null) address = "";
	if(email == null) email = "";
	if(passportNr == null) passportNr = "";
	if(phone == null) phone = "";
	if(arrivalDate == null) arrivalDate = currentDayAsString;
	if(departureDate == null) departureDate = tomorrowDayAsString;
	
	
	
	
	
	List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);
%>

<h1><%=language.reservationOnlineHeading() %></h1>

<form id="check_guestinfo" action="<%=request.getContextPath() %>/Guest/Online-Reservation-check" method="post">
	<fieldset>
		<legend><%=language.reservationGuestDetails() %></legend>
		<table class="showValues">
			<tr>
				<td><label for="firstName"><%=language.firstName() %></label></td>
				<td><input <%if(reservationErrorFirstNameMissing){ %>class="emptyTextField"<%} %> type="text" if="firstName" name="firstName" placeholder="Insert First Name" value="<%=firstName %>"></td>
			</tr>
			<tr>
				<td><label for="lastName"><%=language.lastName() %></label></td>
				<td><input <%if(reservationErrorLastNameMissing){ %>class="emptyTextField"<%} %> type="text" id="lastName" name="lastName" placeholder="Insert Last Name" value="<%=lastName %>"></td>
			</tr>
			<tr>
				<td><label for="address"><%=language.address() %></label></td>
				<td><input <%if(reservationErrorAddressMissing){ %>class="emptyTextField"<%} %> type="text" id="address" name="address" placeholder="Insert Address" value="<%=address %>"></td>
			</tr>
			<tr>
				<td><label for="email"><%=language.email() %></label></td>
				<td><input <%if(!reservationErrorEmail.equals("0")){ %>class="emptyTextField"<%} %> type="text" id="email" name="email" placeholder="ex) example@example.com" value="<%=email %>"></td>
			</tr>
			<tr>
				<td><label for="passportNr"><%=language.passportNr() %></label></td>
				<td><input <%if(!reservationErrorPassportNr.equals("0")){ %>class="emptyTextField"<%} %> type="text" id="passportNr" name="passportNr" placeholder="Insert PassportNumber" value="<%=passportNr %>"></td>
			</tr>
			<tr>
				<td><label for="phone"><%=language.phone() %></label></td>
				<td><input type="text" id="phone" name="phone" placeholder="Input just number" value="<%=phone %>"></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<br>
	<fieldset>
		<legend><%=language.reservationBookingDetails() %></legend>
		<table class="showValues">
			<tr>
				<td><%=language.reservationArrivalDate() %></td>
				<td><%=language.reservationDepartureDate() %></td>
				<td><%=language.reservationPerson() %></td>
				<td><%=language.reservationSmoking() %></td>
			</tr>
			<tr>
				<td><input type="date" id="checkin" name="arrivalDate" value="<%=arrivalDate %>" min="<%=currentDayAsString %>" max="2017-12-31" ></td>
				<td><input type="date" id="checkout" name="departureDate" value="<%=departureDate %>" min="<%=tomorrowDayAsString %>" max="2017-12-31" ></td>
				<td><input type="number" id="numberOfGuests" name="numberOfGuests" value="1" min="1" max="6" ></td>
				<td>
					<input id="smoking" type="radio" name="smoking" value="true" checked>
					<label for="smoking">Yes</label>
					<input id="smoking" type="radio" name="smoking" value="false" checked>
					<label for="smoking">No</label>
				</td>
			</tr>
		</table>
	</fieldset>
	<br>
	<fieldset>
		<legend><%=language.reservationRoomDetails() %></legend>
		<table class="showValues" style="width:100%;">
			<col width="15%" />
	    	<col width="35%" />
	    	<col width="35%" />
	    	<col width="15%" />
	    	
	    	<tr>
	    		<th><%=language.reservationRoomType() %></th>
	    		<th><%=language.reservationRoomTypePicture() %></th>
	    		<th><%=language.reservationRoomTypeDescription() %></th>
	    		<th><%=language.reservationRoomTypeAmmount() %></th>
	    	</tr>
			
			<%int i=1; for(RoomType roomType : roomTypes){ %>
			
				<tr>
					<td><%=roomType.getName() %></td>
					<td>
						<img class="imgRoomType" src="${pageContext.request.contextPath}<%=roomType.getPictureRessource() %>" alt="Img <%=roomType.getName() %>" />
					</td>
					<td><%=roomType.getDescription() %></td>
					<td>
						<input type="number" name="roomTypeAmmount<%=i %>" value="0" min="0" max="9"/>
						<input type="hidden" name="roomTypeID<%=i %>" value="<%=roomType.getRoomTypeID() %>"/>
					</td>
				</tr>
			
			<%i++;} %>
		</table>
	</fieldset>
	<table>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="<%=language.reserveAndPay() %>">
			<input type="reset" value="Reset">
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	
	
</form>


<%if(reservationErrorFirstNameMissing){ %><p class="error"><%=language.reservationErrorFirstNameMissing() %></p><%} %>
<%if(reservationErrorLastNameMissing){ %><p class="error"><%=language.reservationErrorLastNameMissing() %></p><%} %>
<%if(reservationErrorAddressMissing){ %><p class="error"><%=language.reservationErrorAddressMissing() %></p><%} %>
<%if(reservationErrorEmailMissing){ %><p class="error"><%=language.reservationErrorEmailMissing() %></p><%} %>
<%if(reservationErrorEmailNotInRightFormat){ %><p class="error"><%=language.reservationErrorEmailNotInRightFormat() %></p><%} %>
<%if(reservationErrorPassportNrNotInRightFormat){ %><p class="error"><%=language.reservationErrorPassportNrNotInRightFormat() %></p><%} %>
<%if(reservationErrorPassportNrMissing){ %><p class="error"><%=language.reservationErrorPassportNrMissing() %></p><%} %>
<%if(reservationErrorDateDepartureDateBeforeArrivalDate){ %><p class="error"><%=language.reservationErrorDateDepartureDateBeforeArrivalDate() %></p><%} %>


<%@ include file="navigationSlideGuestFooter.jsp" %>