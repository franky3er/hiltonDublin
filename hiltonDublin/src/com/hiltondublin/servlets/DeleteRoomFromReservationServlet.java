package com.hiltondublin.servlets;
import com.hiltondublin.classes.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.db.HiltonDublinDBConnection;

/**
 * Servlet implementation class DeleteRoomServlet
 */
@WebServlet("/DeleteRoomServlet")
public class DeleteRoomFromReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String bookinNumber = request.getParameter("bookingNumber");
		String roomNumber = request.getParameter("roomNumber");
		
		if(0<dbConnection.deleteReserved_Rooms(roomNumber, bookinNumber, null)){
			request.setAttribute("deleteRoomSuccessful", "1");
		} else {
			request.setAttribute("deleteRoomError", "1");
		}
		
		Reservation reservation = dbConnection.getReservations(bookinNumber, null, null, null, null, null).get(0);
		if(reservation != null){
			request.setAttribute("reservation", reservation);
			request.setAttribute("showContent", "showReservation");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
