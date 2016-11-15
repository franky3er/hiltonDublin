
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
String showContent = (String) request.getAttribute("showContent");
String lookedForRooms = (String) request.getAttribute("lookedForRooms");

String searchRoomErrorRoomNumber = (String) request.getAttribute("searchRoomErrorRoomNumber");
String searchRoomErrorTypeID = (String) request.getAttribute("searchRoomErrorTypeID");
String searchRoomErrorSmoking = (String) request.getAttribute("searchRoomErrorSmoking");
String searchRoomErrorOccupied = (String) request.getAttribute("searchRoomErrorOccupied");

String modifyRoomErrorRoomNumber = (String) request.getAttribute("modifyRoomErrorRoomNumber");
String modifyRoomSuccessful = (String) request.getAttribute("modifyRoomSuccessful");

String addRoomErrorRoomNumber = (String) request.getAttribute("addRoomErrorRoomNumber");
String addRoomSuccessful = (String) request.getAttribute("addRoomSuccessful");

List<Room> foundRooms = (List<Room>) request.getAttribute("foundRooms");
Room selectedRoom = (Room) request.getAttribute("selectedRoom");
Room addedRoom = (Room) request.getAttribute("addedRoom");

List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);


boolean searchRoomErrorRoomNumberNotInRightFormat = false;
boolean searchRoomErrorTypeIDNotINRightFormat = false;
boolean searchRoomErrorSmokingNotInRightFormat = false;
boolean searchRoomErrorOccupiedNotInRightFormat = false;

boolean modifyRoomErrorRoomNumberMissing = false;
boolean modifyRoomErrorRoomNumberNotInRightFormat = false;
boolean modifyRoomErrorRoomNotFound = false;
boolean modifyRoomSuccessfulUpdateRoom = false;

boolean addRoomErrorRoomNumberMissing = false;
boolean addRoomErrorRoomNumberNotInRightFormat = false;
boolean addRoomErrorRoomAllreadyExist = false;
boolean addRoomSuccessfulInsertRoom = false;


if(showContent==null){
	showContent = "showOptions";
}
if(lookedForRooms == null){
	lookedForRooms = "0";
}
if(searchRoomErrorRoomNumber == null){
	searchRoomErrorRoomNumber = "0";
}
if(searchRoomErrorTypeID == null){
	searchRoomErrorTypeID = "0";
}
if(searchRoomErrorSmoking == null){
	searchRoomErrorSmoking = "0";
}
if(searchRoomErrorOccupied == null){
	searchRoomErrorOccupied = "0";
}
if(modifyRoomErrorRoomNumber == null){
	modifyRoomErrorRoomNumber = "0";
}
if(modifyRoomSuccessful == null){
	modifyRoomSuccessful = "0";
}
if(addRoomErrorRoomNumber == null){
	addRoomErrorRoomNumber = "0";
}
if(addRoomSuccessful == null){
	addRoomSuccessful = "0";
}

if(searchRoomErrorRoomNumber.equals("1")){
	searchRoomErrorRoomNumberNotInRightFormat = true;
}
if(searchRoomErrorTypeID.equals("1")){
	searchRoomErrorTypeIDNotINRightFormat = true;
}
if(searchRoomErrorSmoking.equals("1")){
	searchRoomErrorSmokingNotInRightFormat = true;
}
if(searchRoomErrorOccupied.equals("1")){
	searchRoomErrorOccupiedNotInRightFormat = true;
}

if(modifyRoomErrorRoomNumber.equals("1")){
	modifyRoomErrorRoomNumberMissing = true;
}
if(modifyRoomErrorRoomNumber.equals("2")){
	modifyRoomErrorRoomNumberNotInRightFormat = true;
}
if(modifyRoomErrorRoomNumber.equals("3")){
	modifyRoomErrorRoomNotFound = true;
}
if(modifyRoomSuccessful.equals("1")){
	modifyRoomSuccessfulUpdateRoom = true;
}

if(addRoomErrorRoomNumber.equals("1")){
	addRoomErrorRoomNumberMissing = true;
}
if(addRoomErrorRoomNumber.equals("2")){
	addRoomErrorRoomNumberNotInRightFormat = true;
}
if(addRoomErrorRoomNumber.equals("3")){
	addRoomErrorRoomAllreadyExist = true;
}
if(addRoomSuccessful.equals("1")){
	addRoomSuccessfulInsertRoom = true;
}
%>

