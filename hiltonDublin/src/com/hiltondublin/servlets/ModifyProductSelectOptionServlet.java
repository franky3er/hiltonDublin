package com.hiltondublin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class ModifyProductSelectOptionServlet
 */
@WebServlet("/ModifyProductSelectOptionServlet")
public class ModifyProductSelectOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		String optionSelection = Helper.setNullIfEmptyString(request.getParameter("optionSelection"));
		
		if(optionSelection != null){
			if(optionSelection.equals("modifyProduct")){
				request.setAttribute("showContent", "searchProducts");
			} else if(optionSelection.equals("addProduct")){
				request.setAttribute("showContent", "addProduct");
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
