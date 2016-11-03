package com.hiltondublin.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hiltondublin.users.Guest;
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
}
