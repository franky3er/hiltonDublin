
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
List<Room> foundRooms = (List<Room>) request.getAttribute("foundRooms");
Room selectedRoom = (Room) request.getAttribute("selectedRoom");

List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);


boolean searchRoomErrorRoomNumberNotInRightFormat = false;
boolean searchRoomErrorTypeIDNotINRightFormat = false;
boolean searchRoomErrorSmokingNotInRightFormat = false;
boolean searchRoomErrorOccupiedNotInRightFormat = false;

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
    <td><input type="text" name="newRoomNumber" maxlength="3" size="3" value="<%=selectedRoom.getRoomNumber() %>"/></td>
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
  		<input type="radio" name="smoking" value="1" <%if(selectedRoom.isSmoking()){ %>checked<%} %> /><%=language.yes() %>
  		<input type="radio" name="smoking" value="0" <%if(!selectedRoom.isSmoking()){ %>checked<%} %> /><%=language.no() %>
  	</td>
  </tr>
  <tr>
  	<td><%=language.administratorModifyRoomDetailsOccupied() %></td>
  	<td>
  		<input type="radio" name="occupied" value="1" <%if(selectedRoom.isOccupied()){ %>checked<%} %> /><%=language.yes() %>
  		<input type="radio" name="occupied" value="0" <%if(!selectedRoom.isOccupied()){ %>checked<%} %> /><%=language.no() %>
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



<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>