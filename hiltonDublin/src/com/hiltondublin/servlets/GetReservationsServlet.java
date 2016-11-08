package com.hiltondublin.servlets;
import com.hiltondublin.users.*;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp.DbcpException;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.db.HiltonDublinDBConnection;

/**
 * Servlet implementation class GetReservationsServlet
 */
@WebServlet("/GetReservationsServlet")
public class GetReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		
		String bookingNumber = request.getParameter("bookingNumber");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String arrivalDate = request.getParameter("arrivalDate");
		String departureDate = request.getParameter("departureDate");
		
		if(bookingNumber.trim().equals("")){
			bookingNumber = null;
		}
		if(firstName.trim().equals("")){
			firstName = null;
		}
		if(lastName.trim().equals("")){
			lastName = null;
		}
		if(arrivalDate.trim().equals("")){
			arrivalDate = null;
		}
		if(departureDate.trim().equals("")){
			departureDate = null;
		}
		
		System.out.println("Arrival Date: " + arrivalDate);
		
		List<Guest> guests = null;
		
		boolean searchForRooms = true;
		
		if(bookingNumber != null){
			if(!bookingNumber.matches("\\d+")){
				request.setAttribute("error", "1");
				searchForRooms = false;
			}
		} 
		
		if(arrivalDate != null){
			arrivalDate += " 00:00:00";
		}
		
		if(departureDate != null){
			departureDate += " 00:00:00";
		}
		
		if(firstName != null || lastName != null){
			guests = dbConnection.getGuests(null, firstName, lastName, null, null, null, null, null);
		}
		
		if(searchForRooms){
			List<Reservation> reservations = new ArrayList<Reservation>();
			if(guests == null){
				reservations = dbConnection.getReservations(bookingNumber, null, arrivalDate, departureDate, null, null);
			} else {
				for(Guest guest : guests){
					reservations.addAll(dbConnection.getReservations(bookingNumber, Integer.toString(guest.getGuestID()), arrivalDate, departureDate, null, null));
				}
			}
			if(!reservations.isEmpty()){
				request.setAttribute("reservations", reservations);
				request.setAttribute("showContent", "showReservations");
			} else {
				request.setAttribute("error", "2");
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
