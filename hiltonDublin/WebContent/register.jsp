<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
String firstName = (String) request.getAttribute("firstName");
String lastName = (String) request.getAttribute("lastName");
String phone = (String) request.getAttribute("phone");
String email = (String) request.getAttribute("email");
String username = (String) request.getAttribute("username");
String isAdmin = (String) request.getAttribute("isAdmin");


if(firstName == null) firstName="";
if(lastName == null) lastName="";
if(phone == null) phone="";
if(email == null) email="";
if(username == null) username="";
if(isAdmin == null) isAdmin="";


String registerEmployeeErrorFirstName = (String) request.getAttribute("registerEmployeeErrorFirstName");
String registerEmployeeErrorLastName = (String) request.getAttribute("registerEmployeeErrorLastName");
String registerEmployeeErrorEmail = (String) request.getAttribute("registerEmployeeErrorEmail");
String registerEmployeeErrorUsername = (String) request.getAttribute("registerEmployeeErrorUsername");
String registerEmployeeErrorPassword = (String) request.getAttribute("registerEmployeeErrorPassword");
String registerEmployeeSuccessful = (String) request.getAttribute("registerEmployeeSuccessful");

boolean registerEmployeeErrorFirstNameMissing = false;
boolean registerEmployeeErrorLastNameMissing = false;
boolean registerEmployeeErrorEmailMissing = false;
boolean registerEmployeeErrorEmailNotInRightFormat = false;
boolean registerEmployeeErrorUsernameMissing = false;
boolean registerEmployeeErrorUsernameAllreadyExist = false;
boolean registerEmployeeErrorPasswordMissing = false;
boolean registerEmployeeErrorPasswordNotInRightFormat = false;
boolean registerEmployeeErrorPasswordNotMatchingWithPasswordRepeat = false;

boolean registerEmployeeSuccess = false;

if(registerEmployeeErrorFirstName == null){
	registerEmployeeErrorFirstName = "0";
}
if(registerEmployeeErrorLastName == null){
	registerEmployeeErrorLastName = "0";
}
if(registerEmployeeErrorEmail == null){
	registerEmployeeErrorEmail = "0";
}
if(registerEmployeeErrorUsername == null){
	registerEmployeeErrorUsername = "0";
}
if(registerEmployeeErrorPassword == null){
	registerEmployeeErrorPassword = "0";
}
if(registerEmployeeSuccessful == null){
	registerEmployeeSuccessful = "0";
}

if(registerEmployeeErrorFirstName.equals("1")){
	registerEmployeeErrorFirstNameMissing = true;
}
if(registerEmployeeErrorLastName.equals("1")){
	registerEmployeeErrorLastNameMissing = true;
}
if(registerEmployeeErrorEmail.equals("1")){
	registerEmployeeErrorEmailMissing = true;
}
if(registerEmployeeErrorEmail.equals("2")){
	registerEmployeeErrorEmailNotInRightFormat = true;
}
if(registerEmployeeErrorUsername.equals("1")){
	registerEmployeeErrorUsernameMissing = true;
}
if(registerEmployeeErrorUsername.equals("2")){
	registerEmployeeErrorUsernameAllreadyExist = true;
}
if(registerEmployeeErrorPassword.equals("1")){
	registerEmployeeErrorPasswordMissing = true;
}
if(registerEmployeeErrorPassword.equals("2")){
	registerEmployeeErrorPasswordNotInRightFormat = true;
}
if(registerEmployeeErrorPassword.equals("3")){
	registerEmployeeErrorPasswordNotMatchingWithPasswordRepeat = true;
}
if(registerEmployeeSuccessful.equals("1")){
	registerEmployeeSuccess = true;
}

%>

<h1><%=language.administratorRegisterEmployeeHeading() %></h1>

<form action="<%=request.getContextPath() %>/Admin/Register-Employee-check" method="post">

<table class="showValues">

	<tr>
		<td><%=language.administratorRegisterEmployeeFirstName() %></td>	
		<td><input <%if(registerEmployeeErrorFirstNameMissing){ %>class="emptyTextField"<%} %> type="text" name="firstName" value="<%=firstName %>"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeeLastName() %></td>	
		<td><input <%if(registerEmployeeErrorLastNameMissing){ %>class="emptyTextField"<%} %> type="text" name="lastName" value="<%=lastName %>"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeePhone() %></td>	
		<td><input type="text" name="phone" value="<%=phone %>"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeeEmail() %></td>	
		<td><input <%if(registerEmployeeErrorEmailMissing || registerEmployeeErrorEmailNotInRightFormat){ %>class="emptyTextField"<%} %> type="text" name="email" value="<%=email %>"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeeUsername() %></td>	
		<td><input <%if(registerEmployeeErrorUsernameMissing || registerEmployeeErrorUsernameAllreadyExist){ %>class="emptyTextField"<%} %> type="text" name="username" value="<%=username %>"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeePassword() %></td>	
		<td><input <%if(registerEmployeeErrorPasswordMissing || registerEmployeeErrorPasswordNotInRightFormat || registerEmployeeErrorPasswordNotMatchingWithPasswordRepeat){ %>class="emptyTextField"<%} %> type="password" name="password"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeePasswordRepeat() %></td>	
		<td><input <%if(registerEmployeeErrorPasswordMissing || registerEmployeeErrorPasswordNotInRightFormat || registerEmployeeErrorPasswordNotMatchingWithPasswordRepeat){ %>class="emptyTextField"<%} %> type="password" name="passwordRepeat"></td>
	</tr>
	<tr>
		<td><%=language.administratorRegisterEmployeeIsAdmin() %></td>
		<td><input type="checkbox" name="isAdmin" value="true" <%=isAdmin %>/></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="<%=language.register() %>"/></td>
	</tr>
	
</table>

<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>

<%if(registerEmployeeErrorFirstNameMissing){ %><p class="error"><%=language.administratorRegisterEmployeeErrorFirstNameMissing() %></p><%} %>
<%if(registerEmployeeErrorLastNameMissing){ %><p class="error"><%=language.administratorRegisterEmployeeErrorLastNameMissing() %></p><%} %>
<%if(registerEmployeeErrorEmailMissing){ %><p class="error"><%=language.administratorRegisterEmployeeErrorEmailMissing() %></p><%} %>
<%if(registerEmployeeErrorEmailNotInRightFormat){ %><p class="error"><%=language.administratorRegisterEmployeeErrorEmailNotInRightFormat() %></p><%} %>
<%if(registerEmployeeErrorUsernameMissing){ %><p class="error"><%=language.administratorRegisterEmployeeErrorUsernameMissing() %></p><%} %>
<%if(registerEmployeeErrorUsernameAllreadyExist){ %><p class="error"><%=language.administratorRegisterEmployeeErrorUsernameAllreadyExist() %></p><%} %>
<%if(registerEmployeeErrorPasswordMissing){ %><p class="error"><%=language.administratorRegisterEmployeeErrorPasswordMissing() %></p><%} %>
<%if(registerEmployeeErrorPasswordNotInRightFormat){ %><p class="error"><%=language.administratorRegisterEmployeeErrorPasswordNotInRightFormat() %></p><%} %>
<%if(registerEmployeeErrorPasswordNotMatchingWithPasswordRepeat){ %><p class="error"><%=language.administratorRegisterEmployeeErrorPasswordNotMatchingPasswordRepeat() %></p><%} %>
<%if(registerEmployeeSuccess){ %><p class="informational"><%=language.administratorRegisterEmployeeSuccessful() %></p><%} %>
<%@ include file="navigationSlideAdminFooter.jsp" %>