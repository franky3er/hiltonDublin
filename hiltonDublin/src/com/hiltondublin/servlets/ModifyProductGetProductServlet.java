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
 * Servlet implementation class ModifyProductGetProductServlet
 */
@WebServlet("/ModifyProductGetProductServlet")
public class ModifyProductGetProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String productID = Helper.setNullIfEmptyString(request.getParameter("productID"));
		
		ConsumerProduct product = null;
		
		System.out.println("Product ID : " + productID);
		
		if(productID != null){
			if(Helper.isInteger(productID)){
				List<ConsumerProduct> products = dbConnection.getConsumerProducts(productID, null, null, null);
				if(products != null){
					product = products.get(0);
				}
			}
		}
		
		
		request.setAttribute("selectedProduct", product);
		request.setAttribute("showContent", "modifyProduct");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
