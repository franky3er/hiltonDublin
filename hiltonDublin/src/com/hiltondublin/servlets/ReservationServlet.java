package com.hiltondublin.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.service.ReservationService;
import com.hiltondublin.users.Guest;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/Reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
		
		Guest guest = new Guest();

		guest.setLastName(request.getParameter("lastname"));
		guest.setFirstName(request.getParameter("firstname"));
		guest.setPhoneNumber(request.getParameter("phonenr"));
		guest.setEmail(request.getParameter("email"));
		guest.setAddress(request.getParameter("address"));
		guest.setPassportNr(Integer.parseInt(request.getParameter("passportnr")));
		
		Room room = new Room();
		if(request.getParameter("smoking").equals("true")) {
			room.setSmoking(true);
		}
		else {
			room.setSmoking(false);
		}
		
		// number of each type rooms that user need
		int[] Type = {Integer.parseInt(request.getParameter("numtype1")), Integer.parseInt(request.getParameter("numtype2")), Integer.parseInt(request.getParameter("numtype3"))};
		
		Reservation reservation = new Reservation();
		
		SimpleDateFormat onlyDayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String ArrivalString = request.getParameter("checkin");
		String DepartureString = request.getParameter("checkout");

		try {
			Date ArrivalDate = onlyDayDateFormat.parse(ArrivalString);
			Date DepartureDate = onlyDayDateFormat.parse(DepartureString);
			
			reservation.setArrivalDate(ArrivalDate);
			reservation.setDepartureDate(DepartureDate);
			
		} catch (ParseException e) {
			response.sendRedirect("onlinereservation.jsp");
			
			return ;
		}
		
		// checking passport-number is just number
		int passportcheck = guest.getPassportNr();
		while(passportcheck != 0)
		{
			if(passportcheck%10 >= 0 && passportcheck%10 < 10)
			{
				passportcheck = passportcheck / 10;
			}
			else
			{
				System.out.println("Passport number is wrong!!");
				return ;
			}
		}
		ReservationService reserve = new ReservationService();
		
		guest = reserve.findGuestID(guest, Type[0]+Type[1]+Type[2]);
		
		if(guest.getGuestID() == -1) {
			response.sendRedirect("onlinereservation.jsp");
			
			return ;
		}
		else {
			request.setAttribute("guest", guest);
			request.setAttribute("room", room);
			request.setAttribute("reservation", reservation);
			request.setAttribute("Type", Type);
			RequestDispatcher dispatcher = request.getRequestDispatcher("onlinereservationsh.jsp");
			dispatcher.forward(request, response);
			
			return ;
		}
	}
}
