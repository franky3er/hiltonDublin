<%@page import="com.hiltondublin.classes.Room" %>
<%@page import="com.hiltondublin.classes.RoomType" %>
<%@page import="com.hiltondublin.classes.ConsumerProduct" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ include file="navigationSlideAdminHeader.jsp" %>

<%
String showContent = (String) request.getAttribute("showContent");

String lookedForProducts = (String) request.getAttribute("lookedForProducts");
String searchProductErrorProductID = (String) request.getAttribute("searchProductErrorProductID");
String searchProductErrorPrice = (String) request.getAttribute("searchProductErrorPrice");

String modifyProductErrorProductID = (String) request.getAttribute("modifyProductErrorProductID");
String modifyProductErrorProductName = (String) request.getAttribute("modifyProductErrorProductName");
String modifyProductErrorPrice = (String) request.getAttribute("modifyProductErrorPrice");

List<ConsumerProduct> foundProducts = (List<ConsumerProduct>) request.getAttribute("foundProducts");
ConsumerProduct selectedProduct = (ConsumerProduct) request.getAttribute("selectedProduct");

boolean searchProductErrorProductIDNotInRightFormat = false;
boolean searchProductErrorPriceNotInRightFormat = false;
boolean lookedForProd = false;

boolean modifyProductErrorProductIDMissing = false;
boolean modifyProductErrorProductIDNotInRightFormat = false;
boolean modifyProductErrorProductIDAllreadyExist = false;
boolean modifyProductErrorProductNameMissing = false;
boolean modifyProductErrorPriceMissing = false;
boolean modifyProductErrorPriceNotInRightFormat = false;

if(showContent == null){
	showContent = "showOptions";
}
if(lookedForProducts == null){
	lookedForProducts = "0";
}
if(searchProductErrorProductID == null){
	searchProductErrorProductID = "0";
}
if(searchProductErrorPrice == null){
	searchProductErrorPrice = "0";
}
if(modifyProductErrorProductID == null){
	modifyProductErrorProductID = "0";
}
if(modifyProductErrorProductName == null){
	modifyProductErrorProductName = "0";
}
if(modifyProductErrorPrice == null){
	modifyProductErrorPrice = "0";
}


if(searchProductErrorProductID.equals("1")){
	searchProductErrorProductIDNotInRightFormat = true;
}
if(searchProductErrorPrice.equals("1")){
	searchProductErrorPriceNotInRightFormat = true;
}
if(lookedForProducts.equals("1")){
	lookedForProd = true;
}
if(modifyProductErrorProductID.equals("1")){
	modifyProductErrorProductIDMissing = true;
}
if(modifyProductErrorProductID.equals("2")){
	modifyProductErrorProductIDNotInRightFormat = true;
}
if(modifyProductErrorProductID.equals("3")){
	modifyProductErrorProductIDAllreadyExist = true;
}
if(modifyProductErrorProductName.equals("1")){
	modifyProductErrorProductNameMissing = true;
}
if(modifyProductErrorPrice.equals("1")){
	modifyProductErrorPriceMissing = true;
}
if(modifyProductErrorPrice.equals("2")){
	modifyProductErrorPriceNotInRightFormat = true;
}
%>

<h1><%=language.administratorModifyProductHead() %></h1>

