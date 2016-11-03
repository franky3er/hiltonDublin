package com.hiltondublin.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Servlet implementation class checkout
 */
@WebServlet("/checkoutRoom")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = (String) request.getParameter("url");
		String roomNumber = (String) request.getParameter("roomNumber");
		if(roomNumber != null){
			request.setAttribute("roomNumber", roomNumber);
			if(!(roomNumber.isEmpty() || roomNumber.trim().equals(""))){
				if(roomNumber.matches("\\d+")){
					Reservation reservation = dbConnection.getReservationFromCheckedOurRoom(roomNumber);
					if (reservation == null){
						System.out.println("No Reservation found for room number " + roomNumber);
						request.setAttribute("checkoutError", "4");
					} else {
						//Set Room Free
						List<Room> rooms = reservation.getRooms();
						List<Room> occupiedRooms = new ArrayList<Room>();
						for(Room room : rooms){
							if(room.getRoomNumber() == Integer.parseInt(roomNumber)){
								room.setOccupied(false);
							}
							if(room.isOccupied()){
								occupiedRooms.add(room);
							}
						}
						dbConnection.updateReservation(reservation);
						
						//Check if all rooms are checked out and create final bill
						if(occupiedRooms.isEmpty()){
							Map<String, Double> bill = reservation.createBill();
							request.setAttribute("bill", bill);
							double totalPrice = determineTotalPrice(bill);
							request.setAttribute("totalPrice", Double.toString(totalPrice));
						} else {
							request.setAttribute("occupiedRooms", occupiedRooms);
						}
					}
				} else {
					System.out.println(roomNumber + " is not a number!");
					request.setAttribute("checkoutError", "1");
				}
			} else {
				System.out.println("Empty Room Number!");
				request.setAttribute("checkoutError", "3");
			}
		} else {
			System.out.println("Room Number is null!");
			request.setAttribute("checkoutError", "2");
		}
		
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
