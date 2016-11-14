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
 * Servlet implementation class ModifyRoomGetRoomServlet
 */
@WebServlet("/ModifyRoomGetRoomServlet")
public class ModifyRoomGetRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String roomNumber = Helper.setNullIfEmptyString(request.getParameter("roomNumber"));
		
		Room room = null;
		
		if(roomNumber != null){
			if(Helper.isInteger(roomNumber)){
				List<Room> rooms = dbConnection.getRooms(roomNumber, null, null, null, null);
				if(rooms!=null){
					room = rooms.get(0);
				}
			} 
		} 
		
		request.setAttribute("selectedRoom", room);
		request.setAttribute("showContent", "modifyRoom");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
