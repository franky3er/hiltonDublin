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
 * Servlet implementation class AddRoomToReservationServlet
 */
@WebServlet("/AddRoomToReservationServlet")
public class AddRoomToReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String bookinNumer = request.getParameter("bookingNumber");
		String smoking = request.getParameter("smoking");
		String typeID = request.getParameter("typeID");
		
		boolean assignRoom = true;
		
		Reservation reservation = dbConnection.getReservations(bookinNumer, null, null, null, null, null).get(0);
		List<Room> rooms = dbConnection.getAvailableRooms(Integer.parseInt(typeID), 1, reservation.getArrivalDate(), reservation.getDepartureDate(), Boolean.parseBoolean(smoking));
	
		if(rooms == null){
			request.setAttribute("addRoomToReservationError", "1");
			assignRoom = false;
		} else {
			if(rooms.isEmpty()){
				request.setAttribute("addRoomToReservationError", "1");
				assignRoom = false;
			}
		}
		
		if(assignRoom){
			Room room = rooms.get(0);
			if(dbConnection.assignRoomToReservation(room, reservation)!=null){
				request.setAttribute("addRoomToReservationSuccessful", "1");
			} else {
				request.setAttribute("addRoomToReservationError", "2");
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
