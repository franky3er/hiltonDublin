package com.hiltondublin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class DeleteSpecialPriceServlet
 */
@WebServlet("/DeleteSpecialPriceServlet")
public class DeleteSpecialPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		String roomTypeID = Helper.setNullIfEmptyString(request.getParameter("selectedRoomTypeID"));
		String date = Helper.setNullIfEmptyString(request.getParameter("date"));
		
		dbConnection.deleteSpecialPrices(roomTypeID, date + " 00:00:00", null, null, null);
		request.setAttribute("deleteSpecialPriceSuccessful", "1");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
