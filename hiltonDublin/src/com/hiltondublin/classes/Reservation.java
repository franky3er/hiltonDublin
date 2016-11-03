package com.hiltondublin.classes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hiltondublin.users.Guest;

public class Reservation {
	private int bookingNumber = -1; //=reservationID
	private int guestID = -1;
	private Date arrivalDate = null;
	private Date departureDate = null;
	private boolean paid;
	private Guest guest = null;
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
	public int getGuestID() {
		return guestID;
	}
	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	/**
	 * Adds the prices of all rooms and consumed products during the stay and returns it as a total price
	 * @return double
	 */
	public double createBill(){
		double totalPrice = 0;
		
		//Room Prices
		for(Room room : rooms){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getArrivalDate());
			while(!calendar.getTime().equals(getDepartureDate())){
				//Standard Price
				double localPrice = room.getType().getStandardPrice();
				
				//Weekday Price
				List<WeekdayPrice> weekdayPrices = room.getType().getWeekdayPrices();
				for(WeekdayPrice weekdayPrice : weekdayPrices){
					if(calendar.get(Calendar.DAY_OF_WEEK) == weekdayPrice.getWeekday()){
						localPrice = weekdayPrice.getPrice();
						break;
					}
				}
				
				//Special Price
				List<SpecialPrice> specialPrices = room.getType().getSpecialPrices();
				for(SpecialPrice specialPrice : specialPrices){
					if(calendar.getTime().equals(specialPrice.getDate())){
						localPrice = specialPrice.getPrice();
						break;
					}
				}
				
				totalPrice += localPrice;
				
				calendar.add(Calendar.DATE, 1); //Adds one day to the date
			}
		}
		
		//Consumed Products
		List<ConsumerProduct> consumerProducts = getConsumerProducts();
		for(ConsumerProduct product : consumerProducts){
			totalPrice += product.getPrice();
		}
		
		return totalPrice;
	}
}
