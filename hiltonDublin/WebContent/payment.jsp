<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.Reservation" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>

<%

String bookingNumber = (String) request.getAttribute("bookingNumber");
Reservation payedReservation = (Reservation) request.getAttribute("payedReservation");

String cardHolder = (String) request.getAttribute("cardHolder");
String creditCard = (String) request.getAttribute("creditCard");
String cardNumber = (String) request.getAttribute("cardNumber");
String cvvCode = (String) request.getAttribute("cvvCode");
String expirationYear = (String) request.getAttribute("expirationYear");
String expirationMonth = (String) request.getAttribute("expirationMonth");

String paymentErrorCardNumber = (String) request.getAttribute("paymentErrorCardNumber");
String paymentErrorCreditCard = (String) request.getAttribute("paymentErrorCreditCard");
String paymentErrorCvvCode = (String) request.getAttribute("paymentErrorCvvCode");
String paymentErrorExpiration = (String) request.getAttribute("paymentErrorExpiration");
String paymentErrorCardHolder = (String) request.getAttribute("paymentErrorCardHolder");

boolean paymentErrorCardNumberMissing = false;
boolean paymentErrorCardNumberInvalid = false;
boolean paymentErrorCreditCardMissing = false;
boolean paymentErrorCreditCardUnknown = false;
boolean paymentErrorCvvCodeMissing = false;
boolean paymentErrorCvvCodeInvalid = false;
boolean paymentErrorExpirationYearMissing = false;
boolean paymentErrorExpirationMonthMissing = false;
boolean paymentErrorCardHolderMissing = false;

if(paymentErrorCardNumber == null){
	paymentErrorCardNumber = "0";
}
if(paymentErrorCreditCard == null){
	paymentErrorCreditCard = "0";
}
if(paymentErrorCvvCode == null){
	paymentErrorCvvCode = "0";
}
if(paymentErrorExpiration == null){
	paymentErrorExpiration = "0";
}
if(paymentErrorCardHolder == null){
	paymentErrorCardHolder = "0";
}

if(cardHolder == null){
	cardHolder = "";
}
if(creditCard == null){
	creditCard = "";
}
if(cardNumber == null){
	cardNumber = "";
}
if(cvvCode == null){
	cvvCode = "";
}
if(expirationYear == null){
	expirationYear = "";
}
if(expirationMonth == null){
	expirationMonth = "";
}

if(paymentErrorCardNumber.equals("1")){
	paymentErrorCardNumberMissing = true;
}
if(paymentErrorCardNumber.equals("2")){
	paymentErrorCardNumberInvalid = true;
}
if(paymentErrorCreditCard.equals("1")){
	paymentErrorCreditCardMissing = true;
}
if(paymentErrorCreditCard.equals("2")){
	paymentErrorCreditCardUnknown = true;
}
if(paymentErrorCvvCode.equals("1")){
	paymentErrorCvvCodeMissing = true;
}
if(paymentErrorCvvCode.equals("2")){
	paymentErrorCvvCodeInvalid = true;
}
if(paymentErrorExpiration.equals("1")){
	paymentErrorExpirationYearMissing = true;
}
if(paymentErrorExpiration.equals("2")){
	paymentErrorExpirationMonthMissing = true;
}
if(paymentErrorCardHolder.equals("1")){
	paymentErrorCardHolderMissing = true;
}

%>

