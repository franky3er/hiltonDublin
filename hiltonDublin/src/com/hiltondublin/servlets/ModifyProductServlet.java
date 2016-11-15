package com.hiltondublin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.ConsumerProduct;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet("/ModifyProductServlet")
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		
		request.setAttribute("selectedProduct", product);
		request.setAttribute("showContent", "modifyProduct");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
