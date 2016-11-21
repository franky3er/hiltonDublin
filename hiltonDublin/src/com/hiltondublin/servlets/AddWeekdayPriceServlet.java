package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.WeekdayPrice;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class AddWeekdayPriceServlet
 */
@WebServlet("/AddWeekdayPriceServlet")
public class AddWeekdayPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		String roomTypeID = Helper.setNullIfEmptyString(request.getParameter("selectedRoomTypeID"));
		String weekDay = Helper.setNullIfEmptyString(request.getParameter("weekDay"));
		String price = Helper.setNullIfEmptyString(request.getParameter("price"));
		
		boolean addWeekdayPrice = true;
		
		if(price != null){
			if(!Helper.isDouble(price)){
				request.setAttribute("modifyRoomTypeErrorStandardPrice", "2");
				addWeekdayPrice = false;
			}
		} else {
			request.setAttribute("modifyRoomTypeErrorStandardPrice", "1");
			addWeekdayPrice = false;
		}
		
		if(weekDay != null){
			List<WeekdayPrice> weekdayPrices = dbConnection.getWeekdayPrices(roomTypeID, null, weekDay, null);
			if(weekdayPrices != null){
				if(!weekdayPrices.isEmpty()){
					request.setAttribute("addWeekdayPriceErrorWeekday", "1");
					addWeekdayPrice = false;
				}
			}
		}
		
		if(addWeekdayPrice){
			dbConnection.insertWeekdayPrice(roomTypeID, price, weekDay);
			request.setAttribute("addWeekdayPriceSuccessful", "1");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
