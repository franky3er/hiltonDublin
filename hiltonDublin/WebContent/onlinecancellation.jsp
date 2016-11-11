
<%@page import="com.hiltondublin.db.HiltonDublinDBConnection"%>
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="com.hiltondublin.users.Guest" %>
<%@page import="com.hiltondublin.classes.ConsumerProduct" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import= "java.sql.ResultSet" %>

<%@ include file="navigationSlideGuestHeader.jsp" %>
     
<%
	/*Get Rooms data*/
	String roomNumber = null;
	String typeID = null;
	String smoking = "true";
	String occupied = "false";
	String additionalSQL = HiltonDublinDBConnection.ROOM_NUMBER + ">'200'";
	
	List<String> roomsAsStrings = new ArrayList<String>();
	
	List<Room> rooms = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, additionalSQL);
	System.out.println("rooms.size() : "+rooms.size());
	System.out.println("HiltonDublinDBConnection.ROOM_NUMBER : "+HiltonDublinDBConnection.ROOM_NUMBER);
	System.out.println("additionalSQL : "+additionalSQL);
	for(Room room : rooms){
		roomsAsStrings.add("RoomNumber: " + room.getRoomNumber() + "   typeID: " + room.getTypeID() + "   smoking: " + room.isSmoking() + "   occupied: " + room.isOccupied());
	}
	
	
	/*Insert fake Geust data
	 String guestID =null;
	 String  firstName = "Max";
	 String  lastName = "Mustermann";
	 String  phoneNumber = "12345678";
	 String  email = "email@gmail.com";
	 String address ="Eine Adresse 103 Hamburg";
	 String  passportNr = "123456"; 
	 String additionalSQLCondition = null;
	 */
	 /*
	 ResultSet guestID_primKey = dbConnection.insertGuest(null, firstName, lastName, phoneNumber, email, address, passportNr);
	   
	 if(guestID_primKey.next()){
			guestID = guestID_primKey.getString(1); //Returns the Primary Key of the inserted row in the GUEST table
			 
			System.out.println("guestID_primKey.getString(1)= "+guestID);
		}  
	 */  
	 
	 /*Get Geust data*/
	 String guestID =null;
	 String  firstName =null;
	 String  lastName =null;
	 String  phoneNumber =null;
	 String  email =null;
	 String address =null;
	 String  passportNr =null;
	 String additionalSQLCondition = null;	 
	 //List<String> guestsAsStrings = new ArrayList<String>();	 

	 //Get every saved guests datas from DB.
	List<Guest> guests = dbConnection.getGuests(guestID, firstName, lastName, phoneNumber, email, address, passportNr, additionalSQLCondition);
	 System.out.println("guest.size(): " + guests.size());
	/*  for(Guest guest : guests){
		 guestsAsStrings.add("GuestId: "+guest.getGuestID()+"  LastName :"+guest.getLastName());
		 System.out.println("GuestId: "+guest.getGuestID()+"  Lastname :"+guest.getLastName());
	 }  */

	
	/*Insert fake Bookings data*/
	/* 	String reservationID = "1";
	String arrivalDate = "2016-11-03 00:00:00";
	String departureDate = "2016-11-05 00:00:00";
	String paid = "false"; */
	/* 	 ResultSet reservationID_primKey = dbConnection.insertReservation(null, guestID, arrivalDate, departureDate, paid);
	 
	if(reservationID_primKey.next()){
		reservationID = reservationID_primKey.getString(1); //Returns the Primary Key of the inserted row in the Reservation table
	}  
	 */
	
	/*Get Bookings data*/
	String reservationID = null;
	String arrivalDate = null;
	String departureDate = null;
	String paid = null;
	
	/*Get Bookings data*/
	List<String> reservationAsStrings = new ArrayList<String>();
	List<Reservation> reservations = dbConnection.getReservations(reservationID, guestID, arrivalDate, departureDate, paid, additionalSQLCondition);
	 System.out.println("reservations.size() : "+reservations.size());
	 /*
	for(Reservation reservation : reservations)
	{
		reservationAsStrings.add("Booking Number: "+reservation.getBookingNumber()+"  guestID: "+reservation.getGuestID());
		System.out.println("Booking Number: "+reservation.getBookingNumber()+"  guestID: "+reservation.getGuestID());
		
	}  	 */
%>

<%
String inputReservationId = request.getParameter("inputReservationId");
String inputLastname = request.getParameter("inputlastname");

boolean isSubmitted = false;
boolean isFilledOutCorrect = true;
boolean reservationidWrong = false;
boolean lastNameWrong = false;
boolean reservationIdFound =false;
boolean lastnameFound =false;
boolean resultFound = false;

String errorMessage = "";
String resultMessage = "";
String resultMessage2 = "";

