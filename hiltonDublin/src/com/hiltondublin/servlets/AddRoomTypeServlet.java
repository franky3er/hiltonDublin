package com.hiltondublin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class AddRoomTypeServlet
 */
@WebServlet("/AddRoomTypeServlet")
public class AddRoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		
		String roomTypeName = Helper.setNullIfEmptyString(request.getParameter("roomTypeName"));
		String picture = Helper.setNullIfEmptyString(request.getParameter("picture"));
		String standardPrice = Helper.setNullIfEmptyString(request.getParameter("standardPrice"));
		String description = Helper.setNullIfEmptyString(request.getParameter("description"));
		
		boolean addRoomType = true;
		
		if(roomTypeName == null){
			request.setAttribute("modifyRoomTypeErrorRoomTypeName", "1");
			addRoomType = false;
		}
		
		if(standardPrice != null){
			if(!Helper.isDouble(standardPrice)){
				request.setAttribute("modifyRoomTypeErrorStandardPrice", "2");
				addRoomType = false;
			}
		} else {
			request.setAttribute("modifyRoomTypeErrorStandardPrice", "1");
			addRoomType = false;
		}
		
		if(addRoomType){
			dbConnection.insertRoomType(null, roomTypeName, picture, standardPrice, description);
			request.setAttribute("addRoomTypeSuccessful", "1");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
