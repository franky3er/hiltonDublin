package com.hiltondublin.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String productID = Helper.setNullIfEmptyString(request.getParameter("productID"));
		String productName = Helper.setNullIfEmptyString(request.getParameter("productName"));
		String price = Helper.setNullIfEmptyString(request.getParameter("price"));
		
		boolean addProduct = true;
		
		if(productID != null){
			if(Helper.isInteger(productID)){
				if(!productID.equals(productID)){
					List<ConsumerProduct> products = dbConnection.getConsumerProducts(productID, null, null, null);
					
					if(products != null){
						if(!products.isEmpty()){
							request.setAttribute("modifyProductErrorProductID", "3");
							addProduct = false;
						}
					}
				}
			} else {
				request.setAttribute("modifyProductErrorProductID", "2");
				addProduct = false;
			}
		} 
		
		if(productName == null){
			request.setAttribute("modifyProductErrorProductName", "1");
			addProduct = false;
		}
		
		if(price != null){
			if(!Helper.isDouble(price)){
				request.setAttribute("modifyProductErrorPrice", "2");
				addProduct = false;
			}
		} else {
			request.setAttribute("modifyProductErrorPrice", "1");
			addProduct = false;
		}
		
		
		if(addProduct){
			ResultSet rs = dbConnection.insertConsumerProduct(productID, productName, price);
			
			if(rs != null){
				request.setAttribute("addProductSuccessful", "1");
				try {
					if(rs.next()){
						productID = rs.getString(1);
						ConsumerProduct product = dbConnection.getConsumerProducts(productID, null, null, null).get(0);
						request.setAttribute("addedProduct", product);
					}
				} catch (SQLException e) {
					request.setAttribute("addProductFailed", "1");
				}
			} else {
				request.setAttribute("addProductFailed", "1");
			}
			
		}
		
		
		request.setAttribute("showContent", "addProduct");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
