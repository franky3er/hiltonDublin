package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.SpecialPrice;
import com.hiltondublin.classes.WeekdayPrice;
import com.hiltondublin.db.HiltonDublinDBConnection;
import com.hiltondublin.helper.Helper;

/**
 * Servlet implementation class AddSpecialPriceServlet
 */
@WebServlet("/AddSpecialPriceServlet")
public class AddSpecialPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = Helper.setNullIfEmptyString(request.getParameter("url"));
		String roomTypeID = Helper.setNullIfEmptyString(request.getParameter("selectedRoomTypeID"));
		String date = Helper.setNullIfEmptyString(request.getParameter("date"));
		String price = Helper.setNullIfEmptyString(request.getParameter("price"));
		String comment = Helper.setNullIfEmptyString(request.getParameter("comment"));
		
		System.out.println("PRICEEEE: " + price);
		
		boolean addSpecialPrice = true;
		
		if(price != null){
			if(!Helper.isDouble(price)){
				request.setAttribute("modifyRoomTypeErrorStandardPrice", "2");
				addSpecialPrice = false;
			}
		} else {
			request.setAttribute("modifyRoomTypeErrorStandardPrice", "1");
			addSpecialPrice = false;
		}
		
		if(date != null){
			List<SpecialPrice> specialPrices = dbConnection.getSpecialPrices(roomTypeID, date + " 00:00:00", null, null, null);
			if(specialPrices != null){
				if(!specialPrices.isEmpty()){
					request.setAttribute("addSpecialPriceErrorDate", "1");
					addSpecialPrice = false;
				}
			}
		}
		
		if(addSpecialPrice){
			dbConnection.insertSpecialPrice(roomTypeID, date + " 00:00:00", price, comment);
			request.setAttribute("addSpecialPriceSuccessful", "1");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
