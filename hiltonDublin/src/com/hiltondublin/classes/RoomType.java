package com.hiltondublin.classes;

import java.util.List;

public class RoomType {
	private String name;
	private int roomTypeID;
	private List<SpecialPrice> specialPrices = null;
	private List<WeekdayPrice> weekdayPrices = null;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRoomTypeID() {
		return roomTypeID;
	}
	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
	
	public List<SpecialPrice> getSpecialPrices() {
		return specialPrices;
	}
	public void setSpecialPrices(List<SpecialPrice> specialPrices) {
		this.specialPrices = specialPrices;
	}
	
	public List<WeekdayPrice> getWeekdayPrices() {
		return weekdayPrices;
	}
	public void setWeekdayPrices(List<WeekdayPrice> weekdayPrices) {
		this.weekdayPrices = weekdayPrices;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