<%if(bookingNumber != null && payedReservation == null){ %>

<form action="<%=request.getContextPath() %>/Guest/Online-Reservation-payment-check" method="post">
	<fieldset>
	<h1><%=language.paymentInformation() %></h1>
	
	<div class="cc-selector">
        <input id="visa" type="radio" name="credit-card" value="visa" <%if(creditCard.equals("visa")){ %>checked<%} %> />
        <label class="drinkcard-cc visa" for="visa"></label>
        <input id="mastercard" type="radio" name="credit-card" value="mastercard" <%if(creditCard.equals("mastercard")){ %>checked<%} %> />
        <label class="drinkcard-cc mastercard"for="mastercard"></label>
    </div>
		
	<table>
		<tr>
			<td><label for="cardHolder"><%=language.paymentCardHolder() %></label></td>
			<td><input <%if(!paymentErrorCardHolder.equals("0")){ %>class="emptyTextField"<%} %> type="text" id="cardHolder" name="cardHolder" <%if(!cardHolder.equals("")){ %>value="<%=cardHolder %>"<%} %> placeholder="Card Holder"/></td>
		</tr>
		<tr>
			<td><label for="cardnumber"><%=language.paymentCardNumber() %></label></td>
		  	<td><input <%if(!paymentErrorCardNumber.equals("0")){ %>class="emptyTextField"<%} %> type="text" id="cardnumber" name="cardNumber" <%if(!cardNumber.equals("")){ %>value="<%=cardNumber %>"<%} %> placeholder="Insert Card number" > 
		
		</tr>
		
		<tr>
			<td><label for="cardcvv"><%=language.paymentCvvCode() %></label></td>
			<td><input <%if(!paymentErrorCvvCode.equals("0")){ %>class="emptyTextField"<%} %> type="text" id="cvvnum" name="cvcNumber" size="4" maxlength="4" <%if(!cvvCode.equals("")){ %>value="<%=cvvCode %>"<%} %> placeholder="CVV" ></td>
		</tr>
		
		<tr>
			<td><label for="expiration"><%=language.paymentExpiration() %></label></td>
			<td>
				<select name="expirationYear">
					<option value = "year"><%=language.paymentYear() %></option>
					<%for (int i=2016;i<=2030;i++){%>
						<option value="<%=i %>" <%if(expirationYear.equals(Integer.toString(i))){ %>selected<%} %> > <%= i %></option>
					<%} %>		
				</select>
				
				<select name="expirationMonth"> 
					<option value = "month"><%=language.paymentMonth() %></option>
					<%for (int i=1;i<=12;i++){%>
						<option value = "<%= i%>" <%if(expirationMonth.equals(Integer.toString(i))){ %>selected<%} %> > <%= i %></option>
					<%} %>	
				</select>
			</td>
		</tr>
	
	</table>
	</fieldset>
	
	<fieldset>
		<h1>Payment Policies</h1>
		The deposit and service charge are required now to confirm and guarantee your reservation, the remainder is due on arrival at the hotel. Deposits are non-refundable and form part of your total accommodation cost. You will be charged in the currency that you have selected above. Where this currency differs from the currency of the accommodation provider we have converted the amount due in accordance with our standard currency conversion rates.
	</fieldset>
	
	<fieldset>
		<h1>Cancellation Policies</h1>
	 	Please note that any cancellations must be notified directly to the hotel at least 24 hours in advance of your scheduled arrival date, unless otherwise stated in the hotel's conditions. In the event of a no-show, the total of the first night's accommodation will be charged to your credit card. If you cancel any individual night of your reservation, then the deposit for that night will not be refunded or transferred.
	</fieldset>
	
	<div>	
		<input type="reset" value="<%=language.paymentReset() %>">
		<input type="submit" value="<%=language.paymentPay() %>">
	</div>
	
	<input type="hidden" name="bookingNumber" value="<%=bookingNumber %>"/>
	<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
	
	<%if(paymentErrorCardNumberMissing){ %><p class="error"><%=language.paymentErrorCardNumberMissing() %></p><%} %>
	<%if(paymentErrorCardNumberInvalid){ %><p class="error"><%=language.paymentErrorCardNumberInvalid() %></p><%} %>
	<%if(paymentErrorCreditCardMissing){ %><p class="error"><%=language.paymentErrorCreditCardMissing() %></p><%} %>
	<%if(paymentErrorCreditCardUnknown){ %><p class="error"><%=language.paymentErrorCreditCardUnknown() %></p><%} %>
	<%if(paymentErrorCvvCodeMissing){ %><p class="error"><%=language.paymentErrorCvvCodeMissing() %></p><%} %>
	<%if(paymentErrorCvvCodeInvalid){ %><p class="error"><%=language.paymentErrorCvvCodeInvalid() %></p><%} %>
	<%if(paymentErrorExpirationYearMissing){ %><p class="error"><%=language.paymentErrorExpirationYearMissing() %></p><%} %>
	<%if(paymentErrorExpirationMonthMissing){ %><p class="error"><%=language.paymentErrorExpirationMonthMissing() %></p><%} %>
	<%if(paymentErrorCardHolderMissing){ %><p class="error"><%=language.paymentErrorCardHolderMissing() %></p><%} %>
	
</form>

<%} else if(bookingNumber != null && payedReservation != null){ %>
	
	<h1><%=language.reservationOnlineHeading() %></h1>
	
	<p><%=language.paymentSuccessful() %></p>
	
	<h3><%=language.paymentYourBooking() %></h3>
	
	<table class="showValues">
  		<tr>
   			<td><%=language.reservationBookingNumber() %></td>
    		<td><%=payedReservation.getBookingNumber() %></td>
  		</tr>
  		<tr>
   			<td><%=language.firstName() %></td>
    		<td><%=payedReservation.getGuest().getFirstName() %></td>
  		</tr>
  		<tr>
   			<td><%=language.lastName() %></td>
    		<td><%=payedReservation.getGuest().getLastName() %></td>
  		</tr>
  		<tr>
   			<td><%=language.reservationArrivalDate() %></td>
    		<td><%=dbConnection.onlyDayDateFormat.format(payedReservation.getArrivalDate()) %></td>
  		</tr>
  		<tr>
   			<td><%=language.reservationDepartureDate() %></td>
    		<td><%=dbConnection.onlyDayDateFormat.format(payedReservation.getDepartureDate()) %></td>
  		</tr>
	</table>

	
<%} else { %>
	
	<jsp:forward page="/" /> 
	
<%} %>

<%@ include file="navigationSlideGuestFooter.jsp" %>
