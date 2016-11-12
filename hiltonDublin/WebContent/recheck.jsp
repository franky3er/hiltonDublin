<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.lang.*" %>
    
<%@ include file="navigationSlideGuestHeader.jsp" %>



	<fieldset>
<h1>Reservation Summary</h1>

<table>
 <tr>
  <td>Name</td> 
    <td>====</td>
</tr>
<tr>
	<td>Email</td>
    <td>====</td>   
</tr>

<tr>
    <td>Phone number</td>
    <td>====</td>
</tr>

<tr>
<td>Check in </td>
<td>====<td>
</tr>

<tr>
<td>Check out</td>
<td>====</td>
</tr>
<tr>
<td>Person</td>
<td>=====</td>
</tr>

 <tr>
  <td>Room info</td>
    <td>====</td>
</tr>
<tr>	
    <td>Total Price</td>
    <td>====</td>
   
</tr>

	</fieldset>
  </table>
<div>


	<p>
Check your booking information, and go on next page to complete reservation.	
		</p>
		<p>
			<input type="button" onclick = "location = 'onlinereservation.jsp'" value="Back"/>
			<input type="button" onclick = "location = 'payment.jsp'" value="next">
		</p>
	
	

</div>

<%@ include file="navigationSlideGuestFooter.jsp" %>
