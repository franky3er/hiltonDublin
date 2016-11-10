package com.hiltondublin.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.service.ShowroomService;
import com.hiltondublin.users.Guest;
import com.hiltondublin.db.*;

/**
 * Servlet implementation class ShowroomServlet
 */
@WebServlet("/Showroom")
public class ShowroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();
		
		String guestID = request.getParameter("guestID");
		Guest guest = dbConnection.getGuests(guestID, null, null, null, null, null, null, null).get(0);
		Reservation reservation = new Reservation();
		reservation.setGuest(guest);
		reservation.setGuestID(guest.getGuestID());
		
		String ArrivalString = request.getParameter("arrivalString");
		String DepartureString = request.getParameter("departureString");
		
		SimpleDateFormat onlyDayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date ArrivalDate = onlyDayDateFormat.parse(ArrivalString);
			Date DepartureDate = onlyDayDateFormat.parse(DepartureString);
			
			reservation.setArrivalDate(ArrivalDate);
			reservation.setDepartureDate(DepartureDate);
			
		} catch (ParseException e1) {
			response.reset();
			return ;
		}
		
		List<Room> roomtype1 = new ArrayList<Room>();
		List<Room> roomtype2 = new ArrayList<Room>();
		List<Room> roomtype3 = new ArrayList<Room>();
		
		List<Room> roomforGuest = new ArrayList<Room>();
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		
		// count checked room
		if(!request.getParameter("type1").equals("null")) {
			String type1[] = request.getParameterValues("type1list");
			
			if(type1 != null) {
				for(String s : type1) {
					for(Room rooms : roomtype1) {
						if(rooms.getRoomNumber() == Integer.parseInt(s)) {
							roomforGuest.add(rooms);
						}
					}
					count1 ++;
				}
			}
		}
		
		if(!request.getParameter("type2").equals("null")) {
			String type2[] = request.getParameterValues("type2list");
			
			if(type2 != null) {
				for(String s : type2) {
					for(Room rooms : roomtype2) {
						if(rooms.getRoomNumber() == Integer.parseInt(s)) {
							roomforGuest.add(rooms);
						}
					}
					count2 ++;
				}
			}
		}
		
		if(!request.getParameter("type3").equals("null")) {
			String type3[] = request.getParameterValues("type3list");
			
			if(type3 != null) {
				for(String s : type3) {
					for(Room rooms : roomtype3) {
						if(rooms.getRoomNumber() == Integer.parseInt(s)) {
							roomforGuest.add(rooms);
						}
					}
					count3 ++;
				}
			}
		}
		
		int Type1 = Integer.parseInt(request.getParameter("Type1"));
		int Type2 = Integer.parseInt(request.getParameter("Type2"));
		int Type3 = Integer.parseInt(request.getParameter("Type3"));
		
		//check count of checked roomtype is same with user's input
		if(Type1 == count1 && Type2 == count2 && Type3 == count3)
		{
			ShowroomService shroom = new ShowroomService();
			try
			{
				reservation = shroom.reserveroom(roomforGuest, reservation);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("reservation", reservation);
			RequestDispatcher dispatcher = request.getRequestDispatcher("paid.jsp");
			dispatcher.forward(request, response);
			
			return ;
		}
		else
		{
			response.reset();
			return ;
		}
	}
}
