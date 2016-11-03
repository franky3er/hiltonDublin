package com.hiltondublin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.service.ReservationService;
import com.hiltondublin.users.Guest;
import com.hiltondublin.users.GuestSingleton;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/Reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Guest guest = new Guest();
		GuestSingleton guestinfo = GuestSingleton.getInstatnce();

		guest.setLastName(request.getParameter("lastname"));
		guest.setFirstName(request.getParameter("firstname"));
		guest.setPhoneNumber(request.getParameter("phonenr"));
		guest.setEmail(request.getParameter("email"));
		guest.setAddress(request.getParameter("address"));
		guest.setPassportNr(Integer.parseInt(request.getParameter("passportnr")));
		
		guestinfo.setLastName(request.getParameter("lastname"));
		guestinfo.setFirstName(request.getParameter("firstname"));
		guestinfo.setPhoneNumber(request.getParameter("phonenr"));
		guestinfo.setEmail(request.getParameter("email"));
		guestinfo.setAddress(request.getParameter("address"));
		guestinfo.setPassportNr(Integer.parseInt(request.getParameter("passportnr")));
		guestinfo.setSmoking(request.getParameter("smoking"));
		guestinfo.setCheckin(request.getParameter("checkin"));
		guestinfo.setCheckout(request.getParameter("checkout"));
		guestinfo.setGuests(Integer.parseInt(request.getParameter("numberOfGuests")));
		guestinfo.setType1(Integer.parseInt(request.getParameter("numtype1")));
		guestinfo.setType2(Integer.parseInt(request.getParameter("numtype2")));
		guestinfo.setType3(Integer.parseInt(request.getParameter("numtype3")));
		
		
		int roomtype1 = Integer.parseInt(request.getParameter("numtype1"));
		int roomtype2 = Integer.parseInt(request.getParameter("numtype2"));
		int roomtype3 = Integer.parseInt(request.getParameter("numtype3"));
		
		ReservationService reserve = new ReservationService();
		
		guest = reserve.findGuestID(guest, roomtype1 + roomtype2 + roomtype3);
		
		guestinfo.setGuestID(guest.getGuestID());
		
		if(guest.getGuestID() == -1) {
			response.sendRedirect("onlinereservation.jsp");
			
			return ;
		}
		else {
			response.sendRedirect("onlinereservationsh.jsp");
			
			return ;
		}
	}

}
