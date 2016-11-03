package com.hiltondublin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class checkout
 */
@WebServlet("/checkoutRoom")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = (String) request.getParameter("url");
		String roomNumber = (String) request.getParameter("roomNumber");
		if(roomNumber != null){
			request.setAttribute("roomNumber", roomNumber);
			if(!(roomNumber.isEmpty() || roomNumber.trim().equals(""))){
				if(roomNumber.matches("\\d+")){
					
				} else {
					System.out.println(roomNumber + " is not a number!");
					request.setAttribute("checkoutError", "1");
				}
			} else {
				System.out.println("Empty Room Number!");
				request.setAttribute("checkoutError", "3");
			}
		} else {
			System.out.println("Room Number is null!");
			request.setAttribute("checkoutError", "2");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
