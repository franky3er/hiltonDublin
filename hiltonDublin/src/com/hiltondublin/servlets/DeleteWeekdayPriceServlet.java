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
 * Servlet implementation class DeleteWeekdayPriceServlet
 */
@WebServlet("/DeleteWeekdayPriceServlet")
public class DeleteWeekdayPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		String roomTypeID = Helper.setNullIfEmptyString(request.getParameter("selectedRoomTypeID"));
		String weekDay = Helper.setNullIfEmptyString(request.getParameter("weekDay"));
		
		dbConnection.deleteWeekdayPrices(roomTypeID, null, weekDay, null);
		request.setAttribute("deleteWeekdayPriceSuccessful", "1");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
