
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
<%} else { %>

<p class="informational"><%=language.administratorModifyRoomSearchRoomSuccessfulFoundRoom(foundRooms.size()) %></p>

<%}}} else if(showContent.equals("addRoom")){%>



<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>