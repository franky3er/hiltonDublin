package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.db.HiltonDublinDBConnection;

/**
 * Servlet implementation class AddProductToReservationServlet
 */
@WebServlet("/AddProductToReservationServlet")
public class AddProductToReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String bookinNumer = request.getParameter("bookingNumber");
		String productID = request.getParameter("productID");
		
		Reservation reservation = dbConnection.getReservations(bookinNumer, null, null, null, null, null).get(0);
		
		dbConnection.assignProductToReservation(null, productID, bookinNumer);
		request.setAttribute("addProductToReservationSuccessful", "1");
		
		reservation = dbConnection.getReservations(bookinNumer, null, null, null, null, null).get(0);
		if(reservation != null){
			request.setAttribute("reservation", reservation);
			request.setAttribute("showContent", "showReservation");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
