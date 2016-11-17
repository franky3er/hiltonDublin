package com.hiltondublin.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.hiltondublin.users.Guest;
import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.db.*;;

public class ReservationService {
	public Guest findGuestID(Guest guest, int numofrooms) {
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
		ResultSet key = dbConnection.insertGuest(guest);
		
		if(numofrooms == 0) {
			guest.setGuestID(-1);
		} else {
			int guestID = 0;
			try {
				if(key.next()) {
					guestID = key.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			guest.setGuestID(guestID);
		}
		
		return guest;
	}
	public Reservation findReservationID(Reservation reservation, Guest guest, Room room, int[] Types) {
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();
		
		List<Room> roomtype1 = new ArrayList<Room>();
		List<Room> roomtype2 = new ArrayList<Room>();
		List<Room> roomtype3 = new ArrayList<Room>();
		List<Room> rooms = new ArrayList<Room>();
		
		if(Types[0] != 0) {
			roomtype1 = dbConnection.getAvailableRooms(1, Types[0], reservation.getArrivalDate(), reservation.getDepartureDate(), room.isSmoking());
			
			if(roomtype1 != null) {
				for(int i=0; i<Types[0]; i++) {
					rooms.add(roomtype1.get(i));
				}
			}
			else {
				return null;
			}
		}
		
		if(Types[1] != 0) {
			roomtype2 = dbConnection.getAvailableRooms(1, Types[0], reservation.getArrivalDate(), reservation.getDepartureDate(), room.isSmoking());
			
			if(roomtype2 != null) {
				for(int i=0; i<Types[1]; i++) {
					rooms.add(roomtype2.get(i));
				}
			}
			else {
				return null;
			}
		}
		
		if(Types[2] != 0) {
			roomtype3 = dbConnection.getAvailableRooms(1, Types[0], reservation.getArrivalDate(), reservation.getDepartureDate(), room.isSmoking());
			
			if(roomtype3 != null) {
				for(int i=0; i<Types[2]; i++) {
					rooms.add(roomtype3.get(i));
				}
			}
			else {
				return null;
			}
		}
		
		reservation.setRooms(rooms);
		reservation.setPaid(false);
		reservation.setGuest(guest);
		reservation.setGuestID(guest.getGuestID());
		
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
