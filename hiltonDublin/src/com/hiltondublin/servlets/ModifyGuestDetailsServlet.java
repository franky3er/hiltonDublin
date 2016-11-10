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
import com.hiltondublin.helper.Helper;
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
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		String bookinNumber = Helper.setNullIfEmptyString(request.getParameter("bookingNumber"));
		String guestID = Helper.setNullIfEmptyString(request.getParameter("guestID"));
		String firstName = Helper.setNullIfEmptyString(request.getParameter("firstName"));
		String lastName = Helper.setNullIfEmptyString(request.getParameter("lastName"));
		String phoneNumber = Helper.setNullIfEmptyString(request.getParameter("phoneNumber"));
		String email = Helper.setNullIfEmptyString(request.getParameter("email"));
		String address = Helper.setNullIfEmptyString(request.getParameter("address"));
		String passportNr = Helper.setNullIfEmptyString(request.getParameter("passportNr"));
		
		boolean updateGuest = true;
		
		Guest guest = dbConnection.getGuests(guestID, null, null, null, null, null, null, null).get(0);
		
		if(firstName == null) {
			request.setAttribute("modifyGuestDetailsErrorFirstName", "1");
			updateGuest = false;
		}
		
		if(lastName == null) {
			request.setAttribute("modifyGuestDetailsErrorLastName", "1");
			updateGuest = false;
		}
		
		if(email != null){
			if(!Helper.validateEmail(email)){
				request.setAttribute("modifyGuestDetailsErrorEmail", "2");
				updateGuest = false;
			}
		}
		
		if(passportNr != null){
			if(!Helper.isInteger(passportNr)){
				request.setAttribute("modifyGuestDetailsErrorPassportNr", "2");
				updateGuest = false;
			}
		} else {
			request.setAttribute("modifyGuestDetailsErrorPassportNr", "1");
			updateGuest = false;
		}
		
		
		if(updateGuest){
			guest.setFirstName(firstName);
			guest.setLastName(lastName);
			guest.setPhoneNumber(phoneNumber);
			guest.setEmail(email);
			guest.setAddress(address);
			guest.setPassportNr(Integer.parseInt(passportNr));
			
			if(0 < dbConnection.updateGuest(guest)){
				request.setAttribute("modifyGuestDetailsSuccessful", "1");
			}
		}
		
		
		guest = dbConnection.getGuests(guestID, null, null, null, null, null, null, null).get(0);
		Reservation reservation = dbConnection.getReservations(bookinNumber, null, null, null, null, null).get(0);
		
		request.setAttribute("guest", guest);
		request.setAttribute("reservation", reservation);
		request.setAttribute("showContent", "showReservation");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
