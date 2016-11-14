package com.hiltondublin.servlets;
import com.hiltondublin.classes.*;
import com.hiltondublin.db.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class ModifyRoomGetRoomsServlet
 */
@WebServlet("/ModifyRoomGetRoomsServlet")
public class ModifyRoomGetRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String roomNumber = Helper.setNullIfEmptyString(request.getParameter("roomNumber"));
		String typeID = Helper.setNullIfEmptyString(request.getParameter("typeID"));
		String smoking = Helper.setNullIfEmptyString(request.getParameter("smoking"));
		String occupied = Helper.setNullIfEmptyString(request.getParameter("occupied"));
		
		boolean searchForRooms = true;
		
		if(roomNumber != null){
			if(!Helper.isInteger(roomNumber)){
				request.setAttribute("searchRoomErrorRoomNumber", "1");
				searchForRooms = false;
			}
		}
		
		if(typeID != null){
			if(!Helper.isInteger(typeID)){
				request.setAttribute("searchRoomErrorTypeID", "1");
				searchForRooms = false;
			}
		}
		
		if(smoking != null){
			if(!Helper.isBoolean(smoking)){
				request.setAttribute("searchRoomErrorSmoking", "1");
				searchForRooms = false;
			}
		}
		
		if(occupied != null){
			if(!Helper.isBoolean(occupied)){
				request.setAttribute("searchRoomErrorOccupied", "1");
				searchForRooms = false;
			}
		}
		
		if(searchForRooms){
			List<Room> rooms = dbConnection.getRooms(roomNumber, typeID, smoking, occupied, null);
			request.setAttribute("foundRooms", rooms);
		}
		
		request.setAttribute("lookedForRooms", "1");
		request.setAttribute("showContent", "searchRooms");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
