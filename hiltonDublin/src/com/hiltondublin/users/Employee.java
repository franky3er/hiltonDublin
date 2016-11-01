package com.hiltondublin.users;

public class Employee extends User {
	private String username;
	private String password;
	private int sessionID;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
}
