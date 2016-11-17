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
 * Servlet implementation class ModifyRoomDeleteRoomServlet
 */
@WebServlet("/ModifyRoomDeleteRoomServlet")
public class ModifyRoomDeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String roomNumber = Helper.setNullIfEmptyString(request.getParameter("roomNumber"));
		String searchRoomNumber = Helper.setNullIfEmptyString(request.getParameter("searchRoomNumber"));
		String searchTypeID = Helper.setNullIfEmptyString(request.getParameter("searchTypeID"));
		String searchSmoking = Helper.setNullIfEmptyString(request.getParameter("searchSmoking"));
		String searchOccupied = Helper.setNullIfEmptyString(request.getParameter("searchOccupied"));
		
		if(dbConnection.deleteRooms(roomNumber, null, null, null, null)>0){
			request.setAttribute("deleteRoomSuccessful", "1");
		} else {
			request.setAttribute("deleteRoomFailed", "1");
		}
		
		System.out.println("HIIIIEEER!!!!");
		List<Room> rooms = dbConnection.getRooms(searchRoomNumber, searchTypeID, searchSmoking, searchOccupied, null);
		request.setAttribute("foundRooms", rooms);
		
		request.setAttribute("lookedForRooms", "1");
		request.setAttribute("showContent", "searchRooms");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
