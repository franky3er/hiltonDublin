package com.hiltondublin.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiltondublin.db.HiltonDublinDBConnection;
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
	private HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();
	
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
	public Map<String, Double> createBill(){
		HiltonDublinDBConnection dbConnection = HiltonDublinDBConnection.getInstance();
		
		Map<String, Double> bill = new HashMap<String, Double>();
		
		//Room Prices
		for(Room room : rooms){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getArrivalDate());
			String productName = room.getType().getName() + " (Room Nr.: " + Integer.toString(room.getRoomNumber()) + ") x ";
			double productPrice = 0;
			int amountOfDays = 0;
			while(!calendar.getTime().equals(getDepartureDate())){
				amountOfDays++;
				double localPrice = room.getType().getStandardPrice();
				
				//Weekday Price
				List<WeekdayPrice> weekdayPrices = room.getType().getWeekdayPrices();
				if(weekdayPrices!=null){
					for(WeekdayPrice weekdayPrice : weekdayPrices){
						if(calendar.get(Calendar.DAY_OF_WEEK) == weekdayPrice.getWeekday()){
							localPrice = weekdayPrice.getPrice();
							break;
						}
					}
				}
				
				//Special Price
				List<SpecialPrice> specialPrices = room.getType().getSpecialPrices();
				if(specialPrices!=null){
					for(SpecialPrice specialPrice : specialPrices){
						if(calendar.getTime().equals(specialPrice.getDate())){
							localPrice = specialPrice.getPrice();
							break;
						}
					}
				}
				
				productPrice += localPrice;
				
				calendar.add(Calendar.DATE, 1); //Adds one day to the date
			}
			
			bill.put(productName + Integer.toString(amountOfDays), new Double(productPrice));
		}
		
		HashMap<String, Integer> consumedProducts = new HashMap<String, Integer>();
		//Ammount of Consumed Products
		if(consumerProducts != null){
			for(ConsumerProduct product : consumerProducts){
				if(!consumedProducts.containsKey(Integer.toString(product.getProductID()))){
					consumedProducts.put(Integer.toString(product.getProductID()), new Integer(1));
				}
				else {
					if(consumedProducts.get(Integer.toString(product.getProductID())) != null){
						int ammount = (int) consumedProducts.get(Integer.toString(product.getProductID()));
						ammount++;
						consumedProducts.put(Integer.toString(product.getProductID()), new Integer(ammount));
					}
				}
			}
		}
		
		for(Map.Entry<String, Integer> productEntry : consumedProducts.entrySet()){
			String productID = productEntry.getKey();
			int ammount = productEntry.getValue();
			ConsumerProduct product = dbConnection.getConsumerProducts(productID, null, null, null).get(0);
			bill.put(product.getName() + " x " + Integer.toString(ammount), product.getPrice() * ammount);
		}
		
		return bill;
	}
	
	public HashMap<String, Integer> ammountOfEachRoomType(){
		HashMap<String, Integer> roomTypes = new HashMap<String, Integer>();
		
		for(Room room : rooms){
			String roomTypeName = room.getType().getName();
			
			if(!roomTypes.containsKey(roomTypeName)){
				roomTypes.put(roomTypeName, new Integer(1));
			} else {
				int ammount = (int) roomTypes.get(roomTypeName);
				ammount++;
				roomTypes.put(roomTypeName, new Integer(ammount));
			}
		}
		
		return roomTypes;
	}
	
	public List<String> ammountOfEachRoomTypeAsStrings(){
		List<String> roomTypeAmmounts = new ArrayList<String>();
		
		HashMap<String, Integer> roomTypes = ammountOfEachRoomType();
		for(Map.Entry<String, Integer> roomType : roomTypes.entrySet()){
			String roomTypeName = (String) roomType.getKey();
			int ammount = (int) roomType.getValue();
			
			String roomTypeAmmount = roomTypeName + " x " + ammount;
			roomTypeAmmounts.add(roomTypeAmmount);
		}
		
		return roomTypeAmmounts;
	}
	
	public String getRoomNumbersAsString(){
		String roomNumbers = null;
		if(rooms != null){
			roomNumbers = "";
			for(Room room : rooms){
				roomNumbers += room.getRoomNumber() + "<br/>";
			}
		} 
		return roomNumbers;
	}
	
}
