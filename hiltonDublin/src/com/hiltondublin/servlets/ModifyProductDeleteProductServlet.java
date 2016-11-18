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
 * Servlet implementation class ModifyProductDeleteProductServlet
 */
@WebServlet("/ModifyProductDeleteProductServlet")
public class ModifyProductDeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String productID = Helper.setNullIfEmptyString(request.getParameter("productID"));
		String searchedProductID = Helper.setNullIfEmptyString(request.getParameter("searchedProductID"));
		String searchedProductName = Helper.setNullIfEmptyString(request.getParameter("searchedProductName"));
		String searchedPrice = Helper.setNullIfEmptyString(request.getParameter("searchedPrice"));
		
		if(dbConnection.deleteConsumerProducts(productID, null, null, null) > 0){
			request.setAttribute("deleteProductSuccessful", "1");
		} else {
			request.setAttribute("deleteProductFailed", "1");
		}
		
		List<ConsumerProduct> products = dbConnection.getConsumerProducts(searchedProductID, searchedProductName, searchedPrice, null);
		request.setAttribute("foundProducts", products);
				
		request.setAttribute("lookedForProducts", "1");
		request.setAttribute("showContent", "searchProducts");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
