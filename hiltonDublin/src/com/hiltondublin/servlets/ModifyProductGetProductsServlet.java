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
 * Servlet implementation class ModifyProductGetProductsServlet
 */
@WebServlet("/ModifyProductGetProductsServlet")
public class ModifyProductGetProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String productID = Helper.setNullIfEmptyString(request.getParameter("productID"));
		String productName = Helper.setNullIfEmptyString(request.getParameter("productName"));
		String price = Helper.setNullIfEmptyString(request.getParameter("price"));
		
		boolean searchForProducts = true;
		
		if(productID != null){
			if(!Helper.isInteger(productID)){
				request.setAttribute("searchProductErrorProductID", "1");
				searchForProducts = false;
			}
		}
		
		if(price != null){
			if(!Helper.isDouble(price)){
				request.setAttribute("searchProductErrorPrice", "1");
				searchForProducts = false;
			}
		}
		
		if(searchForProducts){
			List<ConsumerProduct> products = dbConnection.getConsumerProducts(productID, productName, price, null);
			request.setAttribute("foundProducts", products);
		}
		
		request.setAttribute("lookedForProducts", "1");
		request.setAttribute("showContent", "searchProducts");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
