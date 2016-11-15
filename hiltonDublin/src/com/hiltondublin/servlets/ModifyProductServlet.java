package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.ConsumerProduct;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet("/ModifyProductServlet")
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String productID = Helper.setNullIfEmptyString(request.getParameter("productID"));
		String newProductID = Helper.setNullIfEmptyString(request.getParameter("newProductID"));
		String productName = Helper.setNullIfEmptyString(request.getParameter("productName"));
		String price = Helper.setNullIfEmptyString(request.getParameter("price"));
		
		ConsumerProduct product = null;
		List<ConsumerProduct> consumerProducts = dbConnection.getConsumerProducts(productID, null, null, null);
		if(consumerProducts!=null){
			product = consumerProducts.get(0);
		}
		
		boolean modifyProduct = true;
		
		if(newProductID != null){
			if(Helper.isInteger(newProductID)){
				List<ConsumerProduct> products = dbConnection.getConsumerProducts(newProductID, null, null, null);
				
				if(products != null){
					if(!products.isEmpty()){
						request.setAttribute("modifyProductErrorProductID", "3");
						modifyProduct = false;
					}
				}
			} else {
				request.setAttribute("modifyProductErrorProductID", "2");
				modifyProduct = false;
			}
		} else {
			request.setAttribute("modifyProductErrorProductID", "1");
			modifyProduct = false;
		}
		
		
		if(productName == null){
			request.setAttribute("modifyProductErrorProductName", "1");
			modifyProduct = false;
		}
		
		if(price != null){
			if(!Helper.isDouble(price)){
				request.setAttribute("modifyProductErrorPrice", "2");
				modifyProduct = false;
			}
		} else {
			request.setAttribute("modifyProductErrorPrice", "1");
			modifyProduct = false;
		}
		
		
		
		
		if(product!=null){
			if(modifyProduct){
				product.setProductID(Integer.parseInt(newProductID));
				product.setName(productName);
				product.setPrice(Double.parseDouble(price));
				
				if(dbConnection.updateConsumerProduct(productID, product)>0){
					request.setAttribute("modifyProductSuccessful", "1");
					product = dbConnection.getConsumerProducts(newProductID, null, null, null).get(0);
				} else {
					request.setAttribute("modifyProductFailed", "1");
					product = dbConnection.getConsumerProducts(productID, null, null, null).get(0);
				}
			} else {
				
			}
		}
		
		
		
		
		request.setAttribute("selectedProduct", product);
		request.setAttribute("showContent", "modifyProduct");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
