package com.hiltondublin.users;


public class Guest extends User {
	private int guestID;
	private String address;
	private int passportNr;
	
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
	
}