<%if(showContent.equals("showOptions")){ %>

<h4><%=language.whatWouldYouLikeToDo() %></h4>
<table>
  <tr>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Product-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="modifyProduct"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input type="submit" value="<%=language.administratorModifyProductSelectModifyProduct() %>"/>
    	</form>
    </td>
    <td>
    	<form action="<%=request.getContextPath() %>/Admin/Modify-Product-select-option" method="get">
    		<input type="hidden" name="optionSelection" value="addProduct"/>
    		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
    		<input type="submit" value="<%=language.administratorModifyProductSelectAddProduct() %>"/>
    	</form>
    </td>
  </tr>
</table>

<%} else if(showContent.equals("modifyProduct")){ %>

	<%if(selectedProduct != null){ %>
		
		<form action="<%=request.getContextPath() %>/Admin/Modify-Product-details" method="post">
		<table class="showValues">
		  	<tr>
		    	<td><%=language.administratorModifyProductProductID() %></td>
		    	<td>
		    		<input type="text" name="newProductID" value="<%=selectedProduct.getProductID() %>"/>
		    	</td>
		  	</tr>
		  	<tr>
		    	<td><%=language.administratorModifyProductProductName() %></td>
		    	<td>
		    		<input type="text" name="productName" value="<%=selectedProduct.getName() %>"/>
		    	</td>
		  	</tr>
		  	<tr>
		    	<td><%=language.administratorModifyProductPrice() %></td>
		    	<td>
		    		<input type="text" name="price" value="<%=selectedProduct.getPrice() %>"/>
		    	</td>
		  	</tr>
		  	<tr>
		  		<td></td>
		  		<td><input type="submit" value="<%=language.modify() %>"/></td>
		  	</tr>
		</table>
		<input type="hidden" name="productID" value="<%=selectedProduct.getProductID() %>"/>
		<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>	   	 		
		</form>
		
	<%} else { %>
		<p class="error"><%=language.administratorModifyProductErrorProductNotFound() %></p>
	<%} %>

<%} else if(showContent.equals("searchProducts")){ %>

<form action="<%=request.getContextPath() %>/Admin/Modify-Product-get-products" method="get">
<table class="showValues">
  <tr>
    <th><%=language.administratorModifyProductProductID() %></th>
    <th><%=language.administratorModifyProductProductName() %></th>
    <th><%=language.administratorModifyProductPrice() %></th>
    <th></th>
  </tr>
  <tr>
  	<td><input type="text" name="productID" /></td>
  	<td><input type="text" name="productName"/></td>
  	<td>
  		<input type="text" name="price"/><br/>
  		<p class="information">Format 01.00</p>
  	</td>
  	<td><input type="submit" value="<%=language.search() %>"/></td>
  </tr>
</table>
<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
</form>

	<%if(lookedForProd){ %>

		<%if(searchProductErrorProductIDNotInRightFormat){ %><p class="error"><%=language.administratorModifyProductErrorProductIDNotInRightFormat() %></p><%} %>
		<%if(searchProductErrorPriceNotInRightFormat){ %><p class="error"><%=language.administratorModifyProductErrorPriceNotInRightFormat() %></p><%} %>
		<%if(foundProducts == null){ %><p class="error"><%=language.administratorModifyProductErrorNoRoomFound() %></p><%}%>
		<%if(foundProducts != null){ %>
			<%if(foundProducts.size()==0){ %><p class="error"><%=language.administratorModifyProductErrorNoRoomFound() %></p><%} %>
			<%if(foundProducts.size()>0){ %>
			
				<p class="informational"><%=language.administratorModifyProductSuccessfulFoundProduct(foundProducts.size()) %></p>
				
				<table class="showValues">
				  	<tr>
					    <th><%=language.administratorModifyProductProductID() %></th>
					    <th><%=language.administratorModifyProductProductName() %></th>
					    <th><%=language.administratorModifyProductPrice() %></th>
				    	<th></th>
				  	</tr>
				  	<%for(ConsumerProduct product : foundProducts){ %>
				  	<tr>
				    	<td><%=product.getProductID() %></td>
				   	 	<td><%=product.getName() %></td>
				   	 	<td><%=product.getPrice() %></td>
				   	 	<td>
				   	 		<form action="<%=request.getContextPath() %>/Admin/Modify-Product-get-product" method="get">
				   	 			<input type="submit" value="<%=language.modify() %>"/>
				   	 			<input type="hidden" name="productID" value="<%=product.getProductID() %>"/>
				   	 			<input type="hidden" name="url" value="<%=request.getRequestURI().substring(request.getContextPath().length()) %>"/>
				   	 		</form>
				   	 	</td>
				  	</tr>
				  	</form>
				  	<%} %>
				</table>

				
			<%} %>
		<%} %>
	
	<%} %>

<%} else if(showContent.equals("addProducts")){%>


<%} %>

<%@ include file="navigationSlideAdminFooter.jsp" %>