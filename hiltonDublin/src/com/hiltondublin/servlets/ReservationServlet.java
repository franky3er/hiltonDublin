package com.hiltondublin.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final String KEY_ROOM_TYPE_AMMOUNT = "roomTypeAmmount";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Guest guest = new Guest();
		
		boolean isEmployee = false;
		
		if(request.getParameter("isEmployee") != null){
			isEmployee = Boolean.parseBoolean(request.getParameter("isEmployee"));
		} else {
			response.sendRedirect("/");
		}
		
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
			if(!isEmployee){
				request.setAttribute("reservationErrorEmail", "1");
				reserve = false;
			}
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
			System.out.println(roomTypeID + " : " + roomTypeAmmount);
			if(roomTypeID != null && roomTypeAmmount != null){
				if(Integer.parseInt(roomTypeAmmount)>0){
					requestedRoomTypes.put(new Integer(Integer.parseInt(roomTypeID)), new Integer(Integer.parseInt(roomTypeAmmount)));
				}
			} else {
				break;
			}
			
			i++;
		}
		
		if(requestedRoomTypes.isEmpty()){
			request.setAttribute("reservationErrorNoRoomsSelected", "1");
			reserve = false;
		}
		
		List<Room> assignedRooms = new ArrayList<Room>();
		
		for(Map.Entry<Integer, Integer> requestedRoomType : requestedRoomTypes.entrySet()){
			int roomTypeID = requestedRoomType.getKey();
			int ammount = requestedRoomType.getValue();
			
			try {
				List<Room> assignedRoomsFromRoomType = dbConnection.getAvailableRooms(roomTypeID, ammount, dbConnection.onlyDayDateFormat.parse(arrivalDate), dbConnection.onlyDayDateFormat.parse(departureDate), Boolean.parseBoolean(smoking));
				
				if(assignedRoomsFromRoomType != null){
					assignedRooms.addAll(assignedRoomsFromRoomType);
				} else {
					request.setAttribute("reservationErrorNotEnoughRooms", "1");
					reserve = false;
					break;
				}
			} catch (ParseException e) {
				System.out.println("Parse Date ERROR : ReservationServlet.java");
				request.setAttribute("reservationSystemError", "1");
				reserve = false;
			}
		}
		
		
		if(reserve){
			
			ResultSet rsGuest = dbConnection.insertGuest(null, firstName, lastName, phone, email, address, passportNr);
			String guestID = null;
			try {
				if(rsGuest.next()){
					guestID = rsGuest.getString(1);
					
					ResultSet rsReservation = dbConnection.insertReservation(null, guestID, arrivalDate + " 00:00:00", departureDate + " 00:00:00", "false");
					String bookingNumber = null;
					if(rsReservation.next()){
						bookingNumber = rsReservation.getString(1);
						
						for(Room assignedRoom : assignedRooms){
							dbConnection.assignRoomToReservation(Integer.toString(assignedRoom.getRoomNumber()), bookingNumber);
						}
						
						request.setAttribute("bookingNumber", bookingNumber);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("Online-Reservation-payment");
						dispatcher.forward(request, response);
					}
				}
			} catch (SQLException e) {
				
				System.out.println("ResultSet ERROR : ReservationServlet.java");
				
				request.setAttribute("reservationSystemError", "2");
			}
			
		} 
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
		
		
	}
}
