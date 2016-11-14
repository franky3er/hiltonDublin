package com.hiltondublin.servlets;

import java.io.IOException;
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
 * Servlet implementation class ModifyRoomServlet
 */
@WebServlet("/ModifyRoomServlet")
public class ModifyRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String roomNumber = Helper.setNullIfEmptyString(request.getParameter("roomNumber"));
		String newRoomNumber = Helper.setNullIfEmptyString(request.getParameter("newRoomNumber"));
		String typeID = Helper.setNullIfEmptyString(request.getParameter("typeID"));
		String smoking = Helper.setNullIfEmptyString(request.getParameter("smoking"));
		String occupied = Helper.setNullIfEmptyString(request.getParameter("occupied"));
		
		boolean updateRoom = true;
		
		Room room = dbConnection.getRooms(roomNumber, null, null, null, null).get(0);
		
		if(newRoomNumber!=null){
			if(Helper.isInteger(newRoomNumber)){
				List<Room> rooms = dbConnection.getRooms(newRoomNumber, null, null, null, null);
				if(rooms!=null){
					if(rooms.isEmpty()){
						request.setAttribute("modifyRoomErrorRoomNumber", "3");
						updateRoom = false;
					}
				} else {
					request.setAttribute("modifyRoomErrorRoomNumber", "3");
					updateRoom = false;
				}
			} else {
				request.setAttribute("modifyRoomErrorRoomNumber", "2");
				updateRoom = false;
			}
		} else {
			request.setAttribute("modifyRoomErrorRoomNumber", "1");
			updateRoom = false;
		}
		
		
		if(updateRoom){
			room.setRoomNumber(Integer.parseInt(newRoomNumber));
			room.setTypeID(Integer.parseInt(typeID));
			room.setSmoking(Boolean.parseBoolean(smoking));
			room.setOccupied(Boolean.parseBoolean(occupied));
			
			if(dbConnection.updateRoom(roomNumber, room)>0){
				request.setAttribute("modifyRoomSuccessful", 1);
				room = dbConnection.getRooms(newRoomNumber, null, null, null, null).get(0);
			} else {
				request.setAttribute("modifyRoomFailed", 1);
				room = dbConnection.getRooms(roomNumber, null, null, null, null).get(0);
			}
		} else {
			room = dbConnection.getRooms(roomNumber, null, null, null, null).get(0);
		}
		
		request.setAttribute("selectedRoom", room);
		request.setAttribute("showContent", "modifyRoom");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
