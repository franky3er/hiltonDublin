
<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="com.hiltondublin.classes.WeekdayPrice" %>
<%@page import="com.hiltondublin.classes.SpecialPrice" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
String[] namesOfDays =  {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

String showContent = (String) request.getAttribute("showContent");
String lookedForRooms = (String) request.getAttribute("lookedForRooms");

String searchRoomErrorRoomNumber = (String) request.getAttribute("searchRoomErrorRoomNumber");
String searchRoomErrorTypeID = (String) request.getAttribute("searchRoomErrorTypeID");
String searchRoomErrorSmoking = (String) request.getAttribute("searchRoomErrorSmoking");
String searchRoomErrorOccupied = (String) request.getAttribute("searchRoomErrorOccupied");
String searchRoomRoomNumber = (String) request.getAttribute("roomNumber");
String searchRoomTypeID = (String) request.getAttribute("typeID");
String searchRoomSmoking = (String) request.getAttribute("smoking");
String searchRoomOccupied = (String) request.getAttribute("smoking");

String deleteRoomSuccessful = (String) request.getAttribute("deleteRoomSuccessful");

String modifyRoomErrorRoomNumber = (String) request.getAttribute("modifyRoomErrorRoomNumber");
String modifyRoomSuccessful = (String) request.getAttribute("modifyRoomSuccessful");

String addRoomErrorRoomNumber = (String) request.getAttribute("addRoomErrorRoomNumber");
String addRoomSuccessful = (String) request.getAttribute("addRoomSuccessful");

String modifyRoomTypeErrorRoomTypeName = (String) request.getAttribute("modifyRoomTypeErrorRoomTypeName");
String modifyRoomTypeErrorStandardPrice = (String) request.getAttribute("modifyRoomTypeErrorStandardPrice");
String modifyRoomTypeSuccessul = (String) request.getAttribute("modifyRoomTypeSuccessul");

String deleteWeekdayPriceSuccessful = (String) request.getAttribute("deleteWeekdayPriceSuccessful");

String addWeekdayPriceErrorWeekday = (String) request.getAttribute("addWeekdayPriceErrorWeekday");
String addWeekdayPriceSuccessful = (String) request.getAttribute("addWeekdayPriceSuccessful");

String deleteSpecialPriceSuccessful = (String) request.getAttribute("deleteSpecialPriceSuccessful");

String addSpecialPriceErrorDate = (String) request.getAttribute("addSpecialPriceErrorDate");
String addSpecialPriceSuccessful = (String) request.getAttribute("addSpecialPriceSuccessful");

List<Room> foundRooms = (List<Room>) request.getAttribute("foundRooms");
Room selectedRoom = (Room) request.getAttribute("selectedRoom");
Room addedRoom = (Room) request.getAttribute("addedRoom");

List<RoomType> roomTypes = dbConnection.getRoomTypes(null, null, null, null, null, null);





boolean searchRoomErrorRoomNumberNotInRightFormat = false;
boolean searchRoomErrorTypeIDNotINRightFormat = false;
boolean searchRoomErrorSmokingNotInRightFormat = false;
boolean searchRoomErrorOccupiedNotInRightFormat = false;

boolean deleteRoomSuccess = false;

boolean modifyRoomErrorRoomNumberMissing = false;
boolean modifyRoomErrorRoomNumberNotInRightFormat = false;
boolean modifyRoomErrorRoomNotFound = false;
boolean modifyRoomSuccessfulUpdateRoom = false;

boolean addRoomErrorRoomNumberMissing = false;
boolean addRoomErrorRoomNumberNotInRightFormat = false;
boolean addRoomErrorRoomAllreadyExist = false;
boolean addRoomSuccessfulInsertRoom = false;

boolean modifyRoomTypeErrorRoomTypeNameMissing = false;
boolean modifyRoomTypeErrorStandardPriceMissing = false;
boolean modifyRoomTypeErrorStandardPriceNotInRightFormat = false;
boolean modifyRoomTypeSuccess = false;

boolean deleteWeekdayPriceSuccess = false;

boolean addWeekdayPriceErrorWeekdayAllreadyExist = false;
boolean addWeekdayPriceSuccess = false;

boolean deleteSpecialPriceSuccess = false;

boolean addSpecialPriceErrorDateAllreadyExist = false;
boolean addSpecialPriceSuccess = false;

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
if(deleteRoomSuccessful == null){
	deleteRoomSuccessful = "0";
}
if(searchRoomRoomNumber == null){
	searchRoomRoomNumber = "";
}
if(searchRoomTypeID == null){
	searchRoomTypeID = "";
}
if(searchRoomSmoking == null){
	searchRoomSmoking = "";
}
if(searchRoomOccupied == null){
	searchRoomOccupied = "";
}
if(modifyRoomTypeErrorRoomTypeName == null){
	modifyRoomTypeErrorRoomTypeName = "0";
}
if(modifyRoomTypeErrorStandardPrice == null){
	modifyRoomTypeErrorStandardPrice = "0";
}
if(modifyRoomTypeSuccessul == null){
	modifyRoomTypeSuccessul = "0";
}
if(deleteWeekdayPriceSuccessful == null){
	deleteWeekdayPriceSuccessful = "0";
}
if(addWeekdayPriceErrorWeekday == null){
	addWeekdayPriceErrorWeekday = "0";
}
if(addWeekdayPriceSuccessful == null){
	addWeekdayPriceSuccessful = "0";
}
if(deleteSpecialPriceSuccessful == null){
	deleteSpecialPriceSuccessful = "0";
}
if(addSpecialPriceErrorDate == null){
	addSpecialPriceErrorDate = "0";
}
if(addSpecialPriceSuccessful == null){
	addSpecialPriceSuccessful = "0";
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

if(deleteRoomSuccessful.equals("1")){
	deleteRoomSuccess = true;
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

if(modifyRoomTypeErrorRoomTypeName.equals("1")){
	modifyRoomTypeErrorRoomTypeNameMissing = true;
}
if(modifyRoomTypeErrorStandardPrice.equals("1")){
	modifyRoomTypeErrorStandardPriceMissing = true;
}
if(modifyRoomTypeErrorStandardPrice.equals("2")){
	modifyRoomTypeErrorStandardPriceNotInRightFormat = true;
}
if(modifyRoomTypeSuccessul.equals("1")){
	modifyRoomTypeSuccess = true;
}

if(deleteWeekdayPriceSuccessful.equals("1")){
	deleteWeekdayPriceSuccess = true;
}

if(addWeekdayPriceErrorWeekday.equals("1")){
	addWeekdayPriceErrorWeekdayAllreadyExist = true;
}
if(addWeekdayPriceSuccessful.equals("1")){
	addWeekdayPriceSuccess = true;
}

if(deleteSpecialPriceSuccessful.equals("1")){
	deleteSpecialPriceSuccess = true;
}

if(addSpecialPriceErrorDate.equals("1")){
	addSpecialPriceErrorDateAllreadyExist = true;
}
if(addSpecialPriceSuccessful.equals("1")){
	addSpecialPriceSuccess = true;
}


boolean ableToModifyRoomType = true;
RoomType selectedRoomType = null;
String selectedRoomTypeID = request.getParameter("selectedRoomTypeID");

if(selectedRoomTypeID == null){
	if(roomTypes != null){
		if(!roomTypes.isEmpty()){
			selectedRoomTypeID = Integer.toString(roomTypes.get(0).getRoomTypeID());
		} else {
			ableToModifyRoomType = false;
		}
	} else {
		ableToModifyRoomType = false;
	}
} 

List<RoomType> selectedRoomTypes = dbConnection.getRoomTypes(selectedRoomTypeID, null, null, null, null, null);
if(selectedRoomTypes != null){
	if(!selectedRoomTypes.isEmpty()){
		selectedRoomType = selectedRoomTypes.get(0);
	} else {
		ableToModifyRoomType = false;
	}
} else {
	ableToModifyRoomType = false;
}


if(request.getParameter("showContent") != null){
	if(!request.getParameter("showContent").isEmpty()){
		showContent = request.getParameter("showContent");
	}
}


%>

<h1><%=language.administratorModifyRoomHeading() %></h1>
<%if(showContent.equals("showOptions")){ %>

<h4><%=language.whatWouldYouLikeToDo() %></h4>
<table class="showValues">
  <tr>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="modifyRoom"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input style="width:100%;" type="submit" value="<%=language.administratorModifyRoomSelectModifyRoom() %>"/>
    	</form>
    </td>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="addRoom"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input style="width:100%;" type="submit" value="<%=language.administratorModifyRoomSelectAddRoom() %>"/>
    	</form>
    </td>
  </tr>
  <tr>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="modifyRoomType"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input style="width:100%;" type="submit" value="<%=language.administratorModifyRoomSelectModifyRoomType() %>"/>
    	</form>
    </td>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="addRoomType"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input style="width:100%;" type="submit" value="<%=language.administratorModifyRoomSelectAddRoomType() %>"/>
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
<%} if(deleteRoomSuccess){ %>
<p class="informational"><%=language.administratorDeleteRoomSuccessful() %></p>
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
    <th></th>
  </tr>
  <%for(Room room : foundRooms){ %>
  
  <tr>
    <td><%=room.getRoomNumber() %></td>
    <td><%=room.getType().getName() %></td>
    <td><%=room.isSmoking() %></td>
    <td><%=room.isOccupied() %></td>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-get-room" method="get">
    		<input type="submit" value="<%=language.modify() %>"/>
    		<input type="hidden" name="roomNumber" value="<%=room.getRoomNumber() %>"/>
  			<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
  		</form>
   	</td>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-delete-room" method="post">
    		<input type="submit" value="<%=language.delete() %>"/>
    		<input type="hidden" name="roomNumber" value="<%=room.getRoomNumber() %>"/>
  			<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
  			<input type="hidden" name="searchRoomNumber" value="<%=searchRoomRoomNumber %>"/>
  			<input type="hidden" name="searchTypeID" value="<%=searchRoomTypeID %>"/>
  			<input type="hidden" name="searchSmoking" value="<%=searchRoomSmoking %>"/>
  			<input type="hidden" name="searchOccupied" value="<%=searchRoomRoomNumber %>"/>
    	</form>
    </td>
  </tr>
  
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


<%} else if(showContent.equals("modifyRoomType")){ %>
	
	<%if(!ableToModifyRoomType){ %>
		<jsp:forward page="/" />
	<%} %>
	
	<h3><%=language.administratorModifyRoomTypeSelectRoomType() %></h3>
	
	<table class="showValues">
		<tr>
			<td>
				<form action="<%=request.getRequestURI()%>" method="get">
					<select name="selectedRoomTypeID" size="1" onchange="this.form.submit()">
						<%for(RoomType roomType : roomTypes){ %>
						<option <%if(selectedRoomTypeID.equals(Integer.toString(roomType.getRoomTypeID()))){ %>selected<%} %> value="<%=roomType.getRoomTypeID() %>"><%=roomType.getName() %></option>
						<%} %>
					</select>
					<input type="hidden" name="showContent" value="modifyRoomType"/>
				</form>
			</td>
			
			<td>
				<form action="<%=request.getContextPath() %>/Admin/Modify-Room-Type-delete" method="post">
					<input type="submit" value="<%=language.delete() %>"/>
					<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
					<input type="hidden" name="showContent" value="modifyRoomType"/>
					<input type="hidden" name="selectedRoomTypeID" value="<%=selectedRoomTypeID %>"/>
				</form>
			</td>
		</tr>
	</table>

	<h3><%=language.administratorModifyRoomTypeDetails() %></h3>
	
	<form action="<%=request.getContextPath() %>/Admin/Modify-Room-Type-details" method="post">
	
		<table class="showValues">
			<tr>
				<td><%=language.roomTypeName() %></td>
				<td><input type="text" size="30%" name="roomTypeName" value="<%=selectedRoomType.getName() %>"/></td>
			</tr>
			<tr>
				<td><%=language.roomTypePictureResource() %></td>
				<td><input type="text" size="30%" name="picture" value="<%=selectedRoomType.getPictureRessource() %>"/></td>
			</tr>
			<tr>
				<td><%=language.roomTypeStandardPrice() %></td>
				<td><input type="text" size="30%" name="standardPrice" value="<%=selectedRoomType.getStandardPrice() %>"/></td>
			</tr>
			<tr>
				<td><%=language.roomTypeDescription() %></td>
				<td><textarea rows="7" cols="60%" name="description"><%=selectedRoomType.getDescription() %></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="<%=language.modify() %>"/></td>
			</tr>
		</table>
		
		
		
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	<input type="hidden" name="showContent" value="modifyRoomType"/>
	<input type="hidden" name="selectedRoomTypeID" value="<%=selectedRoomTypeID %>"/>
	</form>
	
	<br/>
	<h3><%=language.administratorModifyRoomTypeWeekdayPrices() %></h3>
	
	<table class="showValues">
	
		<tr>
			<td><b><%=language.weekDay() %></b></th>
			<td><b><%=language.price() %></b></th>
			<td><b></b></th>
		</tr>
		<%
		List<WeekdayPrice> weekdayPrices = selectedRoomType.getWeekdayPrices();
		if(weekdayPrices != null){
			for(WeekdayPrice weekdayPrice : weekdayPrices){
		%>
				<tr>
					<td><%=namesOfDays[weekdayPrice.getWeekday()-1] %></td>
					<td><%=weekdayPrice.getPrice() %></td>
					<td>
						<form action="<%=request.getContextPath() %>/Admin/Modify-Room-Type-weekday-price-delete" method="post">
							<input type="submit" value="<%=language.delete() %>"/>
							<input type="hidden" name="weekDay" value="<%=weekdayPrice.getWeekday() %>"/>
							<input type="hidden" name="selectedRoomTypeID" value="<%=selectedRoomTypeID %>"/>
							<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
							<input type="hidden" name="showContent" value="modifyRoomType"/>
						</form>
					</td>
				</tr>
				
			<%} %>
		<%} %>
		
		<form action="<%=request.getContextPath() %>/Admin/Modify-Room-Type-weekday-price-add" method="post">
			<tr>
				<td>
					<select name="weekDay">
					<%for(int i = 0; i < namesOfDays.length; i++){ %>
						<option value="<%=i+1 %>"><%=namesOfDays[i] %></option>
					<%} %>
					</select>
				</td>
				<td><input type="text" name="price"/></td>
				<td><input type="submit" value="<%=language.add() %>"/></td>
			</tr>
		<input type="hidden" name="selectedRoomTypeID" value="<%=selectedRoomTypeID %>"/>
		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
		<input type="hidden" name="showContent" value="modifyRoomType"/>
		</form>
	
	</table>
	
	<br/>
	<h3><%=language.administratorModifyRoomTypeSpecialPrices() %></h3>
	
	<table class="showValues">
	
		<tr>
			<td><b><%=language.date() %></b></td>
			<td><b><%=language.price() %></b></td>
			<td><b><%=language.comment() %></b></td>
			<td><b></b></td>
		</tr>
		
		<%
		List<SpecialPrice> specialPrices = selectedRoomType.getSpecialPrices();
		
		if(specialPrices != null){
			for(SpecialPrice specialPrice : specialPrices){
		%>
		
				<tr>
					<td><%=dbConnection.onlyDayDateFormat.format(specialPrice.getDate()) %></td>
					<td><%=specialPrice.getPrice() %></td>
					<td><%=specialPrice.getComment() %></td>
					<td>
						<form action="<%=request.getContextPath() %>/Admin/Modify-Room-Type-special-price-delete" method="post">
							<input type="submit" value="<%=language.delete() %>"/>
							<input type="hidden" name="date" value="<%=dbConnection.onlyDayDateFormat.format(specialPrice.getDate()) %>"/>
							<input type="hidden" name="selectedRoomTypeID" value="<%=selectedRoomTypeID %>"/>
							<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
							<input type="hidden" name="showContent" value="modifyRoomType"/>
						</form>
					</td>
				</tr>
		
			<%} %>
		<%} %>
		
		<form action="<%=request.getContextPath() %>/Admin/Modify-Room-Type-special-price-add" method="post">
			<tr>
				<td><input type="date" name="date" required/></td>
				<td><input type="text" name="price" /></td>
				<td><input type="text" name="comment"/></td>
				<td><input type="submit" value="<%=language.add() %>"/></td>
			</tr>
		<input type="hidden" name="selectedRoomTypeID" value="<%=selectedRoomTypeID %>"/>
		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
		<input type="hidden" name="showContent" value="modifyRoomType"/>
		</form>
	
	</table>
	
	<%if(modifyRoomTypeErrorRoomTypeNameMissing){ %><p class="error"><%=language.administratorModifyRoomTypeErrorRoomTypeNameMissing() %></p><%} %>
	<%if(modifyRoomTypeErrorStandardPriceMissing){ %><p class="error"><%=language.administratorModifyRoomTypeErrorStandardPriceMissing() %></p><%} %>
	<%if(modifyRoomTypeErrorStandardPriceNotInRightFormat){ %><p class="error"><%=language.administratorModifyRoomTypeErrorStandardPriceNotInRightFormat() %></p><%} %>
	<%if(modifyRoomTypeSuccess){ %><p class="informational"><%=language.administratorModifyRoomTypeSuccessful() %></p><%} %>
	<%if(deleteWeekdayPriceSuccess){ %><p class="informational"><%=language.administratorDeleteWeekdayPriceSuccessful() %></p><%} %>
	<%if(addWeekdayPriceErrorWeekdayAllreadyExist){ %><p class="error"><%=language.administratorAddWeekdayPriceErrorWeekdayAllreadyExist() %></p><%} %>
	<%if(addWeekdayPriceSuccess){ %><p class="informational"><%=language.administratorAddWeekdayPriceSuccessful() %></p><%} %>
	<%if(deleteSpecialPriceSuccess){ %><p class="informational"><%=language.administratorDeleteSpecialPriceSuccessful() %></p><%} %>
	<%if(addSpecialPriceErrorDateAllreadyExist){ %><p class="error"><%=language.administratorAddSpecialPriceErrorDateAllreadyExist() %></p><%} %>
	<%if(addSpecialPriceSuccess){ %><p class="informational"><%=language.administratorAddSpecialPriceSuccessful() %></p><%} %>
	

<%} else if(showContent.equals("addRoomType")){ %>



<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>