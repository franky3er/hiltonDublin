package com.hiltondublin.classes;

public class WeekdayPrice {
	private int roomTypeID;
	private int weekday;
	private double price;
	
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getRoomTypeID() {
		return roomTypeID;
	}
	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
}
