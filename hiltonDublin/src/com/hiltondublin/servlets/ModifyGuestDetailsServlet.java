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
import com.hiltondublin.users.Guest;

/**
 * Servlet implementation class ModifyGuestDetailsServlet
 */
@WebServlet("/ModifyGuestDetailsServlet")
public class ModifyGuestDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String bookinNumber = request.getParameter("bookingNumber");
		String guestID = request.getParameter("guestID");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String passportNr = request.getParameter("passportNr");
		
		boolean updateGuest = true;
		
		Guest guest = dbConnection.getGuests(guestID, null, null, null, null, null, null, null).get(0);
		
		if(firstName != null){
			if(!firstName.trim().equals("")){
				guest.setFirstName(firstName);
			}
			else {
				request.setAttribute("modifyErrorFirstName", "1");
				updateGuest = false;
			}
		} else {
			request.setAttribute("modifyErrorFirstName", "1");
			updateGuest = false;
		}
		
		if(lastName != null){
			if(!lastName.trim().equals("")){
				guest.setLastName(lastName);
			}
			else {
				request.setAttribute("modifyErrorLastName", "1");
				updateGuest = false;
			}
		} else {
			request.setAttribute("modifyErrorLastName", "1");
			updateGuest = false;
		}
		
		
		
		Reservation reservation = dbConnection.getReservations(bookinNumber, null, null, null, null, null).get(0);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
