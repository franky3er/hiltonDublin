package com.hiltondublin.service;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;

import com.hiltondublin.users.Guest;
import com.hiltondublin.users.GuestSingleton;
import com.hiltondublin.classes.Reservation;
import com.hiltondublin.classes.Room;
import com.hiltondublin.db.*;

public class ShowroomService {
	public void reserveroom(List<Room> rooms) throws ParseException {
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance(); 
		
		GuestSingleton guestinfo = GuestSingleton.getInstatnce();
		
		Reservation reservation = new Reservation();
		Guest guest = new Guest();
		
		guest.setFirstName(guestinfo.firstName);
		guest.setLastName(guestinfo.lastName);
		guest.setEmail(guestinfo.email);
		guest.setAddress(guestinfo.address);
		guest.setGuestID(guestinfo.guestID);
		guest.setPassportNr(guestinfo.passportNr);
		guest.setPhoneNumber(guestinfo.phoneNumber);
		
		reservation.setGuest(guest);
		reservation.setRooms(rooms);
		reservation.setGuestID(guest.getGuestID());
		reservation.setPaid(false);
		
		
		//
		reservation.setArrivalDate(dbConnection.mySQLDateFormat.parse(guestinfo.checkin));
		reservation.setDepartureDate(dbConnection.mySQLDateFormat.parse(guestinfo.checkout));
		
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
		
		guestinfo.setReservationID(reservationID);
	
		return ;
	}
}
