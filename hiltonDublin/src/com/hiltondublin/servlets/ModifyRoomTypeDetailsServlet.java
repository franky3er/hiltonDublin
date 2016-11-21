package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.RoomType;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class ModifyRoomTypeDetailsServlet
 */
@WebServlet("/ModifyRoomTypeDetailsServlet")
public class ModifyRoomTypeDetailsServlet extends HttpServlet {
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
		String roomTypeID = Helper.setNullIfEmptyString(request.getParameter("selectedRoomTypeID"));
		
		
		boolean modifyRoomType = true;
		
		if(roomTypeName == null){
			request.setAttribute("modifyRoomTypeErrorRoomTypeName", "1");
			modifyRoomType = false;
		}
		
		if(standardPrice != null){
			if(!Helper.isDouble(standardPrice)){
				request.setAttribute("modifyRoomTypeErrorStandardPrice", "2");
				modifyRoomType = false;
			}
		} else {
			request.setAttribute("modifyRoomTypeErrorStandardPrice", "1");
			modifyRoomType = false;
		}
		
		
		
		if(modifyRoomType){
			List<RoomType> roomTypes = dbConnection.getRoomTypes(roomTypeID, null, null, null, null, null);
			
			if(roomTypes != null){
				if(!roomTypes.isEmpty()){
					RoomType roomType = roomTypes.get(0);
					roomType.setName(roomTypeName);
					roomType.setPictureRessource(picture);
					roomType.setStandardPrice(Double.parseDouble(standardPrice));
					roomType.setDescription(description);
					
					dbConnection.updateRoomType(roomType);
					
					request.setAttribute("modifyRoomTypeSuccessul", "1");
				}
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
