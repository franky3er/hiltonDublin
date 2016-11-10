package com.hiltondublin.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.db.HiltonDublinDBConnection;

/**
 * Servlet implementation class DeleteProductFromReservationServlet
 */
@WebServlet("/DeleteProductFromReservationServlet")
public class DeleteProductFromReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		String bookinNumber = request.getParameter("bookingNumber");
		String productID = request.getParameter("productID");
		
		String []selectedColumn = {dbConnection.RESERVED_PRODUCT_ORDERID};
		
		ResultSet rs = dbConnection.executeQueryAndReturnResultSet(dbConnection.getReserved_ProductAsSQLStatement(selectedColumn, null, productID, bookinNumber, null));
		
		if(rs != null){
			try {
				if(rs.next()){
					String orderID = rs.getString(dbConnection.RESERVED_PRODUCT_ORDERID);
					System.out.println("OrderID: " + orderID);
					if (0<dbConnection.deleteReserved_Products(orderID, null, null, null)){
						request.setAttribute("deleteProductFromReservationSuccessful", "1");
					} else {
						request.setAttribute("deleteProductFromReservationError", "1");
					}
				} else {
					request.setAttribute("deleteProductFromReservationError", "1");
				}
			} catch (SQLException e) {
				request.setAttribute("deleteProductFromReservationError", "1");
			}
		} else {
			request.setAttribute("deleteProductFromReservationError", "1");
		}
		
		Reservation reservation = dbConnection.getReservations(bookinNumber, null, null, null, null, null).get(0);
		if(reservation != null){
			request.setAttribute("reservation", reservation);
			request.setAttribute("showContent", "showReservation");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
