package com.hiltondublin.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Room;
import com.hiltondublin.service.ShowroomService;
import com.hiltondublin.users.GuestSingleton;
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
		
		GuestSingleton guestinfo = GuestSingleton.getInstatnce();
		
		List<Room> roomtype1 = new ArrayList<Room>();
		List<Room> roomtype2 = new ArrayList<Room>();
		List<Room> roomtype3 = new ArrayList<Room>();
		
		String type1[] = request.getParameterValues("type1");
		String type2[] = request.getParameterValues("type2");
		String type3[] = request.getParameterValues("type3");
		
		List<Room> roomforGuest = new ArrayList<Room>();
	
		roomtype1 = dbConnection.getRooms(null, "1", guestinfo.getSmoking(), null, null);
		roomtype2 = dbConnection.getRooms(null, "2", guestinfo.getSmoking(), null, null);
		roomtype3 = dbConnection.getRooms(null, "3", guestinfo.getSmoking(), null, null);
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		
		
		// count checked room
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
		
		if(type3 != null) {
			for(String s : type3) {
				for(Room rooms : roomtype1) {
					if(rooms.getRoomNumber() == Integer.parseInt(s)) {
						roomforGuest.add(rooms);
					}
				}
				count3 ++;
			}
		}
		
		
		//check count of checked roomtype is same with user's input
		if(guestinfo.getType1() == count1 && guestinfo.getType2() == count2 && guestinfo.getType3() == count3) // if guest doesn't need type1 room
		{
			ShowroomService shroom = new ShowroomService();
			try
			{
				shroom.reserveroom(roomforGuest);
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("paid.jsp");
			return ;
		}
		else
		{
			response.reset();
			return ;
		}
	}
}
