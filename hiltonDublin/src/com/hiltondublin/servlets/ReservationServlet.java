package com.hiltondublin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
import com.hiltondublin.service.ReservationService;
import com.hiltondublin.users.Guest;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/Reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();
	private static final String KEY_ROOM_TYPE_ID = "roomTypeID";
	private static final String KEY_ROOM_TYPE_AMMOUNT = "roomTypeAmmoung";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Guest guest = new Guest();
		
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		
		String firstName = Helper.setNullIfEmptyString(request.getParameter("firstName"));
		String lastName = Helper.setNullIfEmptyString(request.getParameter("lastName"));
		String address = Helper.setNullIfEmptyString(request.getParameter("address"));
		String email = Helper.setNullIfEmptyString(request.getParameter("email"));
		String passportNr = Helper.setNullIfEmptyString(request.getParameter("passportNr"));
		String phone = Helper.setNullIfEmptyString(request.getParameter("phone"));
		String arrivalDate = request.getParameter("arrivalDate");
		String departureDate = request.getParameter("departureDate");
		String smoking = request.getParameter("smoking");
		String numberOfGuests = request.getParameter("numberOfGuests");
		
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("address", address);
		request.setAttribute("email", email);
		request.setAttribute("passportNr", passportNr);
		request.setAttribute("phone", phone);
		request.setAttribute("arrivalDate", arrivalDate);
		request.setAttribute("departureDate", departureDate);
		
		System.out.println(firstName);
		
		boolean reserve = true;
		
		if(firstName == null){
			request.setAttribute("reservationErrorFirstName", "1");
			reserve = false;
		}
		
		if(lastName == null){
			request.setAttribute("reservationErrorLastName", "1");
			reserve = false;
		}
		
		if(address == null){
			request.setAttribute("reservationErrorAddress", "1");
			reserve = false;
		}
		
		if(email != null){
			if(!Helper.validateEmail(email)){
				request.setAttribute("reservationErrorEmail", "2");
				reserve = false;
			}
		} else {
			request.setAttribute("reservationErrorEmail", "1");
			reserve = false;
		}
		
		if(passportNr != null){
			if(!Helper.isInteger(passportNr)){
				request.setAttribute("reservationErrorPassportNr", "2");
				reserve = false;
			}
		} else {
			request.setAttribute("reservationErrorPassportNr", "1");
			reserve = false;
		}
		
		if(!Helper.arrivalDateBeforeDepartureDate(arrivalDate, departureDate)){
			request.setAttribute("reservationErrorDate", "1");
			reserve = false;
		}
		
		HashMap<Integer, Integer> requestedRoomTypes = new HashMap<Integer, Integer>();
		
		int i = 1;
		while(true){
			String roomTypeID = request.getParameter(KEY_ROOM_TYPE_ID + i);
			String roomTypeAmmount = request.getParameter(KEY_ROOM_TYPE_AMMOUNT + i);
			if(roomTypeID != null && roomTypeAmmount != null){
				if(Integer.parseInt(roomTypeAmmount)>0){
					requestedRoomTypes.put(Integer.parseInt(roomTypeID), Integer.parseInt(roomTypeAmmount));
				}
			} else {
				break;
			}
			
			i++;
		}
		
		
		if(reserve){
			
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		
//		//check phone number is integer
//		if(request.getParameter("phonenr") != null && !helper.isInteger(request.getParameter("phonenr"))) {
//			PrintWriter writer = response.getWriter();
//			writer.println("<script type='text/javascript'>");
//			writer.println("alert('Please check your phone number!');");
//			writer.println("history.back();");
//			writer.println("</script>");
//			writer.flush();
//			
//			return;
//		}
//		else {
//			guest.setPassportNr(Integer.parseInt(request.getParameter("passportnr")));
//		}
//		
//		//check passport number is integer
//		if(!helper.isInteger(request.getParameter("passportnr")))
//		{
//			PrintWriter writer = response.getWriter();
//			writer.println("<script type='text/javascript'>");
//			writer.println("alert('Please check your passport number!');");
//			writer.println("history.back();");
//			writer.println("</script>");
//			writer.flush();
//			
//			return;
//		}
//		
//		guest.setLastName(request.getParameter("lastname"));
//		guest.setFirstName(request.getParameter("firstname"));
//		if(request.getParameter("email") != null) {
//			guest.setEmail(request.getParameter("email"));
//		}
//		guest.setAddress(request.getParameter("address"));
//		guest.setPhoneNumber(request.getParameter("phonenr"));
//		
//		Room room = new Room();
//		
//		if(request.getParameter("smoking").equals("true")) {
//			room.setSmoking(true);
//		}
//		else {
//			room.setSmoking(false);
//		}
//		
//		// number of each type rooms that user need
//		int[] Type = {Integer.parseInt(request.getParameter("numtype1")), Integer.parseInt(request.getParameter("numtype2")), Integer.parseInt(request.getParameter("numtype3"))};
//		
//		Reservation reservation = new Reservation();
//		
//		SimpleDateFormat onlyDayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		
//		String ArrivalString = request.getParameter("checkin");
//		String DepartureString = request.getParameter("checkout");
//		
//		//Check arrival date is faster than departure date
//		if(ArrivalString.compareTo(DepartureString) > 0)
//		{
//			PrintWriter writer = response.getWriter();
//			writer.println("<script type='text/javascript'>");
//			writer.println("alert('Please check arrivaldate and departuredate!');");
//			writer.println("history.back();");
//			writer.println("</script>");
//			writer.flush();
//			
//			return;
//		}
//
//		try {
//			Date ArrivalDate = onlyDayDateFormat.parse(ArrivalString);
//			Date DepartureDate = onlyDayDateFormat.parse(DepartureString);
//			
//			reservation.setArrivalDate(ArrivalDate);
//			reservation.setDepartureDate(DepartureDate);
//		} catch (ParseException e) {
//			response.sendRedirect("onlinereservation.jsp");
//			return ;
//		}
//		
//		if(request.getParameter("numberOfGuests") != null) {
//			int numofguest = Integer.parseInt(request.getParameter("numberOfGuests"));
//			
//			// check guest reserve proper amount of rooms
//			if(Type[0]*1+Type[1]*2+Type[2]*3 < numofguest) {
//				PrintWriter writer = response.getWriter();
//				writer.println("<script type='text/javascript'>");
//				writer.println("alert('You need to reserve proper amount of rooms!');");
//				writer.println("history.back();");
//				writer.println("</script>");
//				writer.flush();
//				return;
//			}
//		}
//		
//		ReservationService reserve = new ReservationService();
//		
//		guest = reserve.findGuestID(guest, Type[0]+Type[1]+Type[2]);
//		
//		if(guest.getGuestID() == -1) {
//			response.sendRedirect("onlinereservation.jsp");
//			return ;
//		}
//		else {
//			reservation = reserve.findReservationID(reservation, guest, room, Type);
//			request.setAttribute("reservation", reservation);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("paid.jsp");
//			dispatcher.forward(request, response);
//			
//			return ;
//		}
	}
}
