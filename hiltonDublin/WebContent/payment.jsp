<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>


<fieldset>
<h1>Payment Information</h1>
	
<table>
 <tr>
<td><label for="cardnumber">CARD NUMBER</label></td>
  <td><input type="text" id="cardnumber" name="cardnumber" placeholder="Insert Card number" >   <select><option>Master Card</option><option>Visa Card</option><option>Credit Card</option></select> </td>

  </tr>

<tr>
<td><label for="cardcvc">CVC CODE</label></td>
<td><input type="text" id="cvcnum" name="cvcnum" placeholder="Insert CVC number" ></td>
</tr>

<tr>
<td><label for="expiration">EXPIRATION(year,month)</label></td>
<td>

	<select id = "year" name = "year" required>

<%
for (int i=2016;i<=2030;i++){%>

	<option value = "<%= i%>"> <%= i %></option>
<% 	

}
%>	<option value = "year" selected>year</option></select>


	<select id = "month"> 

<%
for (int i=1;i<=12;i++){%>

	<option value = "<%= i%>"> <%= i %></option>
<% 	

}
%>	<option value = "month" selected>month</option></select>


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
	<input type="button" onclick = "location = 'recheck.jsp'" value="Back">		
	<input type="reset"  value="cancel">
	<input type="button" onclick = "location = 'payment.jsp'" value="Book Reservation">
</div>


<%@ include file="navigationSlideGuestFooter.jsp" %>
