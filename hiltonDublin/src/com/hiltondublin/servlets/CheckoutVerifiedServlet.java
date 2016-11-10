package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.db.HiltonDublinDBConnection;

/**
 * Servlet implementation class CheckoutVerifiedServlet
 */
@WebServlet("/CheckoutVerifiedServlet")
public class CheckoutVerifiedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = (String) request.getParameter("url");
		String reservationID = request.getParameter("reservationID");
		String roomNumber = request.getParameter("roomNumber");
		
		Reservation reservation = dbConnection.getReservations(reservationID, null, null, null, null, null).get(0);
		
		if(reservation!=null){
			List<Room> rooms = reservation.getRooms();
			List<Room> occupiedRooms = new ArrayList<Room>();
			
			for(Room room : rooms){
				if(roomNumber.equals(Integer.toString(room.getRoomNumber()))){
					room.setOccupied(false); //Free room
					request.setAttribute("checkoutInfo", "1");
					
				}
				if(room.isOccupied()){ //Add room to occupied rooms if still occupied
					occupiedRooms.add(room);
				}
			}
			
			dbConnection.updateReservation(reservation); //Update db with reservation
			
			if(occupiedRooms.isEmpty()){ //Create final bill if no more occupied rooms for this reservation
				Map<String, Double> bill = reservation.createBill();
				double totalPrice = determineTotalPrice(bill);
				
				request.setAttribute("bill", bill);
				request.setAttribute("totalPrice", Double.toString(totalPrice));
			} else {
				request.setAttribute("occupiedRooms", occupiedRooms);
			}
			
			
		}
		
		request.setAttribute("reservation", null);
		
		request.setAttribute("roomNumber", roomNumber);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private double determineTotalPrice(Map bill) {
		double totalPrice = 0;
		
		Iterator entries = bill.entrySet().iterator();
		while(entries.hasNext()){
			Entry entry = (Entry) entries.next();
			double productPrice = (double) entry.getValue();
			totalPrice += productPrice;
		}
		
		return totalPrice;
	}

}