if(inputReservationId!=null && inputLastname!=null){
	if(inputReservationId.isEmpty() || inputReservationId.trim().equals(""))
	{
		errorMessage += "Booking reference is missing"+"<br/>";
		isFilledOutCorrect = false;
		reservationidWrong = true;
	}
	if(inputLastname.isEmpty() || inputLastname.trim().equals("") )
	{
		errorMessage += "Last name is missing"+"<br/>";
		isFilledOutCorrect = false;
		lastNameWrong = true;
	}
	if(isFilledOutCorrect){
	
		 for(Reservation reservation : reservations){
			 resultMessage = "Can't find Booking reference."+"<br/>";
			if( inputReservationId.trim().equals(reservation.getBookingNumber()+"" ) )
			{
				reservationIdFound =true;
				resultMessage = "";
				break;
			}
			
		}//end of reservation for loop
		for(Guest guest : guests){
			resultMessage2 = "Can't find Last name."+"<br/>";
				if(inputLastname.equals(guest.getLastName()))
				{
					lastnameFound =true;
				resultMessage2 = "";
				break;
				}
		 } //end of guest for loop
		isSubmitted = true;
		
	}
	
	if(reservationIdFound==true && lastnameFound==true) { 
		resultFound =true;
		
		}
}
%>

<%if(isSubmitted==false ){ %>
<h1>Book Cancellation</h1>
<form id="onlinecancellationform" name="onlinecancellationform" action="<%=request.getRequestURI()%>" method="post">
<table>
		<tr>
			<td><b>Please fill it up to access your booking. </b></td>
			<td></td>
		</tr>
		<tr>
			<td>Booking reference </td>
			<td>	
				<input <%if(reservationidWrong)
						{ %>class="emptyTextField" <%} %> type="text" name="inputReservationId" placeholder="Booking reference" size="30" <%if(inputReservationId!=null){ %>value="<%=inputReservationId%>"/<%} %>>
			</td>
		</tr>
		<tr>
			<td>Last Name </td>
			<td>	
				<input <%if(lastNameWrong)
				{ %>class="emptyTextField" <%} %> type="text" name="inputlastname" placeholder="Last name" size="30" <%if(inputLastname!=null){ %>value="<%=inputLastname %>" <%} %>/>
			</td>
		</tr>
		<tr>
		<tr>
			<td><input type="submit" value="Find Booking"/></td>
		</tr>
</table>
</form>
<p class="loginError"><%=errorMessage %></p>
<p class="loginError"><%=resultMessage %><%=resultMessage2%></p>
<%} else if(resultFound) { 
	String orderID = null;
	String productID = null;
	//dbConnection.deleteReservations(inputReservationId, guestID, arrivalDate, departureDate, paid, additionalSQLCondition); 
	//dbConnection.deleteReserved_Rooms(roomNumber, inputReservationId, additionalSQLCondition);
	//dbConnection.deleteReserved_Products(orderID, productID, inputReservationId, additionalSQLCondition);
	List<String> reserved_roomAsStrings = new ArrayList<String>();
	List<String> reserved_productsAsStrings = new ArrayList<String>();
	List<Room> reserved_rooms = dbConnection.getReservedRooms(reservationID);
	List<ConsumerProduct> reserved_products = dbConnection.getReservedProducts(reservationID);
	
	for(Room reserved_room : reserved_rooms){
		reserved_roomAsStrings.add(reserved_room.getRoomNumber()+"");
	}
	for(ConsumerProduct reserved_prouct : reserved_products ){
		reserved_productsAsStrings.add(reserved_prouct.getProductID()+"");
	}	
%>

<h1>Your Bookings</h1>

<p><b>Booking reference</b></p>  <%=inputReservationId %>
<p><b>Last name</b></p><%=inputLastname %>
<p><b>Room number </b></p>
<%
for(String reserved_roomAsString : reserved_roomAsStrings){
%>	
 <p><%=reserved_roomAsString %></p>
<%
}
%>

<p></p><b>The booking is canceled. We hope to see you again!</b>
<%} 
else { %>
<h1>Book Cancellation</h1>
<form id="onlinecancellationform" name="onlinecancellationform" action="<%=request.getRequestURI()%>" method="post">
<table>
		<tr>
			<td><b>Please fill it up to access your booking. </b></td>
			<td></td>
		</tr>
		<tr>
			<td>Booking reference </td>
			<td>	
				<input <%if(reservationidWrong)
						{ %>class="emptyTextField" <%} %> type="text" name="inputReservationId" placeholder="1" size="20" <%if(inputReservationId!=null){ %>value="<%=inputReservationId%>"/<%} %>>
			</td>
		</tr>
		<tr>
			<td>Last Name </td>
			<td>	
				<input <%if(lastNameWrong)
				{ %>class="emptyTextField" <%} %> type="text" name="inputlastname" placeholder="Mustermann" size="20" <%if(inputLastname!=null){ %>value="<%=inputLastname %>" <%} %>/>
			</td>
		</tr>
		<tr>
		<tr>
			<td><input type="submit" value="Find Booking"/></td>
		</tr>
</table>
</form>
<p class="loginError"><%=resultMessage %><%=resultMessage2%></p>
<b>We are unable to find this confirmation number. Please validate your entry and try again or contact us for further information.</b>
<%} %>


<%@ include file="navigationSlideGuestFooter.jsp" %>