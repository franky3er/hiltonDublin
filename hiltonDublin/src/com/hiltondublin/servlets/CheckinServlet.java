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
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class CheckinServlet
 */
@WebServlet("/CheckinServlet")
public class CheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String selectedReservationID = Helper.setNullIfEmptyString(request.getParameter("selectedReservationID"));
		
		if(selectedReservationID != null){
			List<Reservation> reservations = dbConnection.getReservations(selectedReservationID, null, null, null, null, null);
			if(reservations != null){
				if(!reservations.isEmpty()){
					Reservation reservation = reservations.get(0);
					List<Room> rooms = reservation.getRooms();
					
					for(Room room : rooms){
						room.setOccupied(true);
					}
					
					dbConnection.updateReservation(reservation);
					
					request.setAttribute("checkinReservationSuccessful", "1");
				} else {
					request.setAttribute("checkinReservationFailed", "1");
				}
			} else {
				request.setAttribute("checkinReservationFailed", "1");
			}
		} else {
			request.setAttribute("checkinReservationFailed", "1");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