<h1><%=language.administratorModifyRoomHeading() %></h1>
<%if(showContent.equals("showOptions")){ %>

<h4><%=language.whatWouldYouLikeToDo() %></h4>
<table>
  <tr>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="modifyRoom"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input type="submit" value="<%=language.administratorModifyRoomSelectModifyRoom() %>"/>
    	</form>
    </td>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="addRoom"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input type="submit" value="<%=language.administratorModifyRoomSelectAddRoom() %>"/>
    	</form>
    </td>
  </tr>
</table>

<%} else if(showContent.equals("modifyRoom")){ %>


	<%if(selectedRoom != null){ %>
<form action="<%=request.getContextPath() %>/Admin/Modify-Room-details" method="post">
<table class="showValues">
  <tr>
    <td><%=language.administratorModifyRoomDetailsRoomNumber() %></td>
    <td><input <%if(modifyRoomErrorRoomNumberMissing||modifyRoomErrorRoomNumberNotInRightFormat){ %>class="emptyTextField"<%} %> type="text" name="newRoomNumber" maxlength="3" size="3" value="<%=selectedRoom.getRoomNumber() %>"/></td>
  </tr>
  <tr>
    <td><%=language.administratorModifyRoomDetailsType() %></td>
    <td>
    	<select name="typeID">
    		<%for(RoomType roomType : roomTypes){ %>
    		<option <%if(selectedRoom.getTypeID() == roomType.getRoomTypeID()){ %>selected <%} %> value="<%=roomType.getRoomTypeID() %>"><%=roomType.getName() %></option>
    		<%} %>
    	</select>
    </td>
  </tr>
  <tr>
  	<td><%=language.administratorModifyRoomDetailsSmoking() %></td>
  	<td>
  		<input type="radio" name="smoking" value="true" <%if(selectedRoom.isSmoking()){ %>checked<%} %> /><%=language.yes() %>
  		<input type="radio" name="smoking" value="false" <%if(!selectedRoom.isSmoking()){ %>checked<%} %> /><%=language.no() %>
  	</td>
  </tr>
  <tr>
  	<td><%=language.administratorModifyRoomDetailsOccupied() %></td>
  	<td>
  		<input type="radio" name="occupied" value="true" <%if(selectedRoom.isOccupied()){ %>checked<%} %> /><%=language.yes() %>
  		<input type="radio" name="occupied" value="false" <%if(!selectedRoom.isOccupied()){ %>checked<%} %> /><%=language.no() %>
  	</td>
  </tr>
  <tr>
  	<td></td>
  	<td><input type="submit" value="<%=language.modify() %>"/></td>
  </tr>
</table>
<input type="hidden" name="roomNumber" value="<%=selectedRoom.getRoomNumber() %>"/>
<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>

<%if(modifyRoomErrorRoomNumberMissing){ %><p class="error"><%=language.administratorModifyRoomErrorRoomNumberMissing() %></p><%} %>
<%if(modifyRoomErrorRoomNumberNotInRightFormat){ %><p class="error"><%=language.administratorModifyRoomErrorRoomNumberNotInRightFormat() %></p><%} %>
<%if(modifyRoomErrorRoomNotFound){ %><p class="error"><%=language.administratorModifyRoomErrorRoomNotFound() %></p><%} %>
<%if(modifyRoomSuccessfulUpdateRoom){ %><p class="informational"><%=language.administratorModifyRoomSuccessful() %></p><%} %>	
	<%} %>


<%} else if(showContent.equals("searchRooms")){ %>

<form action="<%=request.getContextPath() %>/Admin/Modify-Room-get-rooms" method="get">
<table class="showValues">
  <tr>
    <th><%=language.administratorModifyRoomDetailsRoomNumber() %></th>
    <th><%=language.administratorModifyRoomDetailsType() %></th>
    <th><%=language.administratorModifyRoomDetailsSmoking() %></th>
    <th><%=language.administratorModifyRoomDetailsOccupied() %></th>
    <th></th>
  </tr>
  <tr>
  	<td><input type="text" name="roomNumber" size="3" maxlength="3"/></td>
  	<td>
  		<select name="typeID">
  			<option value=""></option>
  			<%for(RoomType roomType : roomTypes){ %>
  			<option value="<%=roomType.getRoomTypeID() %>"><%=roomType.getName() %></option>
  			<%} %>
  		</select>
  	</td>
  	<td>
  		<input type="radio" name="smoking" value="1"/><%=language.yes() %>
  		<input type="radio" name="smoking" value="0"/><%=language.no() %>
  	</td>
  	<td>
  		<input type="radio" name="occupied" value="1"/><%=language.yes() %>
  		<input type="radio" name="occupied" value="0"/><%=language.no() %>
  	</td>
  	<td><input type="submit" value="<%=language.search() %>"/></td>
  </tr>
</table>
<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>

<%if(lookedForRooms.equals("1")){ %>

<%if(searchRoomErrorRoomNumberNotInRightFormat){ %>
<p class="error"><%=language.administratorModifyRoomSearchRoomErrorRoomNumberNotInRightFormat() %></p>
<%} if(searchRoomErrorTypeIDNotINRightFormat){ %>
<p class="error"><%=language.administratorModifyRoomSearchRoomErrorTypeIDNotInRightFormat() %></p>
<%} if(searchRoomErrorSmokingNotInRightFormat){ %>
<p class="error"><%=language.administratorModifyRoomSearchRoomErrorSmokingNotInRightFormat() %></p>
<%} if(searchRoomErrorOccupiedNotInRightFormat){ %>
<p class="error"><%=language.administratorModifyRoomSearchRoomErrorOccupiedNotInRightFormat() %></p>
<%} %>

<%if(foundRooms == null){ %>
<p class="error"><%=language.administratorModifyRoomSearchRoomErrorNoRoomsFound() %></p>
<%} else { 
	if(foundRooms.size()==0){%>
		<p class="error"><%=language.administratorModifyRoomSearchRoomErrorNoRoomsFound() %></p>
	<%} else { %>
		<p class="informational"><%=language.administratorModifyRoomSearchRoomSuccessfulFoundRoom(foundRooms.size()) %></p>
	<%} %>
	
<%if(foundRooms != null){ %>
	<%if(foundRooms.size() != 0){ %>
<table class="showValues">
  <tr>
    <th><%=language.administratorModifyRoomDetailsRoomNumber() %></th>
    <th><%=language.administratorModifyRoomDetailsType() %></th>
    <th><%=language.administratorModifyRoomDetailsSmoking() %></th>
    <th><%=language.administratorModifyRoomDetailsOccupied() %></th>
    <th></th>
  </tr>
  <%for(Room room : foundRooms){ %>
  <form action="<%=request.getContextPath() %>/Admin/Modify-Room-get-room" method="get">
  <tr>
    <td><%=room.getRoomNumber() %></td>
    <td><%=room.getType().getName() %></td>
    <td><%=room.isSmoking() %></td>
    <td><%=room.isOccupied() %></td>
    <td><input type="submit" value="<%=language.modify() %>"/></td>
  </tr>
  <input type="hidden" name="roomNumber" value="<%=room.getRoomNumber() %>"/>
  <input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
  </form>
  <%} %>
</table>
	<%} %>
<%} %>

<%}}} else if(showContent.equals("addRoom")){%>

<form action="<%=request.getContextPath() %>/Admin/Modify-Room-add-room" method="post">
<table class="showValues">
  <tr>
    <td><%=language.administratorModifyRoomDetailsRoomNumber() %></td>
    <td><input <%if(addRoomErrorRoomNumberMissing||addRoomErrorRoomNumberNotInRightFormat){ %>class="emptyTextField"<%} %> type="text" name="roomNumber" maxlength="3" size="3" /></td>
  </tr>
  <tr>
    <td><%=language.administratorModifyRoomDetailsType() %></td>
    <td>
    	<select name="typeID">
    		<%for(RoomType roomType : roomTypes){ %>
    		<option value="<%=roomType.getRoomTypeID() %>"><%=roomType.getName() %></option>
    		<%} %>
    	</select>
    </td>
  </tr>
  <tr>
  	<td><%=language.administratorModifyRoomDetailsSmoking() %></td>
  	<td>
  		<input type="radio" name="smoking" value="true" /><%=language.yes() %>
  		<input type="radio" name="smoking" value="false" checked /><%=language.no() %>
  	</td>
  </tr>
  <tr>
  	<td><%=language.administratorModifyRoomDetailsOccupied() %></td>
  	<td>
  		<input type="radio" name="occupied" value="true" /><%=language.yes() %>
  		<input type="radio" name="occupied" value="false" checked /><%=language.no() %>
  	</td>
  </tr>
  <tr>
  	<td></td>
  	<td><input type="submit" value="<%=language.add() %>"/></td>
  </tr>
</table>
<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>

<%if(addRoomErrorRoomNumberMissing){ %><p class="error"><%=language.administratorAddRoomErrorRoomNumberMissing() %></p><%} %>
<%if(addRoomErrorRoomNumberNotInRightFormat){ %><p class="error"><%=language.administratorAddRoomErrorRoomNumberNotInRightFormat() %></p><%} %>
<%if(addRoomErrorRoomAllreadyExist){ %><p class="error"><%=language.administratorAddRoomErrorRoomAllreadyExist() %></p><%} %>
<%if(addRoomSuccessfulInsertRoom){ %><p class="informational"><%=language.administratorAddRoomSuccessful(addedRoom) %></p><%} %>
<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>