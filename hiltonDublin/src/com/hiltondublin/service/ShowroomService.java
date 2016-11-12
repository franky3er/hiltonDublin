package com.hiltondublin.service;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;

import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.db.*;

public class ShowroomService {
	public Reservation reserveroom(List<Room> rooms, Reservation reservation) throws ParseException {
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
		
		reservation.setRooms(rooms);
		reservation.setPaid(false);
		
		ResultSet key = dbConnection.insertReservation(reservation);
		
		int reservationID = 0;
		
		try {
			if(key.next()) {
				reservationID = key.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reservation.setBookingNumber(reservationID);
		
		return reservation;
	}
}
