package com.hiltondublin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.hiltondublin.helper.Helper;
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
		Helper helper = new Helper();
		Guest guest = new Guest();
		
		//check phone number is integer
		if(request.getParameter("phonenr") != null && !helper.isInteger(request.getParameter("phonenr"))) {
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('Please check your phone number!');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			
			return;
		}
		else {
			guest.setPassportNr(Integer.parseInt(request.getParameter("passportnr")));
		}
		
		//check passport number is integer
		if(!helper.isInteger(request.getParameter("passportnr")))
		{
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('Please check your passport number!');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			
			return;
		}
		
		guest.setLastName(request.getParameter("lastname"));
		guest.setFirstName(request.getParameter("firstname"));
		guest.setEmail(request.getParameter("email"));
		guest.setAddress(request.getParameter("address"));
		guest.setPhoneNumber(request.getParameter("phonenr"));
		
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
		
		//Check arrival date is faster than departure date
		if(ArrivalString.compareTo(DepartureString) > 0)
		{
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('Please check arrivaldate and departuredate!');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			
			return;
		}

		try {
			Date ArrivalDate = onlyDayDateFormat.parse(ArrivalString);
			Date DepartureDate = onlyDayDateFormat.parse(DepartureString);
			
			reservation.setArrivalDate(ArrivalDate);
			reservation.setDepartureDate(DepartureDate);
		} catch (ParseException e) {
			response.sendRedirect("onlinereservation.jsp");
			return ;
		}
		
		if(request.getParameter("numberOfGuests") != null) {
			int numofguest = Integer.parseInt(request.getParameter("numberOfGuests"));
			
			// check guest reserve proper amount of rooms
			if(Type[0]*1+Type[1]*2+Type[2]*3 < numofguest) {
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('You need to reserve proper amount of rooms!');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
				return;
			}
		}
		
		ReservationService reserve = new ReservationService();
		
		guest = reserve.findGuestID(guest, Type[0]+Type[1]+Type[2]);
		
		if(guest.getGuestID() == -1) {
			response.sendRedirect("onlinereservation.jsp");
			return ;
		}
		else {
			reservation = reserve.findReservationID(reservation, guest, room, Type);
			request.setAttribute("reservation", reservation);
			RequestDispatcher dispatcher = request.getRequestDispatcher("paid.jsp");
			dispatcher.forward(request, response);
			
			return ;
		}
	}
}
