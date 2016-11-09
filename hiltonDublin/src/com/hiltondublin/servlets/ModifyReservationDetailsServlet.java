package com.hiltondublin.servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.db.HiltonDublinDBConnection;

/**
 * Servlet implementation class ModifyReservationDetailsServlet
 */
@WebServlet("/ModifyReservationDetailsServlet")
public class ModifyReservationDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String bookinNumer = request.getParameter("bookingNumber");
		String guestID = request.getParameter("guestID");
		String arrivalDate = request.getParameter("arrivalDate");
		String departureDate = request.getParameter("departureDate");
		
		Reservation reservation = dbConnection.getReservations(bookinNumer, null, null, null, null, null).get(0);
		if(reservation == null){
			request.setAttribute("modifyReservationDetailsError", "1");
		} else {
			try {
				reservation.setArrivalDate(dbConnection.onlyDayDateFormat.parse(arrivalDate));
				reservation.setDepartureDate(dbConnection.onlyDayDateFormat.parse(departureDate));
			} catch (ParseException e) {
				request.setAttribute("modifyReservationDetailsError", "2");
			}
			
			int lines = dbConnection.updateReservation(reservation);
			if(lines==0){
				request.setAttribute("modifyReservationDeatilsError", "3");
			} else {
				request.setAttribute("modifyReservationDetailsSuccess", "1");
			}
		}
		
		reservation = dbConnection.getReservations(bookinNumer, null, null, null, null, null).get(0);
		if(reservation != null){
			request.setAttribute("reservation", reservation);
			request.setAttribute("showContent", "showReservation");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
