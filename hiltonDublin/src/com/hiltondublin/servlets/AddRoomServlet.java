package com.hiltondublin.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Room;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class ModifyRoomAddRoomServlet
 */
@WebServlet("/ModifyRoomAddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String roomNumber = Helper.setNullIfEmptyString(request.getParameter("roomNumber"));
		String typeID = Helper.setNullIfEmptyString(request.getParameter("typeID"));
		String smoking = Helper.setNullIfEmptyString(request.getParameter("smoking"));
		String occupied = Helper.setNullIfEmptyString(request.getParameter("occupied"));
		
		boolean insertRoom = true;
		
		if(roomNumber != null){
			if(Helper.isInteger(roomNumber)){
				List<Room> rooms = dbConnection.getRooms(roomNumber, null, null, null, null);
				if(rooms != null){
					if(!rooms.isEmpty()){
						request.setAttribute("addRoomErrorRoomNumber", "3");
						insertRoom = false;
					}
				} 
			} else {
				request.setAttribute("addRoomErrorRoomNumber", "2");
				insertRoom = false;
			}
		} else {
			request.setAttribute("addRoomErrorRoomNumber", "1");
			insertRoom = false;
		}
		
		
		if(insertRoom){
			Room room = new Room();
			room.setRoomNumber(Integer.parseInt(roomNumber));
			room.setTypeID(Integer.parseInt(typeID));
			room.setSmoking(Boolean.parseBoolean(smoking));
			room.setOccupied(Boolean.parseBoolean(occupied));
			
			ResultSet rs = dbConnection.insertRoom(room);
			if(rs != null){
				request.setAttribute("addRoomSuccessful", "1");
				room = dbConnection.getRooms(roomNumber, null, null, null, null).get(0);
				request.setAttribute("addedRoom", room);
			} else {
				request.setAttribute("addRoomFailed", "1");
			}
		}
		
		request.setAttribute("showContent", "addRoom");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
