package com.hiltondublin.classes;

import java.util.Date;

public class SpecialPrice {
	private int roomTypeID = -1;
	private String comment = null;
	private Date date = null;
	private double price = -1;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTypeID() {
		return roomTypeID;
	}
	public void setTypeID(int typeID) {
		this.roomTypeID = typeID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
