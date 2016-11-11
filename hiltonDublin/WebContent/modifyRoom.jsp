
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
String showContent = (String) request.getAttribute("showContent");
List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);



if(showContent==null){
	showContent = "searchRooms";
}
%>

<h1><%=language.administratorModifyRoomHeading() %></h1>

<%if(showContent.equals("searchRooms")){ %>
<form action="" method="get">
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
</form>
<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>