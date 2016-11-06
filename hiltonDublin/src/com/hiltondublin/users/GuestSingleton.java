package com.hiltondublin.users;

import com.hiltondublin.languages.Language;

public class GuestSingleton {
	
	private static GuestSingleton SINGLETON_CLASS_INSTANCE;
	
	public int guestID;
	public String address;
	public int passportNr;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public String email;
	
	private int reservationID;
	
	public int type1;
	public int type2;
	public int type3;
	public String checkin;
	public String checkout;
	public String smoking;
	public int guests;
	
	
	private GuestSingleton() { }
	
	public static GuestSingleton getInstatnce() {
	
		if(SINGLETON_CLASS_INSTANCE == null)
		{
			SINGLETON_CLASS_INSTANCE = new GuestSingleton();
		}
		return SINGLETON_CLASS_INSTANCE;
	}

	public int getGuestID() {
		return guestID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPassportNr() {
		return passportNr;
	}

	public void setPassportNr(int passportNr) {
		this.passportNr = passportNr;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType1() {
		return type1;
	}

	public void setType1(int type1) {
		this.type1 = type1;
	}

	public int getType2() {
		return type2;
	}

	public void setType2(int type2) {
		this.type2 = type2;
	}

	public int getType3() {
		return type3;
	}

	public void setType3(int type3) {
		this.type3 = type3;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public int getGuests() {
		return guests;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
}
