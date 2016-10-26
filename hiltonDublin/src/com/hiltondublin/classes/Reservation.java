package com.hiltondublin.classes;

import java.util.Date;
import java.util.List;

import com.hiltondublin.users.Guest;

public class Reservation {
	private int bookingNumber; //=reservationID
	private Date arrivalDate;
	private Date departureDate;
	private Guest guest;
	private List<Room> rooms = null;
	private List<ConsumerProduct> consumerProducts = null;
	
	public int getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(int bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public List<ConsumerProduct> getConsumerProducts() {
		return consumerProducts;
	}
	public void setConsumerProducts(List<ConsumerProduct> consumerProducts) {
		this.consumerProducts = consumerProducts;
	}
}
